package net.alenzen.dcm;

import java.io.IOException;

public class FixedCharacteristicMap extends CharacteristicMap {
	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "FESTKENNFELD");
	}
}
