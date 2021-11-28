package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Criterion implements IDcmWritable {
	private List<String> comments;
	private String name;
	private List<Value> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln(comments);
		dcmWriter.writeln("KRITERIUM " + name, values);
	}
}
