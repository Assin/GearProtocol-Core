import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import data.ConfigXMLDefine;
import data.DataManager;
import vo.ConvertDataTypeVO;
import vo.OutputItemVO;
import vo.OutputVO;

/*GenerateMessage.java
 * 
 */

public class GearProtocol {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 如果第一个参数有，那么就使用第一个参数的配置文件地址，没有的话默认寻找./config/config.xml
		// 从命令传参的使用配置文件路径
		String confPath;
		if (args.length > 0) {
			confPath = args[0];
		} else {
			confPath = "./config/config.xml";
		}
		// 读取配置文件
		File file = new File(confPath);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file.getCanonicalPath());
		Element docRoot = doc.getRootElement();
		for (Iterator<Element> i = docRoot.elementIterator(); i.hasNext();) {
			Element element = i.next();
			if (element.getQName().getName().equals(ConfigXMLDefine.PROTOCOL)) {
				// 解析protocol节点
				for (Iterator<Element> e = element.elementIterator(); e.hasNext();) {
					Element folderItem = e.next();
					// 解析folder节点
					if (folderItem.getQName().getName().equals(ConfigXMLDefine.FOLDER)) {
						ParseProtocolXML.PROTOCOL_FILE_LIST.add(folderItem.getTextTrim());
					}
				}
			} else if (element.getQName().getName().equals(ConfigXMLDefine.OUTPUT)) {
				OutputVO outputVO = new OutputVO();
				outputVO.setName(element.attributeValue(ConfigXMLDefine.NAME));
				outputVO.setProgramLanguage(element.attributeValue(ConfigXMLDefine.PROGRAM_LANGUAGE));
				outputVO.setConverDataTypes(new ArrayList<ConvertDataTypeVO>());
				outputVO.setItems(new ArrayList<OutputItemVO>());
				// 解析output节点
				for (Iterator<Element> e = element.elementIterator(); e.hasNext();) {
					Element item = e.next();
					if (item.getQName().getName().equals(ConfigXMLDefine.CONVERT_DATATYPE)) {
						ConvertDataTypeVO convertVO = new ConvertDataTypeVO();
						convertVO.setGptype(item.attributeValue(ConfigXMLDefine.GPTYPE));
						convertVO.setTo(item.attributeValue(ConfigXMLDefine.TO));
						outputVO.getConverDataTypes().add(convertVO);
					} else if (item.getQName().getName().equals(ConfigXMLDefine.OUTPUT_ITEM)) {
						OutputItemVO itemVO = new OutputItemVO();
						itemVO.setTemplateFolder(item.attributeValue(ConfigXMLDefine.TEMPLATE_FOLDER));
						itemVO.setTemplateFile(item.attributeValue(ConfigXMLDefine.TEMPLATE_FILE));
						itemVO.setOrigin(item.attributeValue(ConfigXMLDefine.ORIGIN).toLowerCase());
						itemVO.setTo(item.attributeValue(ConfigXMLDefine.TO));
						itemVO.setCharset(item.attributeValue(ConfigXMLDefine.CHARSET));
						itemVO.setInjectionList(new ArrayList<String>());
						// 解析injection节点
						for (Iterator<Element> injectionElement = item.elementIterator(); injectionElement.hasNext();) {
							Element injectionItem = injectionElement.next();
							if (injectionItem.getQName().getName().equals(ConfigXMLDefine.INJECTION)) {
								itemVO.getInjectionList().add(injectionItem.getTextTrim());
							}
						}
						outputVO.getItems().add(itemVO);
					}
				}

				DataManager.getInstance().addOutputVO(outputVO);
				// 解析协议内容 并输出
				ParseProtocolXML getMessageXML = new ParseProtocolXML();
				getMessageXML.parseProtocolXMLFileList(outputVO);
			}

		}
	}

}
