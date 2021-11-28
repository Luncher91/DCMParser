package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class CharacteristicLine extends BasicSyntaxElement {
	private int sizeX;
	private List<String> unitXComments;
	private String unitX;
	private List<String> unitWComments;
	private String unitW;
	private List<Value> stx;
	private List<Value> values;

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

	public List<Value> getStx() {
		return stx;
	}

	public void setStx(List<Value> stx) {
		this.stx = stx;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public List<String> getUnitXComments() {
		return unitXComments;
	}

	public void setUnitXComments(List<String> unitXComments) {
		this.unitXComments = unitXComments;
	}

	public List<String> getUnitWComments() {
		return unitWComments;
	}

	public void setUnitWComments(List<String> unitWComments) {
		this.unitWComments = unitWComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		writeTo(p, "KENNLINIE");
	}
	
	protected void writeTo(DcmWriter p, String keyword) throws IOException {
		super.writeBeginning(p, keyword, Integer.toString(sizeX));

		p.indent();
		super.writeBodyTo(p);
		p.writeln(unitXComments);
		p.writeln("EINHEIT_X", DcmWriter.toDcmString(unitX));
		p.writeln(unitWComments);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		writeToAfterUnits(p);
		p.writeln("ST/X", stx);
		p.writeValues(values);
		p.dedent();

		super.writeEnd(p);
	}
	
	protected void writeToAfterUnits(DcmWriter p) throws IOException {
	}
}
