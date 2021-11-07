package net.alenzen.dcm;

import java.util.List;

public class ModuleHeader {
	private String name;
	private List<String> text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}
}
