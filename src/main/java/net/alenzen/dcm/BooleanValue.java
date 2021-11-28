package net.alenzen.dcm;

import java.io.IOException;

public class BooleanValue extends Value {
	private boolean value;

	public BooleanValue(boolean asBoolean) {
		this.value = asBoolean;
	}

	public BooleanValue() {
	}

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
		dcmWriter.writeln(comments);
		dcmWriter.writeln("WERT", this.toString());
	}
}
