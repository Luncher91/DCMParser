package net.alenzen.dcm;

import java.util.List;

public class ModuleHeaderLine {
	protected List<String> comments;
	private String text;
	
	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
