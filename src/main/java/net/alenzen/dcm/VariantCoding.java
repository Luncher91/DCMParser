package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class VariantCoding implements IDcmWritable {
	private List<Criterion> criteria;

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("VARIANTENKODIERUNG");
		
		dcmWriter.indent();
		dcmWriter.write(criteria);
		dcmWriter.dedent();
		
		dcmWriter.writeEnd();
		dcmWriter.writeln();
	}
}
