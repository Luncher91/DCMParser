package net.alenzen.dcm;

import java.io.IOException;
import java.util.List;

public class FunctionGroup implements IDcmWritable {
	private List<Function> functions;

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	@Override
	public void writeTo(DcmWriter dcmWriter) throws IOException {
		dcmWriter.writeln("FUNKTIONEN");
		
		dcmWriter.indent();
		dcmWriter.write(functions);
		dcmWriter.dedent();
		
		dcmWriter.writeEnd();
		dcmWriter.writeln();
	}
}
