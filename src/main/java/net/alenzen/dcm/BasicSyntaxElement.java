package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public abstract class BasicSyntaxElement implements IDcmWritable {
	private List<String> comments;
	private String name;
	private List<String> longNameComments;
	private String longName;
	private List<String> displayNameComments;
	private String displayName;
	private List<VariantValue> variantValues;
	private List<String> functionComments;
	private String function;
	private List<String> endComments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<VariantValue> getVariantValues() {
		return variantValues;
	}

	public void setVariantValues(List<VariantValue> variantValues) {
		this.variantValues = variantValues;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getLongNameComments() {
		return longNameComments;
	}

	public void setLongNameComments(List<String> longNameComments) {
		this.longNameComments = longNameComments;
	}

	public List<String> getDisplayNameComments() {
		return displayNameComments;
	}

	public void setDisplayNameComments(List<String> displayNameComments) {
		this.displayNameComments = displayNameComments;
	}

	public List<String> getFunctionComments() {
		return functionComments;
	}

	public void setFunctionComments(List<String> functionComments) {
		this.functionComments = functionComments;
	}

	public List<String> getEndComments() {
		return endComments;
	}

	public void setEndComments(List<String> endComments) {
		this.endComments = endComments;
	}

	public void writeBodyTo(DcmWriter p) throws IOException {
		p.writeln(longNameComments);
		p.writeExpressionString("LANGNAME", longName);
		p.writeln(displayNameComments);
		p.writeExpression("DISPLAYNAME", displayName);
		p.writeExpressionVariants("VAR", variantValues);
		p.writeln(functionComments);
		p.writeExpression("FUNKTION", function);
	}

	public void writeBeginning(DcmWriter p, String type, String... s) throws IOException {
		p.writeln(comments);
		
		String[] arr = new String[s.length + 2];
		arr[0] = type;
		arr[1] = this.name;
		for (int i = 0; i < s.length; i++) {
			arr[i + 2] = s[i];
		}
		
		p.writeln(arr);
	}

	public void writeEnd(DcmWriter p) throws IOException {
		p.writeln(endComments);
		p.writeEnd();
		p.writeln();
	}
}
