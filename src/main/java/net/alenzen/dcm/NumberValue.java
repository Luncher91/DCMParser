package net.alenzen.dcm;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class NumberValue extends BigDecimal implements IValue {
	private static final long serialVersionUID = -2988465617491331558L;

	public NumberValue(BigInteger unscaledVal, int scale, MathContext mc) {
		super(unscaledVal, scale, mc);
	}

	public NumberValue(BigInteger unscaledVal, int scale) {
		super(unscaledVal, scale);
	}

	public NumberValue(BigInteger val, MathContext mc) {
		super(val, mc);
	}

	public NumberValue(BigInteger val) {
		super(val);
	}

	public NumberValue(char[] in, int offset, int len, MathContext mc) {
		super(in, offset, len, mc);
	}

	public NumberValue(char[] in, int offset, int len) {
		super(in, offset, len);
	}

	public NumberValue(char[] in, MathContext mc) {
		super(in, mc);
	}

	public NumberValue(char[] in) {
		super(in);
	}

	public NumberValue(double val, MathContext mc) {
		super(val, mc);
	}

	public NumberValue(double val) {
		super(val);
	}

	public NumberValue(int val, MathContext mc) {
		super(val, mc);
	}

	public NumberValue(int val) {
		super(val);
	}

	public NumberValue(long val, MathContext mc) {
		super(val, mc);
	}

	public NumberValue(long val) {
		super(val);
	}

	public NumberValue(String val, MathContext mc) {
		super(val, mc);
	}

	public NumberValue(String val) {
		super(val);
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("WERT", this.toString());
	}
}
