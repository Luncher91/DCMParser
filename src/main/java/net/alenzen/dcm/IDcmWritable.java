package net.alenzen.dcm;

import java.io.IOException;

public interface IDcmWritable {

	void writeTo(DcmWriter dcmWriter) throws IOException;

}
