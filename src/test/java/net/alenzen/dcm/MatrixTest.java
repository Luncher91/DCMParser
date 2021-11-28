package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
	private DcmFile file;
	private Matrix matrix;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		matrix = file.getMatrices().get(0);

		assertNotNull(matrix);
	}
	
	@Test
	void testName() {
		assertEquals("matrix1", matrix.getName());
	}
	
	@Test
	void testSizeX() {
		assertEquals(2, matrix.getSizeX());
	}
	
	@Test
	void testSizeY() {
		assertEquals(3, matrix.getSizeY());
	}

	@Test
	void testLongName() {
		assertEquals("FESTWERTBLOCK 3", matrix.getLongName());
	}

	@Test
	void testDisplayName() {
		assertNull(matrix.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertNull(matrix.getVariantValues());
	}

	@Test
	void testFunction() {
		assertNull(matrix.getFunction());
	}

	@Test
	void testUnitW() {
		assertEquals("pcs", matrix.getUnitW());
	}

	@Test
	void testValues() {
		assertEquals(3, matrix.getValues().size());
		assertEquals(2, matrix.getValues().get(0).size());
		assertEquals(2, matrix.getValues().get(1).size());
		assertEquals(2, matrix.getValues().get(2).size());
		
		// row 1
		assertEquals(new NumberValue("11", "* comment 12"), matrix.getValues().get(0).get(0));
		assertEquals(new NumberValue("12"), matrix.getValues().get(0).get(1));
		
		// row 2
		assertEquals(new NumberValue("21", "* comment 12"), matrix.getValues().get(1).get(0));
		assertEquals(new NumberValue("22"), matrix.getValues().get(1).get(1));
		
		// row 3
		assertEquals(new NumberValue("31"), matrix.getValues().get(2).get(0));
		assertEquals(new NumberValue("32"), matrix.getValues().get(2).get(1));
	}
	
	@Test
	void testTextValues() {
		Matrix matrix = file.getMatrices().get(1);
		assertEquals(3, matrix.getValues().size());
		assertEquals(2, matrix.getValues().get(0).size());
		assertEquals(2, matrix.getValues().get(1).size());
		assertEquals(2, matrix.getValues().get(2).size());
		
		// row 1
		assertEquals("11", matrix.getValues().get(0).get(0).toString());
		assertEquals("12", matrix.getValues().get(0).get(1).toString());
		
		// row 2
		assertEquals("21", matrix.getValues().get(1).get(0).toString());
		assertEquals("22", matrix.getValues().get(1).get(1).toString());
		
		// row 3
		assertEquals("31", matrix.getValues().get(2).get(0).toString());
		assertEquals("32", matrix.getValues().get(2).get(1).toString());
	}
	
	@Test
	void testComments() {
		assertEquals("* comment 12", matrix.getComments().get(0));
		assertEquals("* comment 12", matrix.getEndComments().get(0));
	}
}
