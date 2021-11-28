package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Distribution extends BasicSyntaxElement {
	private int sizeX;
	private List<String> unitXComments;
	private String unitX;
	private List<Value> stx;

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

	public List<Value> getStx() {
		return stx;
	}

	public void setStx(List<Value> stx) {
		this.stx = stx;
	}

	public List<String> getUnitXComments() {
		return unitXComments;
	}

	public void setUnitXComments(List<String> unitXComments) {
		this.unitXComments = unitXComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeBeginning(p, "STUETZSTELLENVERTEILUNG", Integer.toString(sizeX));

		p.indent();
		super.writeBodyTo(p);
		p.writeln(unitXComments);
		p.writeln("EINHEIT_X", DcmWriter.toDcmString(unitX));
		p.writeln("ST/X", stx);
		p.dedent();

		super.writeEnd(p);
	}
}
