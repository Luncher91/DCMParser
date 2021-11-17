package net.alenzen.dcm;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class IValueDeserializer extends StdDeserializer<IValue> {
	private static final long serialVersionUID = 3668737155220498523L;

	public IValueDeserializer() {
		this(null);
	}

	protected IValueDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public IValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonToken t = p.getCurrentToken();
		if (t == JsonToken.START_OBJECT) {
			t = p.nextToken();
		}

		if (t == JsonToken.VALUE_NUMBER_FLOAT || t == JsonToken.VALUE_NUMBER_INT) {
			return new NumberValue(p.getValueAsString());
		}

		// "value"
		if (t == JsonToken.FIELD_NAME) {
			t = p.nextToken();
			if (t == JsonToken.VALUE_FALSE) {
				BooleanValue ret = new BooleanValue(false);
				p.nextToken();
				return ret;
			}

			if (t == JsonToken.VALUE_TRUE) {
				BooleanValue ret = new BooleanValue(true);
				p.nextToken();
				return ret;
			}

			if (t == JsonToken.VALUE_STRING) {
				TextValue ret = new TextValue(p.getValueAsString());
				p.nextToken();
				return ret;
			}
		}

		ctxt.handleMissingInstantiator(IValue.class, getValueInstantiator(), p, "Cannot determine class for IValue", p.getCurrentLocation());
		return null;
	}

}
