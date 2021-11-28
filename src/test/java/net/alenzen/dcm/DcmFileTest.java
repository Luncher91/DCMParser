package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DcmFileTest {

	private static final String TESTFILE_A_JSON = "{\"comments\":[\"!comment 1\",\"*comment 2\",\".comment 3\"],\"functionGroups\":[{\"comments\":[\"* comment number one\",\"* second line\"],\"functions\":[{\"comments\":[\"* comment\"],\"name\":\"nameOfTheFirstFunction\",\"version\":\"version 42\",\"longname\":\"long----name\"},{\"comments\":[\"* comment\"],\"name\":\"nameOfTheSecondFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"},{\"comments\":null,\"name\":\"nameOfTheFifthFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"}],\"endComments\":[\"* comment\"]},{\"comments\":null,\"functions\":[{\"comments\":null,\"name\":\"nameOfTheThirdFunction\",\"version\":\"version 42\",\"longname\":\"long----name\"},{\"comments\":null,\"name\":\"nameOfTheFourthFunction\",\"version\":\"12.4\",\"longname\":\"longerrrrrrrrrr name\"}],\"endComments\":null}],\"variantCodings\":[{\"comments\":[\"* comment\",\"* comment\"],\"criteria\":[{\"comments\":[\"* comment\"],\"name\":\"bed1\",\"values\":[{\"comments\":null,\"value\":1},{\"comments\":null,\"value\":2.0},{\"comments\":null,\"value\":0.3},{\"comments\":null,\"value\":4E+5},{\"comments\":null,\"value\":-7},{\"comments\":null,\"value\":true},{\"comments\":null,\"value\":false}]},{\"comments\":null,\"name\":\"criterion2\",\"values\":[{\"comments\":null,\"value\":1},{\"comments\":null,\"value\":2.0},{\"comments\":null,\"value\":0.3},{\"comments\":null,\"value\":4E+5},{\"comments\":null,\"value\":-7},{\"comments\":null,\"value\":true},{\"comments\":null,\"value\":false}]}],\"endComments\":[\"* comment\"]}],\"moduleHeaders\":[{\"comments\":[\"* comment 1\"],\"text\":\"line 1\",\"name\":\"iAmTheHead\",\"lines\":[{\"comments\":[\"*comment 2\"],\"text\":\"line 2\"},{\"comments\":null,\"text\":\"line 3\"}]},{\"comments\":null,\"text\":\"line 1\",\"name\":\"iAmTheSecondHead\",\"lines\":[{\"comments\":null,\"text\":\"line 2\"}]}],\"parameters\":[{\"comments\":[\"* comment 12\"],\"name\":\"parameter1\",\"longNameComments\":[\"* comment 12\"],\"longName\":\"FESTWERT parameter 1\",\"displayNameComments\":[\"* comment 12\"],\"displayName\":\"asam_Parameter1\",\"variantValues\":[{\"comments\":[\"* comment 12\"],\"name\":\"var1\",\"value\":{\"comments\":null,\"value\":1.0}}],\"functionComments\":[\"* comment 12\"],\"function\":\"nameOfTheFirstFunction\",\"endComments\":[\"* comment 12\"],\"unitWComments\":[\"* comment 12\"],\"unitW\":\"m/s²\",\"value\":{\"comments\":[\"* comment 12\"],\"value\":7.123}},{\"comments\":null,\"name\":\"parameter2\",\"longNameComments\":null,\"longName\":\"FESTWERT parameter 2\",\"displayNameComments\":null,\"displayName\":\"asam_Parameter2\",\"variantValues\":[{\"comments\":null,\"name\":\"var1\",\"value\":{\"comments\":null,\"value\":3.0}}],\"functionComments\":null,\"function\":\"nameOfTheFirstFunction\",\"endComments\":null,\"unitWComments\":null,\"unitW\":\"m/s²\",\"value\":{\"comments\":[\"* comment 12\"],\"value\":\"text value\"}},{\"comments\":null,\"name\":\"parameter3\",\"longNameComments\":null,\"longName\":\"FESTWERT parameter 3\",\"displayNameComments\":null,\"displayName\":\"asam_Parameter3\",\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"unitWComments\":null,\"unitW\":\"boolean\",\"value\":{\"comments\":[\"* comment 12\"],\"value\":true}}],\"arrays\":[{\"comments\":[\"* comment 12\"],\"name\":\"array1\",\"longNameComments\":null,\"longName\":\"FESTWERTBLOCK 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"unitWComments\":null,\"unitW\":\"pcs\",\"values\":[{\"comments\":[\"* comment 12\"],\"value\":1},{\"comments\":null,\"value\":2}]},{\"comments\":null,\"name\":\"array2\",\"longNameComments\":null,\"longName\":\"FESTWERTBLOCK 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitWComments\":null,\"unitW\":\"pcs\",\"values\":[{\"comments\":[\"* comment 12\"],\"value\":\"1\"},{\"comments\":null,\"value\":\"2\"}]}],\"matrices\":[{\"comments\":[\"* comment 12\"],\"name\":\"matrix1\",\"longNameComments\":null,\"longName\":\"FESTWERTBLOCK 3\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"sizeY\":3,\"unitWComments\":null,\"unitW\":\"pcs\",\"values\":[[{\"comments\":[\"* comment 12\"],\"value\":11},{\"comments\":null,\"value\":12}],[{\"comments\":[\"* comment 12\"],\"value\":21},{\"comments\":null,\"value\":22}],[{\"comments\":null,\"value\":31},{\"comments\":null,\"value\":32}]]},{\"comments\":null,\"name\":\"matrix2\",\"longNameComments\":null,\"longName\":\"FESTWERTBLOCK 4\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"sizeY\":3,\"unitWComments\":null,\"unitW\":\"pcs\",\"values\":[[{\"comments\":[\"* comment 12\"],\"value\":\"11\"},{\"comments\":null,\"value\":\"12\"}],[{\"comments\":null,\"value\":\"21\"},{\"comments\":null,\"value\":\"22\"}],[{\"comments\":[\"* comment 12\"],\"value\":\"31\"},{\"comments\":null,\"value\":\"32\"}]]}],\"characteristicLines\":[{\"comments\":[\"* comment 12\"],\"name\":\"charLine1\",\"longNameComments\":null,\"longName\":\"KENNLINIE 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"unitXComments\":[\"* comment 12\"],\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":[\"* comment 12\"],\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":1},{\"comments\":null,\"value\":2}]},{\"comments\":null,\"name\":\"charLine2\",\"longNameComments\":null,\"longName\":\"KENNLINIE 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":\"1\"},{\"comments\":null,\"value\":\"2\"}]}],\"characteristicMaps\":[{\"comments\":[\"* comment 12\"],\"name\":\"map1\",\"longNameComments\":null,\"longName\":\"KENNFELD 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":[\"* comment 12\"],\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":[\"* comment 12\"],\"value\":5},{\"comments\":[\"* comment 12\"],\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":11},{\"comments\":null,\"value\":12}],[{\"comments\":null,\"value\":21},{\"comments\":null,\"value\":22}],[{\"comments\":null,\"value\":31},{\"comments\":null,\"value\":32}]]},{\"comments\":null,\"name\":\"map2\",\"longNameComments\":null,\"longName\":\"KENNFELD 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":null,\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":null,\"value\":5},{\"comments\":null,\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":\"11\"},{\"comments\":null,\"value\":\"12\"}],[{\"comments\":null,\"value\":\"21\"},{\"comments\":null,\"value\":\"22\"}],[{\"comments\":null,\"value\":\"31\"},{\"comments\":null,\"value\":\"32\"}]]}],\"fixedCharacteristicLines\":[{\"comments\":[\"* comment 12\"],\"name\":\"fixedCharLine1\",\"longNameComments\":null,\"longName\":\"FESTKENNLINIE 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":1},{\"comments\":null,\"value\":2}]},{\"comments\":null,\"name\":\"fixedCharLine2\",\"longNameComments\":null,\"longName\":\"FESTKENNLINIE 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":\"1\"},{\"comments\":null,\"value\":\"2\"}]}],\"fixedCharacteristicMaps\":[{\"comments\":[\"* comment 12\"],\"name\":\"fixedMap1\",\"longNameComments\":null,\"longName\":\"FESTKENNFELD 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":[\"* comment 12\"],\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":[\"* comment 12\"],\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":null,\"value\":5},{\"comments\":null,\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":11},{\"comments\":null,\"value\":12}],[{\"comments\":null,\"value\":21},{\"comments\":null,\"value\":22}],[{\"comments\":null,\"value\":31},{\"comments\":null,\"value\":32}]]},{\"comments\":null,\"name\":\"fixedMap2\",\"longNameComments\":null,\"longName\":\"FESTKENNFELD 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":null,\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":null,\"value\":5},{\"comments\":null,\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":\"11\"},{\"comments\":null,\"value\":\"12\"}],[{\"comments\":null,\"value\":\"21\"},{\"comments\":null,\"value\":\"22\"}],[{\"comments\":null,\"value\":\"31\"},{\"comments\":null,\"value\":\"32\"}]]}],\"groupCharacteristicLines\":[{\"comments\":null,\"name\":\"groupCharLine1\",\"longNameComments\":null,\"longName\":\"GRUPPENKENNLINIE 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":1},{\"comments\":null,\"value\":2}],\"sstx\":\"distr/bla hello\",\"sstxComments\":[\"* comment 12\"]},{\"comments\":null,\"name\":\"groupCharLine2\",\"longNameComments\":null,\"longName\":\"GRUPPENKENNLINIE 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"values\":[{\"comments\":null,\"value\":\"1\"},{\"comments\":null,\"value\":\"2\"}],\"sstx\":\"distr/bla hello\",\"sstxComments\":null}],\"groupCharacteristicMaps\":[{\"comments\":null,\"name\":\"groupMap1\",\"longNameComments\":null,\"longName\":\"GRUPPENKENNFELD 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":null,\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":null,\"value\":5},{\"comments\":null,\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":11},{\"comments\":null,\"value\":12}],[{\"comments\":null,\"value\":21},{\"comments\":null,\"value\":22}],[{\"comments\":null,\"value\":31},{\"comments\":null,\"value\":32}]],\"sstxComments\":null,\"sstx\":\"distr/bla hello\",\"sstyComments\":[\"* comment 12\"],\"ssty\":\"distr/bla hello2\"},{\"comments\":null,\"name\":\"groupMap2\",\"longNameComments\":null,\"longName\":\"GRUPPENKENNFELD 2\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"sizeY\":3,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"unitYComments\":null,\"unitY\":\"°C\",\"unitWComments\":null,\"unitW\":\"pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}],\"sty\":[{\"comments\":null,\"value\":5},{\"comments\":null,\"value\":6},{\"comments\":null,\"value\":7}],\"values\":[[{\"comments\":null,\"value\":\"11\"},{\"comments\":null,\"value\":\"12\"}],[{\"comments\":null,\"value\":\"21\"},{\"comments\":null,\"value\":\"22\"}],[{\"comments\":null,\"value\":\"31\"},{\"comments\":null,\"value\":\"32\"}]],\"sstxComments\":null,\"sstx\":\"distr/bla hello\",\"sstyComments\":null,\"ssty\":\"distr/bla hello2\"}],\"distributions\":[{\"comments\":null,\"name\":\"distribution1\",\"longNameComments\":null,\"longName\":\"STUETZSTELLENVERTEILUNG 1\",\"displayNameComments\":null,\"displayName\":null,\"variantValues\":null,\"functionComments\":null,\"function\":null,\"endComments\":null,\"sizeX\":2,\"unitXComments\":null,\"unitX\":\"1/pcs\",\"stx\":[{\"comments\":null,\"value\":3},{\"comments\":null,\"value\":4}]}]}";

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
	void testToDcmParsing() throws JsonGenerationException, JsonMappingException, IOException {
		DcmFile f = new DcmFile();
		String dcmStr = f.toDcm();
		
		DcmParser p = new DcmParser(new ByteArrayInputStream(dcmStr.getBytes()));
		DcmFile f2 = null;
		try {
			f2 = p.parse();
		} catch (IOException e) {
			fail(e);
		}
		
		assertNotNull(f2);
		assertEquals(f.toJson(), f2.toJson());
	}
	
	@Test
	void testToDcmParsingFullExample() throws IOException {
		DcmFile f = getTestFile(TestFile.A);
		String dcmStr = f.toDcm();
		
		DcmParser p = new DcmParser(new ByteArrayInputStream(dcmStr.getBytes(StandardCharsets.UTF_16)));
		DcmFile f2 = null;
		try {
			f2 = p.parse();
		} catch (IOException e) {
			fail(e);
		}
		
		assertNotNull(f2);
		assertEquals(f.toJson(), f2.toJson());
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
