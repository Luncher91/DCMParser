package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class DcmFileTest {

	public static DcmFile getTestFile(TestFile tf) throws IOException {
		DcmParser parser = new DcmParser(ClassLoader.getSystemResourceAsStream(tf.getFilename()));
		parser.setEventHandler((line, position, message) -> {
			fail("Line " + line + "@" + position + ": " + message);
		});
		return parser.parse();
	}

	@Test
	void testToDcm() {
		DcmFile f = new DcmFile();
		String dcmStr = f.toDcm();
		assertEquals("KONSERVIERUNG_FORMAT 2.0", dcmStr.trim());
	}
	
	@Test
	void testToDcmParsing() {
		DcmFile f = new DcmFile();
		String dcmStr = f.toDcm();
		
		DcmParser p = new DcmParser(new ByteArrayInputStream(dcmStr.getBytes()));
		try {
			f = p.parse();
		} catch (IOException e) {
			fail(e);
		}
		
		assertNotNull(f);
	}
	
	@Test
	void testToDcmParsingFullExample() throws IOException {
		DcmFile f = getTestFile(TestFile.A);
		String dcmStr = f.toDcm();
		
		DcmParser p = new DcmParser(new ByteArrayInputStream(dcmStr.getBytes()));
		try {
			f = p.parse();
		} catch (IOException e) {
			fail(e);
		}
		
		assertNotNull(f);
	}
}
