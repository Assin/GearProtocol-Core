import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import data.DataManager;
import data.ProtocolType;
import utils.StringUtil;
import utils.TypeUtil;
import vo.OutputVO;
import vo.ProtocolContentVO;
import vo.ProtocolVO;

/*GetMessageXML.java
 * yanghongbin
 */

public class ParseProtocolXML {
	public static ArrayList<String> PROTOCOL_FILE_LIST = new ArrayList<String>();
	private OutputVO currentOutputVO;
	
	public OutputVO getCurrentOutputVO() {
		return currentOutputVO;
	}

	public void setCurrentOutputVO(OutputVO currentOutputVO) {
		this.currentOutputVO = currentOutputVO;
	}

	//解析协议xml文件列表
	public void parseProtocolXMLFileList(OutputVO outputVO) throws Exception {
		setCurrentOutputVO(outputVO);
		//遍历每个文件夹的每个协议xml文件去解析
		for (String item : PROTOCOL_FILE_LIST) {
			File folder = new File(item).getCanonicalFile();
			File[] files = folder.listFiles();
			if (files == null) {
				throw new Exception("ERROR_FOLDER_IS_EMPTY：" + folder.getCanonicalPath());
			} else {
				for (int i = 0; i < files.length; i++) {
					File currentFile = (File) files[i];
					if (!currentFile.isFile() || currentFile.isHidden() || currentFile.isDirectory()) {
						continue;
					} else {
						SAXReader reader = new SAXReader();
						Document document = reader.read(currentFile.getCanonicalPath());
						this.parse(currentFile, document);
					}
				}
			}	
		}
		OutputProtocolFile outputFile = new OutputProtocolFile();
		outputFile.output(outputVO);
	}
	
	private void parse(File currentFile, Document doc) throws Exception {
		Element root = doc.getRootElement();
		for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			ProtocolVO messageVO = new ProtocolVO();
			int messageType = 0;
			
			if (element.getQName().getName().equals(ProtocolType.SEND_MESSAGE)) {
				messageType = ProtocolType.SEND_MESSAGE_ID;
			} else if (element.getQName().getName().equals(ProtocolType.BACK_MESSAGE)) {
				messageType = ProtocolType.BACK_MESSAGE_ID;
			} else {
				throw new Exception("ERROR_MSG_TYPE:" + currentFile.getCanonicalPath() + "\n" + element.getQName().getName());
			}
			messageVO.setMessageTypeID(messageType);
			
			messageVO.setId(Integer.parseInt(element.attribute(ProtocolType.ID).getValue()));
			messageVO.getFieldMap().put(ProtocolType.ID, Integer.toString(messageVO.getId()));
			
			messageVO.setType(element.attribute(ProtocolType.TYPE).getValue().toUpperCase());
			messageVO.getFieldMap().put(ProtocolType.TYPE, messageVO.getType());
			
			messageVO.setName(StringUtil.toUpperCaseFirstChart(element.attribute(ProtocolType.NAME).getValue()));
			messageVO.getFieldMap().put(ProtocolType.NAME, messageVO.getName());
			
			if (!(element.attribute(ProtocolType.COMMENT) == null)) {
				messageVO.setComment(element.attribute(ProtocolType.COMMENT).getValue());
				messageVO.getFieldMap().put(ProtocolType.COMMENT, messageVO.getComment());
			}
			
			for (Iterator<Element> e = element.elementIterator(); e.hasNext();) {
				Element elementItem = (Element) e.next();
				messageVO.getChildren().add(parseMessageContentVO(elementItem));
			}
			
			DataManager.getInstance().getMessages().add(messageVO);
		}
	}

	
	private ProtocolContentVO parseMessageContentVO(Element element) {
		ProtocolContentVO messageContentVO = new ProtocolContentVO();
		messageContentVO.setContentType(element.getQName().getName().toLowerCase());
		messageContentVO.setName(element.attributeValue(ProtocolType.NAME));
		messageContentVO.getFieldMap().put(ProtocolType.NAME, messageContentVO.getName());
		
		if (!(element.attribute(ProtocolType.LENGTH) == null)) {
			messageContentVO.setLength(element.attributeValue(ProtocolType.LENGTH));
			messageContentVO.getFieldMap().put(ProtocolType.LENGTH, messageContentVO.getLength());
		}
		if (!(element.attribute(ProtocolType.COMMENT) == null)) {
			messageContentVO.setComment(element.attributeValue(ProtocolType.COMMENT));
			messageContentVO.getFieldMap().put(ProtocolType.COMMENT, messageContentVO.getComment());
		}
		if (element.hasContent()) {
			messageContentVO.setHasChildren(true);
			messageContentVO.setChildren(new ArrayList<ProtocolContentVO>());
			for (Iterator<Element> e = element.elementIterator(); e.hasNext();) {
				Element elementItem = (Element) e.next();
				messageContentVO.getChildren().add(parseMessageContentVO(elementItem));
			}
		}
		
		if (messageContentVO.getContentType().equals(ProtocolType.OBJECT) || (messageContentVO.getContentType().equals(ProtocolType.List) && messageContentVO.getHasChildren())) {
			//这里处理了自定义类型的type，也就是要单独生成类
			messageContentVO.setType(StringUtil.toUpperCaseFirstChart(element.attributeValue(ProtocolType.TYPE)));
			messageContentVO.getFieldMap().put(ProtocolType.TYPE, messageContentVO.getType());
			// messageContentVO.setDefType(messageContentVO.getType());
		} else {
			//处理基本类型定义
			messageContentVO.setDefType(element.attributeValue(ProtocolType.TYPE).toLowerCase());
			messageContentVO.setType(TypeUtil.parseDefTypeToRealType(currentOutputVO.getProgramLanguage(), messageContentVO.getDefType()));
			//TODO 这里是之前遗留的写死的处理服务器生成，可以考虑不用
			//messageContentVO.setServerType(TypeUtil.parseDefTypeToRealServerType(messageContentVO.getDefType()));
			messageContentVO.getFieldMap().put(ProtocolType.TYPE, messageContentVO.getType());
		}
		
		if (messageContentVO.getContentType().equals(ProtocolType.OBJECT)) {
			DataManager.getInstance().addObject(messageContentVO);
		}
		
		if(messageContentVO.getContentType().equals(ProtocolType.List) && messageContentVO.getHasChildren()){
			DataManager.getInstance().addObject(messageContentVO);
		}
		return messageContentVO;
	}
}
