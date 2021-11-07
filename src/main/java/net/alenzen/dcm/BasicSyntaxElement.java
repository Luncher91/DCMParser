package net.alenzen.dcm;

import java.util.List;

public abstract class BasicSyntaxElement {
	private String name;
	private String longName;
	private String displayName;
	private List<VariantValue> variantValues;
	private String function;

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
}
