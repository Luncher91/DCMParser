package net.alenzen.dcm;

import java.util.List;

public class Distribution extends BasicSyntaxElement {
	private int sizeX;
	private String unitX;
	private List<IValue> stx;

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

	public List<IValue> getStx() {
		return stx;
	}

	public void setStx(List<IValue> stx) {
		this.stx = stx;
	}
}
