package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class GroupCharacteristicLine extends CharacteristicLine {
	private List<String> SstxComments;
	private String sstx;

	public String getSstx() {
		return sstx;
	}

	public void setSstx(String sstx) {
		this.sstx = sstx;
	}

	public List<String> getSstxComments() {
		return SstxComments;
	}

	public void setSstxComments(List<String> sstxComments) {
		SstxComments = sstxComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "GRUPPENKENNLINIE");
	}

	@Override
	protected void writeToAfterUnits(DcmWriter p) throws IOException {
		p.writeln(SstxComments);
		p.writeln("*SSTX", sstx);
	}
}
