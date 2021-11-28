package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class GroupCharacteristicMap extends CharacteristicMap {
	private List<String> sstxComments;
	private String sstx;
	private List<String> sstyComments;
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
	
	public List<String> getSstxComments() {
		return sstxComments;
	}

	public void setSstxComments(List<String> sstxComments) {
		this.sstxComments = sstxComments;
	}

	public List<String> getSstyComments() {
		return sstyComments;
	}

	public void setSstyComments(List<String> sstyComments) {
		this.sstyComments = sstyComments;
	}

	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "GRUPPENKENNFELD");
	}
	
	@Override
	protected void writeToAfterUnits(DcmWriter p) throws IOException {
		p.writeln(sstxComments);
		p.writeln("*SSTX", sstx);
		p.writeln(sstyComments);
		p.writeln("*SSTY", ssty);
	}
}
