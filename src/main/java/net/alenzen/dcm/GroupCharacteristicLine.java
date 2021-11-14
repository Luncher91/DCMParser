package net.alenzen.dcm;

import java.io.IOException;

public class GroupCharacteristicLine extends CharacteristicLine {
	private String sstx;

	public String getSstx() {
		return sstx;
	}

	public void setSstx(String sstx) {
		this.sstx = sstx;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "GRUPPENKENNLINIE");
	}

	@Override
	protected void writeToAfterUnits(DcmWriter p) throws IOException {
		p.writeln("*SSTX", sstx);
	}
}
