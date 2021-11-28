package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class FunctionGroup implements IDcmWritable {
	private List<String> comments;
	private List<Function> functions;
	private List<String> endComments;

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
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
		dcmWriter.writeln("FUNKTIONEN");
		
		dcmWriter.indent();
		dcmWriter.write(functions);
		dcmWriter.dedent();
		
		dcmWriter.writeln(endComments);
		dcmWriter.writeEnd();
		dcmWriter.writeln();
	}
}
