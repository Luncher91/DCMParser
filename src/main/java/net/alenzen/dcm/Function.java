package net.alenzen.dcm;

import java.io.IOException;

public class Function implements IDcmWritable {
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

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("FKT", name, DcmWriter.toDcmString(version), DcmWriter.toDcmString(longname));
	}
}
