package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class Parameter extends BasicSyntaxElement {
	private List<String> unitWComments;
	private String unitW;
	private Value value;

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	public List<String> getUnitWComments() {
		return unitWComments;
	}

	public void setUnitWComments(List<String> unitWComments) {
		this.unitWComments = unitWComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeBeginning(p, "FESTWERT");
		
		p.indent();
		super.writeBodyTo(p);
		p.writeln(unitWComments);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		if(value != null) value.writeTo(p);
		p.dedent();
		
		super.writeEnd(p);
	}
}
