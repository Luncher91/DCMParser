package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

public class DcmFileTest {

	public static DcmFile getTestFile(TestFile tf) throws IOException {
		DcmParser parser = new DcmParser(ClassLoader.getSystemResourceAsStream(tf.getFilename()));
		parser.setEventHandler((line, position, message) -> {
			fail("Line " + line + "@" + position + ": " + message);
		});
		return parser.parse();
	}

}
