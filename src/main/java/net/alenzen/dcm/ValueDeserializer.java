package net.alenzen.dcm;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ValueDeserializer extends StdDeserializer<Value> {
	private static final long serialVersionUID = 3668737155220498523L;

	public ValueDeserializer() {
		this(null);
	}

	protected ValueDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Value deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonToken t = p.getCurrentToken();
		if (t == JsonToken.START_OBJECT) {
			t = p.nextToken();
		}

		// "value"
		if (t == JsonToken.FIELD_NAME) {
			ArrayList<String> comments = null;
			String fieldname = p.getValueAsString();
			t = p.nextToken();
			
			if (fieldname.equals("comments")) {
				if (t == JsonToken.START_ARRAY) {
					t = p.nextToken();
					comments = new ArrayList<String>();

					while (t != JsonToken.END_ARRAY) {
						comments.add(p.getValueAsString());
						t = p.nextToken();
					}
				} 
				
				t = p.nextToken();
				
				if(t == JsonToken.FIELD_NAME) {
					fieldname = p.getValueAsString();
					t = p.nextToken();
				}
			}

			if (fieldname.equals("value")) {
				if (t == JsonToken.VALUE_NUMBER_FLOAT || t == JsonToken.VALUE_NUMBER_INT) {
					NumberValue ret = new NumberValue(p.getValueAsString());
					ret.setComments(comments);
					p.nextToken();
					return ret;
				}

				if (t == JsonToken.VALUE_FALSE) {
					BooleanValue ret = new BooleanValue(false);
					ret.setComments(comments);
					p.nextToken();
					return ret;
				}

				if (t == JsonToken.VALUE_TRUE) {
					BooleanValue ret = new BooleanValue(true);
					ret.setComments(comments);
					p.nextToken();
					return ret;
				}

				if (t == JsonToken.VALUE_STRING) {
					TextValue ret = new TextValue(p.getValueAsString());
					ret.setComments(comments);
					p.nextToken();
					return ret;
				}
			}
		}

		ctxt.handleMissingInstantiator(Value.class, getValueInstantiator(), p, "Cannot determine class for Value",
				p.getCurrentLocation());
		return null;
	}

}
