package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Matrix extends BasicSyntaxElement {
	private int sizeX;
	private int sizeY;
	private List<String> unitWComments;
	private String unitW;
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

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public List<List<Value>> getValues() {
		return values;
	}

	public void setValues(List<List<Value>> values) {
		this.values = values;
	}

	public List<String> getUnitWComments() {
		return unitWComments;
	}

	public void setUnitWComments(List<String> unitWComments) {
		this.unitWComments = unitWComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeBeginning(p, "FESTWERTEBLOCK", Integer.toString(sizeX), "@", Integer.toString(sizeY));

		p.indent();
		super.writeBodyTo(p);
		p.writeln(unitWComments);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		for (List<Value> vals : values) {
			p.writeValues(vals);
		}
		p.dedent();

		super.writeEnd(p);
	}
}
