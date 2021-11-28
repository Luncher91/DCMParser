package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Function implements IDcmWritable {
	private List<String> comments;
	private String name;
	private String version;
	private String longname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLongname() {
		return longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
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
		dcmWriter.writeln("FKT", name, DcmWriter.toDcmString(version), DcmWriter.toDcmString(longname));
	}
}
