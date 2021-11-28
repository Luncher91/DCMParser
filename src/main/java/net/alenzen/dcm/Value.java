package net.alenzen.dcm;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ValueDeserializer.class)
public abstract class Value implements IDcmWritable {
	protected List<String> comments;
	
	public Value() {
	}
	
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	public abstract String toString();
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Value value = (Value) o;
		return Objects.equals(comments, value.comments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), comments);
	}
}
