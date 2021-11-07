package net.alenzen.dcm;

import java.util.List;

public class CharacteristicMap extends BasicSyntaxElement{
	private int sizeX;
	private int sizeY;
	private String unitX;
	private String unitY;
	private String unitW;
	private List<IValue> stx;
	private List<IValue> sty;
	private List<List<IValue>> values;

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public String getUnitX() {
		return unitX;
	}

	public void setUnitX(String unitX) {
		this.unitX = unitX;
	}

	public String getUnitY() {
		return unitY;
	}

	public void setUnitY(String unitY) {
		this.unitY = unitY;
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

	public List<IValue> getSty() {
		return sty;
	}

	public void setSty(List<IValue> sty) {
		this.sty = sty;
	}

	public List<List<IValue>> getValues() {
		return values;
	}

	public void setValues(List<List<IValue>> values) {
		this.values = values;
	}
}
