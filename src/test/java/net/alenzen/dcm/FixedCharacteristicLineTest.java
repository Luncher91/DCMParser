package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedCharacteristicLineTest {
	private DcmFile file;
	private FixedCharacteristicLine line;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		line = file.getFixedCharacteristicLines().get(0);

		assertNotNull(line);
	}

	@Test
	void testName() {
		assertEquals("fixedCharLine1", line.getName());
	}
	
	@Test
	void testSizeX() {
		assertEquals(2, line.getSizeX());
	}

	@Test
	void testLongName() {
		assertEquals("FESTKENNLINIE 1", line.getLongName());
	}

	@Test
	void testDisplayName() {
		assertNull(line.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertNull(line.getVariantValues());
	}

	@Test
	void testFunction() {
		assertNull(line.getFunction());
	}

	@Test
	void testUnitX() {
		assertEquals("1/pcs", line.getUnitX());
	}
	
	@Test
	void testUnitW() {
		assertEquals("pcs", line.getUnitW());
	}

	@Test
	void testStx() {
		assertEquals(2, line.getStx().size());
		assertEquals(new BigDecimal("3"), line.getStx().get(0));
		assertEquals(new BigDecimal("4"), line.getStx().get(1));
	}
	
	@Test
	void testValues() {
		assertEquals(2, line.getValues().size());
		assertEquals(new BigDecimal("1"), line.getValues().get(0));
		assertEquals(new BigDecimal("2"), line.getValues().get(1));
	}
	
	@Test
	void testTextValues() {
		CharacteristicLine line = file.getCharacteristicLines().get(1);
		assertEquals(2, line.getValues().size());
		assertEquals("1", line.getValues().get(0).toString());
		assertEquals("2", line.getValues().get(1).toString());
	}
}
