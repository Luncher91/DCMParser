package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Matrix extends BasicSyntaxElement {
	private int sizeX;
	private int sizeY;
	private String unitW;
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

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public List<List<IValue>> getValues() {
		return values;
	}

	public void setValues(List<List<IValue>> values) {
		this.values = values;
	}
	
	@Override
	public void writeTo(DcmWriter p) throws IOException {
		p.writeln("FESTWERTEBLOCK", this.getName(), Integer.toString(sizeX), "@", Integer.toString(sizeY));

		p.indent();
		super.writeTo(p);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		for (List<IValue> vals : values) {
			p.writeIValues(vals);
		}
		p.dedent();

		p.writeEnd();
		p.writeln();
	}
}
