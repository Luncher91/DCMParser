package net.alenzen.dcm;

import java.io.IOException;

public class GroupCharacteristicMap extends CharacteristicMap {
	private String sstx;
	private String ssty;

	public String getSstx() {
		return sstx;
	}

	public void setSstx(String sstx) {
		this.sstx = sstx;
	}

	public String getSsty() {
		return ssty;
	}

	public void setSsty(String ssty) {
		this.ssty = ssty;
	}
	
	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "GRUPPENKENNFELD");
	}
	
	@Override
	protected void writeToAfterUnits(DcmWriter p) throws IOException {
		p.writeln("*SSTX", sstx);
		p.writeln("*SSTY", ssty);
	}
}
