package net.alenzen.dcm;

import java.io.IOException;

public class FixedCharacteristicLine extends CharacteristicLine {
	@Override
	public void writeTo(DcmWriter p) throws IOException {
		super.writeTo(p, "FESTKENNLINIE");
	}
}
