package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class CharacteristicMap extends BasicSyntaxElement {
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

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		writeTo(p, "KENNFELD");
	}
	
	protected void writeTo(DcmWriter p, String keyword) throws IOException {
		p.writeln(keyword, this.getName(), Integer.toString(sizeX), Integer.toString(sizeY));

		p.indent();
		super.writeTo(p);
		p.writeln("EINHEIT_X", DcmWriter.toDcmString(unitX));
		p.writeln("EINHEIT_Y", DcmWriter.toDcmString(unitY));
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		writeToAfterUnits(p);
		p.writeln("ST/X", stx);
		for(int i = 0; i < values.size(); i++) {
			List<IValue> vals = values.get(i);
			IValue st = sty.get(i);
			p.writeln("ST/Y", st.toString());
			p.writeIValues(vals);
		}
		p.dedent();

		p.writeEnd();
		p.writeln();
	}

	protected void writeToAfterUnits(DcmWriter p) throws IOException {
	}
}
