package net.alenzen.dcm;

import java.io.IOException;

public class Parameter extends BasicSyntaxElement {
	private String unitW;
	private IValue value;

	public String getUnitW() {
		return unitW;
	}

	public void setUnitW(String unitW) {
		this.unitW = unitW;
	}

	public IValue getValue() {
		return value;
	}

	public void setValue(IValue value) {
		this.value = value;
	}
	
	@Override
	public void writeTo(DcmWriter p) throws IOException {
		p.writeln("FESTWERT", this.getName());
		
		p.indent();
		super.writeTo(p);
		p.writeln("EINHEIT_W", DcmWriter.toDcmString(unitW));
		if(value != null) value.writeTo(p);
		p.dedent();
		
		p.writeEnd();
		p.writeln();
	}
}
