package net.alenzen.dcm;

import java.util.List;

public class CharacteristicLine extends BasicSyntaxElement {
	private int sizeX;
	private String unitX;
	private String unitW;
	private List<IValue> stx;
	private List<IValue> values;

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public String getUnitX() {
		return unitX;
	}

	public void setUnitX(String unitX) {
		this.unitX = unitX;
	}

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public List<IValue> getStx() {
		return stx;
	}

	public void setStx(List<IValue> stx) {
		this.stx = stx;
	}

	public List<IValue> getValues() {
		return values;
	}

	public void setValues(List<IValue> values) {
		this.values = values;
	}
}
