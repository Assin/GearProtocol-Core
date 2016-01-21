package vo;

import java.util.List;

public class OutputVO {
	private String name = "";
	private List<OutputItemVO> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OutputItemVO> getItems() {
		return items;
	}

	public void setItems(List<OutputItemVO> items) {
		this.items = items;
	}

}
