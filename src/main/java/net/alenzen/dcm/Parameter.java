package net.alenzen.dcm;

public class Parameter extends BasicSyntaxElement {
	private String unitW;
	private IValue value;

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public IValue getValue() {
		return value;
	}

	public void setValue(IValue value) {
		this.value = value;
	}
}
