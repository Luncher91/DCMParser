package net.alenzen.dcm;

import java.io.IOException;

public class BooleanValue implements IValue {
	private boolean value;

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Boolean.toString(this.value);
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("WERT", this.toString());
	}
}
