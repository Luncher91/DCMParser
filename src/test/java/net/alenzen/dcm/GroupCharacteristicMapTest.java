package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroupCharacteristicMapTest {
	private DcmFile file;
	private GroupCharacteristicMap m;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		m = file.getGroupCharacteristicMaps().get(0);

		assertNotNull(m);
	}
	
	@Test
	void testName() {
		assertEquals("groupMap1", m.getName());
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
		assertEquals("GRUPPENKENNFELD 1", m.getLongName());
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
	void testSstx() {
		assertEquals("distr/bla hello", m.getSstx());
	}
	
	@Test
	void testSsty() {
		assertEquals("distr/bla hello2", m.getSsty());
	}
	
	@Test
	void testStx() {
		assertEquals(2, m.getStx().size());
		assertEquals(new NumberValue("3"), m.getStx().get(0));
		assertEquals(new NumberValue("4"), m.getStx().get(1));
	}
	
	@Test
	void testSty() {
		assertEquals(3, m.getSty().size());
		assertEquals(new NumberValue("5"), m.getSty().get(0));
		assertEquals(new NumberValue("6"), m.getSty().get(1));
		assertEquals(new NumberValue("7"), m.getSty().get(2));
	}

	@Test
	void testValues() {
		assertEquals(3, m.getValues().size());
		assertEquals(2, m.getValues().get(0).size());
		assertEquals(2, m.getValues().get(1).size());
		assertEquals(2, m.getValues().get(2).size());
		
		// row 1
		assertEquals(new NumberValue("11"), m.getValues().get(0).get(0));
		assertEquals(new NumberValue("12"), m.getValues().get(0).get(1));
		
		// row 2
		assertEquals(new NumberValue("21"), m.getValues().get(1).get(0));
		assertEquals(new NumberValue("22"), m.getValues().get(1).get(1));
		
		// row 3
		assertEquals(new NumberValue("31"), m.getValues().get(2).get(0));
		assertEquals(new NumberValue("32"), m.getValues().get(2).get(1));
	}
	
	@Test
	void testTextValues() {
		GroupCharacteristicMap m = file.getGroupCharacteristicMaps().get(1);
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
	
	@Test
	void testComments() {
		assertEquals("* comment 12", m.getSstyComments().get(0));
	}
}
