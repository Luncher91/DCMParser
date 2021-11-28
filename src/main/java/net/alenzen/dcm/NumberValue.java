package net.alenzen.dcm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class NumberValue extends Value {
	private BigDecimal value;

	public NumberValue(String valueAsString, String... comments) {
		if (comments.length > 0) {
			this.setComments(Arrays.asList(comments));
		}
		this.value = new BigDecimal(valueAsString);
	}

	public NumberValue(int i) {
		this.value = new BigDecimal(i);
	}
	
	public NumberValue() {
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getValue().toString();
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln(comments);
		dcmWriter.writeln("WERT", this.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		NumberValue project = (NumberValue) o;
		return Objects.equals(value, project.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), value);
	}
}
