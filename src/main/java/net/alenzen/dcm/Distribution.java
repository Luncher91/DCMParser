package net.alenzen.dcm;

import java.io.IOException;
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

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		p.writeln("STUETZSTELLENVERTEILUNG", this.getName(), Integer.toString(sizeX));

		p.indent();
		super.writeTo(p);
		p.writeln("EINHEIT_X", DcmWriter.toDcmString(unitX));
		p.writeln("ST/X", stx);
		p.dedent();

		p.writeEnd();
		p.writeln();
	}
}
