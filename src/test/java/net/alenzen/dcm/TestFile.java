package net.alenzen.dcm;

public enum TestFile {
	A("freeTest.dcm");
	
	private String filename;

	TestFile(String fName) {
		setFilename(fName);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
