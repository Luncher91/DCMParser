package net.alenzen.dcm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DcmFile {
	private List<FunctionGroup> functionGroups;
	private List<VariantCoding> variantCodings;
	private List<ModuleHeader> moduleHeaders;
	private List<Parameter> parameters;
	private List<Array> arrays;
	private List<Matrix> matrices;
	private List<CharacteristicLine> characteristicLines;
	private List<CharacteristicMap> characteristicMaps;
	private List<FixedCharacteristicLine> fixedCharacteristicLines;
	private List<FixedCharacteristicMap> fixedCharacteristicMaps;
	private List<GroupCharacteristicLine> groupCharacteristicLines;
	private List<GroupCharacteristicMap> groupCharacteristicMaps;
	private List<Distribution> distributions;

	public List<FunctionGroup> getFunctionGroups() {
		return functionGroups;
	}

	public void setFunctionGroups(List<FunctionGroup> functionGroups) {
		this.functionGroups = functionGroups;
	}

	public List<VariantCoding> getVariantCodings() {
		return variantCodings;
	}

	public void setVariantCodings(List<VariantCoding> variantCodings) {
		this.variantCodings = variantCodings;
	}

	public List<ModuleHeader> getModuleHeaders() {
		return moduleHeaders;
	}

	public void setModuleHeaders(List<ModuleHeader> moduleHeaders) {
		this.moduleHeaders = moduleHeaders;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Array> getArrays() {
		return arrays;
	}

	public void setArrays(List<Array> arrays) {
		this.arrays = arrays;
	}

	public List<Matrix> getMatrices() {
		return matrices;
	}

	public void setMatrices(List<Matrix> matrices) {
		this.matrices = matrices;
	}

	public List<CharacteristicLine> getCharacteristicLines() {
		return characteristicLines;
	}

	public void setCharacteristicLines(List<CharacteristicLine> characteristicLines) {
		this.characteristicLines = characteristicLines;
	}

	public List<CharacteristicMap> getCharacteristicMaps() {
		return characteristicMaps;
	}

	public void setCharacteristicMaps(List<CharacteristicMap> characteristicMaps) {
		this.characteristicMaps = characteristicMaps;
	}

	public List<FixedCharacteristicLine> getFixedCharacteristicLines() {
		return fixedCharacteristicLines;
	}

	public void setFixedCharacteristicLines(List<FixedCharacteristicLine> fixedCharacteristicLines) {
		this.fixedCharacteristicLines = fixedCharacteristicLines;
	}

	public List<FixedCharacteristicMap> getFixedCharacteristicMaps() {
		return fixedCharacteristicMaps;
	}

	public void setFixedCharacteristicMaps(List<FixedCharacteristicMap> fixedCharacteristicMaps) {
		this.fixedCharacteristicMaps = fixedCharacteristicMaps;
	}

	public List<GroupCharacteristicLine> getGroupCharacteristicLines() {
		return groupCharacteristicLines;
	}

	public void setGroupCharacteristicLines(List<GroupCharacteristicLine> groupCharacteristicLines) {
		this.groupCharacteristicLines = groupCharacteristicLines;
	}

	public List<GroupCharacteristicMap> getGroupCharacteristicMaps() {
		return groupCharacteristicMaps;
	}

	public void setGroupCharacteristicMaps(List<GroupCharacteristicMap> groupCharacteristicMaps) {
		this.groupCharacteristicMaps = groupCharacteristicMaps;
	}

	public List<Distribution> getDistributions() {
		return distributions;
	}

	public void setDistributions(List<Distribution> distributions) {
		this.distributions = distributions;
	}

	public void writeTo(DcmWriter w) throws IOException {
		w.writeln("KONSERVIERUNG_FORMAT 2.0");
		w.writeln();
		
		w.write(functionGroups);
		w.write(variantCodings);
		w.write(moduleHeaders);
		w.write(parameters);
		w.write(arrays);
		w.write(matrices);
		w.write(characteristicLines);
		w.write(characteristicMaps);
		w.write(fixedCharacteristicLines);
		w.write(fixedCharacteristicMaps);
		w.write(groupCharacteristicLines);
		w.write(groupCharacteristicMaps);
		w.write(distributions);
	}
	
	public String toDcm() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DcmWriter writer = new DcmWriter(bos);
		try {
			this.writeTo(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new String(bos.toByteArray(), writer.getCharset());
	}
}
