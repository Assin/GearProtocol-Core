package vo;

import java.util.ArrayList;

public class OutputItemVO {
	private String templateFolder = "";
	private String templateFile = "";
	private String origin = "";
	private String to = "";
	private String charset = "";
	private ArrayList<String> injectionList;

	public String getTemplateFolder() {
		return templateFolder;
	}

	public void setTemplateFolder(String templateFolder) {
		this.templateFolder = templateFolder;
	}

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public ArrayList<String> getInjectionList() {
		return injectionList;
	}

	public void setInjectionList(ArrayList<String> injectionList) {
		this.injectionList = injectionList;
	}

}
