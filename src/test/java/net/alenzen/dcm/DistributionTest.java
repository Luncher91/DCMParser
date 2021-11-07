package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistributionTest {
	private DcmFile file;
	private Distribution distr;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		distr = file.getDistributions().get(0);

		assertNotNull(distr);
	}

	@Test
	void testName() {
		assertEquals("distribution1", distr.getName());
	}
	
	@Test
	void testSizeX() {
		assertEquals(2, distr.getSizeX());
	}

	@Test
	void testLongName() {
		assertEquals("STUETZSTELLENVERTEILUNG 1", distr.getLongName());
	}

	@Test
	void testDisplayName() {
		assertNull(distr.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertNull(distr.getVariantValues());
	}

	@Test
	void testFunction() {
		assertNull(distr.getFunction());
	}

	@Test
	void testUnitX() {
		assertEquals("1/pcs", distr.getUnitX());
	}

	@Test
	void testStx() {
		assertEquals(2, distr.getStx().size());
		assertEquals(new BigDecimal("3"), distr.getStx().get(0));
		assertEquals(new BigDecimal("4"), distr.getStx().get(1));
	}
}
