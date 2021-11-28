package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class TextValue extends Value {
	private String value;

	public TextValue(String asText) {
		this.value = asText;
	}

	public TextValue() {
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln(comments);
		dcmWriter.writeln("TEXT", DcmWriter.toDcmString(value));
	}

	public static <T extends Value> String[] listToElementArray(List<T> l) {
		if (l == null || l.size() == 0)
			return new String[0];

		String[] strings = new String[l.size() + 1];
		strings[0] = "TEXT";

		for (int i = 1; i < strings.length; i++) {
			strings[i] = DcmWriter.toDcmString(l.get(i - 1).toString());
		}

		return strings;
	}
}
