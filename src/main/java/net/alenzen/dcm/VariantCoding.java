package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class VariantCoding implements IDcmWritable {
	private List<String> comments;
	private List<Criterion> criteria;
	private List<String> endComments;

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getEndComments() {
		return endComments;
	}

	public void setEndComments(List<String> endComments) {
		this.endComments = endComments;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln(comments);
		dcmWriter.writeln("VARIANTENKODIERUNG");

		dcmWriter.indent();
		dcmWriter.write(criteria);
		dcmWriter.dedent();

		dcmWriter.writeln(endComments);
		dcmWriter.writeEnd();
		dcmWriter.writeln();
	}
}
