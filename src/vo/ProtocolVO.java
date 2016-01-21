/*MessageVO.java
 * ���ߣ�yanghongbin
 */
package vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolVO {
	private int messageTypeID;

	private int id;

	private String type;

	private String name;

	private String comment = "";

	private List<ProtocolContentVO> children = new ArrayList<ProtocolContentVO>();

	private Map<String, String> fieldMap = new HashMap<String, String>();

	public Map<String, String> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}

	/**
	 * @return the messageTypeID
	 */
	public int getMessageTypeID() {
		return messageTypeID;
	}

	/**
	 * @param messageTypeID
	 *            the messageTypeID to set
	 */
	public void setMessageTypeID(int messageTypeID) {
		this.messageTypeID = messageTypeID;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the children
	 */
	public List<ProtocolContentVO> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<ProtocolContentVO> children) {
		this.children = children;
	}
}
