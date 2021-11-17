package net.alenzen.dcm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

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

	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		return toJson(false, false);
	}

	public String toMinimizedJson() throws JsonGenerationException, JsonMappingException, IOException {
		return toJson(true, false);
	}

	public String toJson(boolean excludeNull, boolean indent)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		if (excludeNull) {
			objectMapper.setSerializationInclusion(Include.NON_NULL);
		}

		if (indent) {
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}

		return objectMapper.writeValueAsString(this);
	}

	public static String generateJsonSchema() throws JsonProcessingException {
		return generateJsonSchema(false, false);
	}

	public static String generateJsonSchema(boolean excludeNull, boolean indent)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		if (excludeNull) {
			mapper.setSerializationInclusion(Include.NON_NULL);
		}

		if (indent) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}

		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		JsonSchema schema = schemaGen.generateSchema(DcmFile.class);
		String schemaString = mapper.writeValueAsString(schema);

		JsonNode node = mapper.readTree(schemaString);

		HashMap<String, String> idPathMap = new HashMap<String, String>();
		collectPathIds(node, "#", idPathMap);

		replaceReferences(node, idPathMap);
		removeNamespaces(node);

		return mapper.writeValueAsString(node);
	}
	
	public static DcmFile fromJson(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, DcmFile.class);
	}

	private static void replaceReferences(JsonNode node, HashMap<String, String> idPathMap) {
		if (node.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if (item.getValue().isTextual() && item.getKey().equals("$ref")) {
					String newRef = idPathMap.get(item.getValue().asText());
					ObjectNode nodeAsObject = (ObjectNode) node;
					nodeAsObject.put("$ref", newRef);
				} else {
					replaceReferences(item.getValue(), idPathMap);
				}
			}
		} else if (node.isArray()) {
			for (Iterator<JsonNode> iter = node.iterator(); iter.hasNext();) {
				JsonNode item = iter.next();
				replaceReferences(item, idPathMap);
			}
		}
	}

	private static void removeNamespaces(JsonNode node) {
		if (node.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if (item.getValue().isTextual() && item.getKey().equals("id")) {
					String newId = removeNamespace(item.getValue().asText());
					ObjectNode nodeAsObject = (ObjectNode) node;
					nodeAsObject.put("id", newId);
				} else {
					removeNamespaces(item.getValue());
				}
			}
		} else if (node.isArray()) {
			for (Iterator<JsonNode> iter = node.iterator(); iter.hasNext();) {
				JsonNode item = iter.next();
				removeNamespaces(item);
			}
		}
	}

	private static String removeNamespace(String asText) {
		String[] namespacePath = asText.split(":");
		if (namespacePath.length > 0) {
			return namespacePath[namespacePath.length - 1];
		} else {
			return asText;
		}
	}

	private static void collectPathIds(JsonNode schema, String currentPath, Map<String, String> map) {
		if (schema.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = schema.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if (item.getValue().isTextual() && item.getKey().equals("id")) {
					map.put(item.getValue().asText(), currentPath);
				} else {
					collectPathIds(item.getValue(), currentPath + "/" + item.getKey(), map);
				}
			}
		}
	}
}
