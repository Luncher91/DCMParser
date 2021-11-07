package net.alenzen.dcm;

import java.util.List;

public class Array extends BasicSyntaxElement {
	private int sizeX;
	private String unitW;
	private List<IValue> values;

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public List<IValue> getValues() {
		return values;
	}

	public void setValues(List<IValue> values) {
		this.values = values;
	}
}
