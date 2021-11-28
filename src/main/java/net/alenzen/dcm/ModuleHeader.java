package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class ModuleHeader extends ModuleHeaderLine implements IDcmWritable {
	private String name;
	private List<ModuleHeaderLine> lines;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ModuleHeaderLine> getLines() {
		return lines;
	}

	public void setLines(List<ModuleHeaderLine> lines) {
		this.lines = lines;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln(this.getComments());
		dcmWriter.writeln("MODULKOPF", name, DcmWriter.toDcmString(this.getText()));
		
		for(int i = 0; i < this.lines.size(); i++) {
			dcmWriter.writeln(this.lines.get(i).getComments());
			dcmWriter.writeln("MODULKOPF", DcmWriter.toDcmString(this.lines.get(i).getText()));
		}
		
		dcmWriter.writeln();
	}
}
