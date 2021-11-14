package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class ModuleHeader implements IDcmWritable {
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

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		if(text == null || text.size() == 0) {
			return;
		}
		
		dcmWriter.writeln("MODULKOPF", name, DcmWriter.toDcmString(text.get(0)));
		
		for(int i = 1; i < text.size(); i++) {
			dcmWriter.writeln("MODULKOPF", DcmWriter.toDcmString(text.get(i)));
		}
		
		dcmWriter.writeln();
	}
}
