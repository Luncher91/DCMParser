package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Criterion implements IDcmWritable {
	private String name;
	private List<IValue> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IValue> getValues() {
		return values;
	}

	public void setValues(List<IValue> values) {
		this.values = values;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("KRITERIUM " + name, values);
	}
}
