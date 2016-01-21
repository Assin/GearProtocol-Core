/*MessageContentVO.java
 * ���ߣ�yanghongbin
 */
package vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolContentVO {

	private String contentType = "";
	private String name = "";
	private String type = "";

	private String defType = "";

	private String serverType = "";
	private String length = "0";
	private String comment = "";
	private Boolean hasChildren = false;
	private List<ProtocolContentVO> children;
	private Map<String, String> fieldMap = new HashMap<String, String>();

	public Map<String, String> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
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
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(String length) {
		this.length = length;
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
	 * @return the hasChildren
	 */
	public Boolean getHasChildren() {
		return hasChildren;
	}

	/**
	 * @param hasChildren
	 *            the hasChildren to set
	 */
	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
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

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the defType
	 */
	public String getDefType() {
		return defType;
	}

	/**
	 * @param defType
	 *            the defType to set
	 */
	public void setDefType(String defType) {
		this.defType = defType;
	}

	/**
	 * @return the serverType
	 */
	public String getServerType() {
		return serverType;
	}

	/**
	 * @param serverType
	 *            the serverType to set
	 */
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

}
