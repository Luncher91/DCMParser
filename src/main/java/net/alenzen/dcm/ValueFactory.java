package net.alenzen.dcm;

public class ValueFactory {
	public static TextValue fromString(String v) {
		TextValue val = new TextValue();
		val.setValue(v);
		return val;
	}
	
	public static NumberValue fromNumber(String v) {
		return new NumberValue(v);
	}
	
	public static BooleanValue fromBoolean(boolean v) {
		BooleanValue val = new BooleanValue();
		val.setValue(v);
		return val;
	}
}
