package data;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.element.VariableElement;

import vo.ProtocolContentVO;
import vo.ProtocolVO;
import vo.OutputVO;

/*DataManager.java
 * yanghongbin
 */

public class DataManager {

	private static DataManager instance;

	
	private List<ProtocolVO> messages = new LinkedList<ProtocolVO>();

	private List<ProtocolContentVO> objects = new ArrayList<ProtocolContentVO>();

	private List<OutputVO> outputVOList = new ArrayList<OutputVO>();

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	public void addOutputVO(OutputVO outputVO){
		outputVOList.add(outputVO);
	}
	
	public void addObject(ProtocolContentVO messageContentVO) {
		Boolean value = false;
		for (int i = 0; i < this.objects.size(); i++) {
			ProtocolContentVO oldMessageContentVO = this.objects.get(i);
			if (oldMessageContentVO.getType().equals(messageContentVO.getType())) {
				if (oldMessageContentVO.getHasChildren() && messageContentVO.getHasChildren()) {
					if (messageContentVO.getChildren().size() > oldMessageContentVO.getChildren().size()) {
						oldMessageContentVO.setName(messageContentVO.getName());
						oldMessageContentVO.setLength(messageContentVO.getLength());
						oldMessageContentVO.setComment(messageContentVO.getComment());
						oldMessageContentVO.setChildren(messageContentVO.getChildren());
						oldMessageContentVO.setFieldMap(messageContentVO.getFieldMap());
					}
					value = true;
					break;
				}
			}
		}
		if (!value) {
			ProtocolContentVO newMessageContentVO = new ProtocolContentVO();
			newMessageContentVO.setContentType(messageContentVO.getContentType());
			newMessageContentVO.setName(messageContentVO.getName());
			newMessageContentVO.setType(messageContentVO.getType());
			newMessageContentVO.setDefType(messageContentVO.getDefType());
			newMessageContentVO.setLength(messageContentVO.getLength());
			newMessageContentVO.setComment(messageContentVO.getComment());
			newMessageContentVO.setHasChildren(messageContentVO.getHasChildren());
			newMessageContentVO.setChildren(messageContentVO.getChildren());
			newMessageContentVO.setFieldMap(messageContentVO.getFieldMap());
			this.objects.add(newMessageContentVO);
		}
	}

	/**
	 * @return the messages
	 */
	public List<ProtocolVO> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<ProtocolVO> messages) {
		this.messages = messages;
	}

	/**
	 * @return the objects
	 */
	public List<ProtocolContentVO> getObjects() {
		return objects;
	}

	/**
	 * @param objects
	 *            the objects to set
	 */
	public void setObjects(List<ProtocolContentVO> objects) {
		this.objects = objects;
	}

	public List<OutputVO> getOutputVOList() {
		return outputVOList;
	}
}
