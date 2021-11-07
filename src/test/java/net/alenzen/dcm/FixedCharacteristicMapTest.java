package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedCharacteristicMapTest {
	private DcmFile file;
	private FixedCharacteristicMap m;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		m = file.getFixedCharacteristicMaps().get(0);

		assertNotNull(m);
	}
	
	@Test
	void testName() {
		assertEquals("fixedMap1", m.getName());
	}
	
	@Test
	void testSizeX() {
		assertEquals(2, m.getSizeX());
	}
	
	@Test
	void testSizeY() {
		assertEquals(3, m.getSizeY());
	}

	@Test
	void testLongName() {
		assertEquals("FESTKENNFELD 1", m.getLongName());
	}

	@Test
	void testDisplayName() {
		assertNull(m.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertNull(m.getVariantValues());
	}

	@Test
	void testFunction() {
		assertNull(m.getFunction());
	}

	@Test
	void testUnitX() {
		assertEquals("1/pcs", m.getUnitX());
	}

	@Test
	void testUnitY() {
		assertEquals("°C", m.getUnitY());
	}
	
	@Test
	void testUnitW() {
		assertEquals("pcs", m.getUnitW());
	}
	
	@Test
	void testStx() {
		assertEquals(2, m.getStx().size());
		assertEquals(new BigDecimal("3"), m.getStx().get(0));
		assertEquals(new BigDecimal("4"), m.getStx().get(1));
	}
	
	@Test
	void testSty() {
		assertEquals(3, m.getSty().size());
		assertEquals(new BigDecimal("5"), m.getSty().get(0));
		assertEquals(new BigDecimal("6"), m.getSty().get(1));
		assertEquals(new BigDecimal("7"), m.getSty().get(2));
	}

	@Test
	void testValues() {
		assertEquals(3, m.getValues().size());
		assertEquals(2, m.getValues().get(0).size());
		assertEquals(2, m.getValues().get(1).size());
		assertEquals(2, m.getValues().get(2).size());
		
		// row 1
		assertEquals(new BigDecimal("11"), m.getValues().get(0).get(0));
		assertEquals(new BigDecimal("12"), m.getValues().get(0).get(1));
		
		// row 2
		assertEquals(new BigDecimal("21"), m.getValues().get(1).get(0));
		assertEquals(new BigDecimal("22"), m.getValues().get(1).get(1));
		
		// row 3
		assertEquals(new BigDecimal("31"), m.getValues().get(2).get(0));
		assertEquals(new BigDecimal("32"), m.getValues().get(2).get(1));
	}
	
	@Test
	void testTextValues() {
		FixedCharacteristicMap m = file.getFixedCharacteristicMaps().get(1);
		assertEquals(3, m.getValues().size());
		assertEquals(2, m.getValues().get(0).size());
		assertEquals(2, m.getValues().get(1).size());
		assertEquals(2, m.getValues().get(2).size());
		
		// row 1
		assertEquals("11", m.getValues().get(0).get(0).toString());
		assertEquals("12", m.getValues().get(0).get(1).toString());
		
		// row 2
		assertEquals("21", m.getValues().get(1).get(0).toString());
		assertEquals("22", m.getValues().get(1).get(1).toString());
		
		// row 3
		assertEquals("31", m.getValues().get(2).get(0).toString());
		assertEquals("32", m.getValues().get(2).get(1).toString());
	}
}