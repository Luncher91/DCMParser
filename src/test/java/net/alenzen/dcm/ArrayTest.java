package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayTest {
	private DcmFile file;
	private Array array;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		array = file.getArrays().get(0);

		assertNotNull(array);
	}

	@Test
	void testName() {
		assertEquals("array1", array.getName());
	}
	
	@Test
	void testSizeX() {
		assertEquals(2, array.getSizeX());
	}

	@Test
	void testLongName() {
		assertEquals("FESTWERTBLOCK 1", array.getLongName());
	}

	@Test
	void testDisplayName() {
		assertNull(array.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertNull(array.getVariantValues());
	}

	@Test
	void testFunction() {
		assertNull(array.getFunction());
	}

	void testUnitW() {
		assertEquals("pcs", array.getUnitW());
	}

	@Test
	void testValues() {
		assertEquals(2, array.getValues().size());
		assertEquals(new NumberValue("1", "* comment 12"), array.getValues().get(0));
		assertEquals(new NumberValue("2"), array.getValues().get(1));
	}
	
	@Test
	void testTextValues() {
		Array array = file.getArrays().get(1);
		assertEquals(2, array.getValues().size());
		assertTrue(array.getValues().get(0) instanceof TextValue);
		assertTrue(array.getValues().get(1) instanceof TextValue);
		assertEquals("1", array.getValues().get(0).toString());
		assertEquals("2", array.getValues().get(1).toString());
	}
	
	@Test
	void testComments() {
		assertEquals("* comment 12", array.getComments().get(0));
		assertEquals("* comment 12", array.getEndComments().get(0));
	}
}
