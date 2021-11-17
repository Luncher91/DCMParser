package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class DcmFileTest {

	private static final String TESTFILE_A_JSON = "{\"functionGroups\":[{\"functions\":[{\"name\":\"nameOfTheFirstFunction\",\"version\":\"version 42\",\"longname\":\"long----name\"},{\"name\":\"nameOfTheSecondFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"},{\"name\":\"nameOfTheFifthFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"}]},{\"functions\":[{\"name\":\"nameOfTheThirdFunction\",\"version\":\"version 42\",\"longname\":\"long----name\"},{\"name\":\"nameOfTheFourthFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"}]}],\"variantCodings\":[{\"criteria\":[{\"name\":\"bed1\",\"values\":[1,2.0,0.3,4E+5,-7,{\"value\":true},{\"value\":false}]},{\"name\":\"criterion2\",\"values\":[1,2.0,0.3,4E+5,-7,{\"value\":true},{\"value\":false}]}]}],\"moduleHeaders\":[{\"name\":\"iAmTheHead\",\"text\":[\"line 1\",\"line 2\",\"line 3\"]},{\"name\":\"iAmTheSecondHead\",\"text\":[\"line 1\",\"line 2\"]}],\"parameters\":[{\"name\":\"parameter1\",\"longName\":\"FESTWERT parameter 1\",\"displayName\":\"asam_Parameter1\",\"variantValues\":[{\"name\":\"var1\",\"value\":1.0}],\"function\":\"nameOfTheFirstFunction\",\"unitW\":\"m/s²\",\"value\":7.123},{\"name\":\"parameter2\",\"longName\":\"FESTWERT parameter 2\",\"displayName\":\"asam_Parameter2\",\"variantValues\":[{\"name\":\"var1\",\"value\":3.0}],\"function\":\"nameOfTheFirstFunction\",\"unitW\":\"m/s²\",\"value\":{\"value\":\"text value\"}},{\"name\":\"parameter3\",\"longName\":\"FESTWERT parameter 3\",\"displayName\":\"asam_Parameter3\",\"variantValues\":null,\"function\":null,\"unitW\":\"boolean\",\"value\":{\"value\":true}}],\"arrays\":[{\"name\":\"array1\",\"longName\":\"FESTWERTBLOCK 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitW\":\"pcs\",\"values\":[1,2]},{\"name\":\"array2\",\"longName\":\"FESTWERTBLOCK 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitW\":\"pcs\",\"values\":[{\"value\":\"1\"},{\"value\":\"2\"}]}],\"matrices\":[{\"name\":\"matrix1\",\"longName\":\"FESTWERTBLOCK 3\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitW\":\"pcs\",\"values\":[[11,12],[21,22],[31,32]]},{\"name\":\"matrix2\",\"longName\":\"FESTWERTBLOCK 4\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitW\":\"pcs\",\"values\":[[{\"value\":\"11\"},{\"value\":\"12\"}],[{\"value\":\"21\"},{\"value\":\"22\"}],[{\"value\":\"31\"},{\"value\":\"32\"}]]}],\"characteristicLines\":[{\"name\":\"charLine1\",\"longName\":\"KENNLINIE 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[1,2]},{\"name\":\"charLine2\",\"longName\":\"KENNLINIE 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[{\"value\":\"1\"},{\"value\":\"2\"}]}],\"characteristicMaps\":[{\"name\":\"map1\",\"longName\":\"KENNFELD 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[11,12],[21,22],[31,32]]},{\"name\":\"map2\",\"longName\":\"KENNFELD 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[{\"value\":\"11\"},{\"value\":\"12\"}],[{\"value\":\"21\"},{\"value\":\"22\"}],[{\"value\":\"31\"},{\"value\":\"32\"}]]}],\"fixedCharacteristicLines\":[{\"name\":\"fixedCharLine1\",\"longName\":\"FESTKENNLINIE 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[1,2]},{\"name\":\"fixedCharLine2\",\"longName\":\"FESTKENNLINIE 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[{\"value\":\"1\"},{\"value\":\"2\"}]}],\"fixedCharacteristicMaps\":[{\"name\":\"fixedMap1\",\"longName\":\"FESTKENNFELD 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[11,12],[21,22],[31,32]]},{\"name\":\"fixedMap2\",\"longName\":\"FESTKENNFELD 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[{\"value\":\"11\"},{\"value\":\"12\"}],[{\"value\":\"21\"},{\"value\":\"22\"}],[{\"value\":\"31\"},{\"value\":\"32\"}]]}],\"groupCharacteristicLines\":[{\"name\":\"groupCharLine1\",\"longName\":\"GRUPPENKENNLINIE 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[1,2],\"sstx\":\"distr/bla hello\"},{\"name\":\"groupCharLine2\",\"longName\":\"GRUPPENKENNLINIE 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"unitW\":\"pcs\",\"stx\":[3,4],\"values\":[{\"value\":\"1\"},{\"value\":\"2\"}],\"sstx\":\"distr/bla hello\"}],\"groupCharacteristicMaps\":[{\"name\":\"groupMap1\",\"longName\":\"GRUPPENKENNFELD 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[11,12],[21,22],[31,32]],\"sstx\":\"distr/bla hello\",\"ssty\":\"distr/bla hello2\"},{\"name\":\"groupMap2\",\"longName\":\"GRUPPENKENNFELD 2\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"sizeY\":3,\"unitX\":\"1/pcs\",\"unitY\":\"°C\",\"unitW\":\"pcs\",\"stx\":[3,4],\"sty\":[5,6,7],\"values\":[[{\"value\":\"11\"},{\"value\":\"12\"}],[{\"value\":\"21\"},{\"value\":\"22\"}],[{\"value\":\"31\"},{\"value\":\"32\"}]],\"sstx\":\"distr/bla hello\",\"ssty\":\"distr/bla hello2\"}],\"distributions\":[{\"name\":\"distribution1\",\"longName\":\"STUETZSTELLENVERTEILUNG 1\",\"displayName\":null,\"variantValues\":null,\"function\":null,\"sizeX\":2,\"unitX\":\"1/pcs\",\"stx\":[3,4]}]}";

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
	
	@Test
	void testFromJson() throws IOException {
		DcmFile fromJson = DcmFile.fromJson(TESTFILE_A_JSON);
		DcmFile fromDcm = getTestFile(TestFile.A);
		
		assertEquals(fromDcm.toJson(), fromJson.toJson());
	}
	
	@Test
	void testToJson() throws IOException {
		DcmFile d = getTestFile(TestFile.A);
		assertEquals(TESTFILE_A_JSON, d.toJson());
	}
}
