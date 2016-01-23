import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.VariableElement;

import data.DataManager;
import data.OutputItemOriginType;
import data.TypeDef;
import utils.FileUtil;
import vo.ProtocolContentVO;
import vo.ProtocolVO;
import vo.OutputItemVO;
import vo.OutputVO;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/*	OutputFile.java
 *  yanghongbin
 */

public class OutputProtocolFile {
	public void output(OutputVO outputVO) {

		for (OutputItemVO item : outputVO.getItems()) {
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			try {
				cfg.setDirectoryForTemplateLoading(new File(item.getTemplateFolder()));
				Template temp = cfg.getTemplate(item.getTemplateFile());
				if (item.getOrigin().equals(OutputItemOriginType.SINGLE)) {
					// 要注入的内容
					Map<String, Object> map = new HashMap<String, Object>();
					for (String injectionString : item.getInjectionList()) {
						execInjectionToMap(map, injectionString, null, null);
					}
					String path = getOutputFilePath(0, new String(item.getTo()), null, null);
					File file = FileUtil.getOutPutFile(path, true);
					Writer out = new OutputStreamWriter(new FileOutputStream(file), item.getCharset());
					temp.process(map, out);
					out.flush();
				} else {

					if (item.getOrigin().equals(OutputItemOriginType.EACH_MESSAGE)) {
						List<ProtocolVO> list = DataManager.getInstance().getMessages();
						for (ProtocolVO messageVO : list) {
							// 要注入的内容
							Map<String, Object> map = new HashMap<String, Object>();
							for (String injectionString : item.getInjectionList()) {
								execInjectionToMap(map, injectionString, messageVO, null);
							}
							String path = getOutputFilePath(0, new String(item.getTo()), messageVO, null);
							File file = FileUtil.getOutPutFile(path, true);
							Writer out = new OutputStreamWriter(new FileOutputStream(file), item.getCharset());
							temp.process(map, out);
							out.flush();
						}
					}
					if (item.getOrigin().equals(OutputItemOriginType.EACH_OBJECT)) {
						List<ProtocolContentVO> list = DataManager.getInstance().getObjects();
						for (ProtocolContentVO messageContentVO : list) {
							// 要注入的内容
							Map<String, Object> map = new HashMap<String, Object>();
							for (String injectionString : item.getInjectionList()) {
								execInjectionToMap(map, injectionString, null, messageContentVO);
							}
							String path = getOutputFilePath(0, new String(item.getTo()), null, messageContentVO);
							File file = FileUtil.getOutPutFile(path, true);
							Writer out = new OutputStreamWriter(new FileOutputStream(file), item.getCharset());
							temp.process(map, out);
							out.flush();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		System.out.println("output" + " " + outputVO.getName() + " " + "done");
	}

	private void execInjectionToMap(Map<String, Object> map, String injectionString, ProtocolVO messageVO, ProtocolContentVO messageContentVO) {
		if (injectionString.equals("typeDef")) {
			map.put("typeDef", new TypeDef());
		} else if (injectionString.equals("messageVO")) {
			map.put("messageVO", messageVO);
		} else if (injectionString.equals("messageContentVO")) {
			map.put("messageContentVO", messageContentVO);
		} else if (injectionString.equals("messages")) {
			map.put("messages", DataManager.getInstance().getMessages());
		} else if (injectionString.equals("objects")) {
			map.put("objects", DataManager.getInstance().getObjects());
		}
	}

	private String getOutputFilePath(int start, String src, ProtocolVO messageVO, ProtocolContentVO messageContentVO) {
		int index = src.indexOf("${", start);
		if (index >= 0) {
			int lastIndex = src.indexOf("}", index);
			if (lastIndex > index) {
				String type = src.substring(index + 2, lastIndex);
				String value = "";
				if (messageVO != null) {
					value = messageVO.getFieldMap().get(type);
				} else if (messageContentVO != null) {
					value = messageContentVO.getFieldMap().get(type);
				}
				src = src.replaceAll("\\$\\{" + type + "\\}", value);
				// 替换下一个
				getOutputFilePath(lastIndex, src, messageVO, messageContentVO);
			}
		}
		return src;
	}
}
