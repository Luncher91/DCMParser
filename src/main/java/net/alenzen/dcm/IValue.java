package net.alenzen.dcm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IValueDeserializer.class)
public interface IValue extends IDcmWritable {
	String toString();
}
