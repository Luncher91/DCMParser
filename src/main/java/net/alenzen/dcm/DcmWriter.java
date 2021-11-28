package net.alenzen.dcm;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DcmWriter {
	private int indentationDepth = 0;
	private String indentationString = "    ";
	private String lineBreak = "\n";
	private Charset charset = StandardCharsets.UTF_8;
	private OutputStream os;

	public DcmWriter(OutputStream sb) {
		this.os = sb;
	}

	public DcmWriter(OutputStream sb, int depth, String indentString, String lineBreak, Charset charset) {
		this.os = sb;
		this.indentationDepth = depth;
		this.indentationString = indentString;
		this.setLineBreak(lineBreak);
		this.setCharset(charset);
	}

	public void writeExpression(String name, String value) throws IOException {
		if (value != null && !value.trim().isEmpty()) {
			writeIndentation();
			write(name);
			write(" ");
			write(value);
			writeln();
		}
	}

	public void writeExpressionString(String name, String value) throws IOException {
		if (value != null) {
			writeIndentation();
			write(name);
			write(" ");
			write(toDcmString(value));
			writeln();
		}
	}

	public static String toDcmString(String s) {
		return "\"" + escapeStringCharacters(s) + "\"";
	}

	public static String escapeStringCharacters(String str) {
		String replacedStr = str;
		replacedStr = replacedStr.replaceAll("\\\\", "\\\\\\\\");
		// escape "
		replacedStr = replacedStr.replaceAll("\"", "\\\\\"");

		// escape '
		replacedStr = replacedStr.replaceAll("'", "\\\\'");

		// escape \n
		replacedStr = replacedStr.replaceAll("\n", "\\\\n");

		// escape \r
		replacedStr = replacedStr.replaceAll("\r", "\\\\r");

		// escape \t
		replacedStr = replacedStr.replaceAll("\t", "\\\\t");

		return replacedStr;
	}

	public void writeExpressionVariants(String name, List<VariantValue> varValues) throws IOException {
		if (varValues != null && !varValues.isEmpty()) {
			VariantValue first = varValues.get(0);
			if (first != null) {
				writeln(first.getComments());
			}

			writeIndentation();
			write(name);
			for (VariantValue v : varValues) {
				write(" ");
				write(v.getName());
				write("=");
				write(v.getValue().toString());
			}
			writeln();
		}
	}

	public void writeEnd() throws IOException {
		writeIndentation();
		write("END");
		writeln();
	}

	public void writeIndentation() throws IOException {
		for (int i = 0; i < indentationDepth; i++)
			write(indentationString);
	}

	public void writeln(String... strings) throws IOException {
		writeIndentation();
		write(String.join(" ", strings));
		writeln();
	}

	public <T extends Value> void writeln(String prefix, List<T> objects) throws IOException {
		if (!objects.isEmpty()) {
			T v = objects.get(0);
			if (v != null) {
				writeln(v.getComments());
			}
		}

		writeIndentation();
		write(prefix);
		for (Value o : objects) {
			write(" ");
			write(o.toString());
		}
		writeln();
	}

	private void write(String s) throws IOException {
		os.write(s.getBytes(getCharset()));
	}

	public void write(IDcmWritable e) throws IOException {
		e.writeTo(this);
	}

	public <T extends Value> void writeValues(List<T> l) throws IOException {
		if (l.stream().anyMatch(e -> e instanceof TextValue)) {
			if (!l.isEmpty()) {
				writeln(l.get(0).getComments());
			}

			writeln(TextValue.listToElementArray(l));
		} else {
			writeln("WERT", l);
		}
	}

	public <T extends IDcmWritable> void write(List<T> l) throws IOException {
		if (l == null)
			return;

		for (T e : l) {
			write(e);
		}
	}

	public int getIndentationDepth() {
		return indentationDepth;
	}

	public void setIndentationDepth(int depth) {
		this.indentationDepth = depth;
	}

	public String getIndentationString() {
		return indentationString;
	}

	public void setIndentationString(String indentationString) {
		this.indentationString = indentationString;
	}

	public void writeln() throws IOException {
		write(getLineBreak());
	}

	public void indent() {
		indentationDepth++;
	}

	public void dedent() {
		indentationDepth--;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public String getLineBreak() {
		return lineBreak;
	}

	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}

	public void writeln(List<String> comments) throws IOException {
		if (comments == null) {
			return;
		}

		for (String c : comments) {
			writeln(c);
		}
	}
}
