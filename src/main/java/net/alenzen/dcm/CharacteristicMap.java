package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class CharacteristicMap extends BasicSyntaxElement {
	private int sizeX;
	private int sizeY;
	private List<String> unitXComments;
	private String unitX;
	private List<String> unitYComments;
	private String unitY;
	private List<String> unitWComments;
	private String unitW;
	private List<Value> stx;
	private List<Value> sty;
	private List<List<Value>> values;

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

	public List<Value> getStx() {
		return stx;
	}

	public void setStx(List<Value> stx) {
		this.stx = stx;
	}

	public List<Value> getSty() {
		return sty;
	}

	public void setSty(List<Value> sty) {
		this.sty = sty;
	}

	public List<List<Value>> getValues() {
		return values;
	}

	public void setValues(List<List<Value>> values) {
		this.values = values;
	}

	public List<String> getUnitXComments() {
		return unitXComments;
	}

	public void setUnitXComments(List<String> unitXComments) {
		this.unitXComments = unitXComments;
	}

	public List<String> getUnitYComments() {
		return unitYComments;
	}

	public void setUnitYComments(List<String> unitYComments) {
		this.unitYComments = unitYComments;
	}

	public List<String> getUnitWComments() {
		return unitWComments;
	}

	public void setUnitWComments(List<String> unitWComments) {
		this.unitWComments = unitWComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		writeTo(p, "KENNFELD");
	}
	
	protected void writeTo(DcmWriter p, String keyword) throws IOException {
		super.writeBeginning(p, keyword, Integer.toString(sizeX), Integer.toString(sizeY));

		p.indent();
		super.writeBodyTo(p);
		p.writeln(unitXComments);
		p.writeln("EINHEIT_X", DcmWriter.toDcmString(unitX));
		p.writeln(unitYComments);
		p.writeln("EINHEIT_Y", DcmWriter.toDcmString(unitY));
		p.writeln(unitWComments);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		writeToAfterUnits(p);
		p.writeln("ST/X", stx);
		for(int i = 0; i < values.size(); i++) {
			List<Value> vals = values.get(i);
			Value st = sty.get(i);
			p.writeln(st.getComments());
			p.writeln("ST/Y", st.toString());
			p.writeValues(vals);
		}
		p.dedent();

		super.writeEnd(p);
	}

	protected void writeToAfterUnits(DcmWriter p) throws IOException {
	}
}
