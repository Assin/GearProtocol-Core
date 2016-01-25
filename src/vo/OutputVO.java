package vo;

import java.util.List;

public class OutputVO {
	private String name = "";
	private String programLanguage = "";
	private List<ConvertDataTypeVO> converDataTypes;
	private List<OutputItemVO> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProgramLanguage() {
		return programLanguage;
	}
	
	public void setProgramLanguage(String programLanguage) {
		this.programLanguage = programLanguage;
	}
	
	public List<ConvertDataTypeVO> getConverDataTypes() {
		return converDataTypes;
	}

	public void setConverDataTypes(List<ConvertDataTypeVO> converDataTypes) {
		this.converDataTypes = converDataTypes;
	}

	public List<OutputItemVO> getItems() {
		return items;
	}

	public void setItems(List<OutputItemVO> items) {
		this.items = items;
	}


}
