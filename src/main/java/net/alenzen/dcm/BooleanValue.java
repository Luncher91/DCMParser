package net.alenzen.dcm;

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
}
