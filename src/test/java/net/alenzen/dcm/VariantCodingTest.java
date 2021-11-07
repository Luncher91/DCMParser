package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VariantCodingTest {
	private DcmFile file;
	private VariantCoding variantCoding;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		variantCoding = file.getVariantCodings().get(0);

		assertNotNull(variantCoding);
	}

	@Test
	void testCriteriaNumber() {
		assertEquals(2, variantCoding.getCriteria().size());
	}

	@Test
	void testCriterionName() {
		assertEquals("bed1", variantCoding.getCriteria().get(0).getName());
	}

	@Test
	void testCriterionValuePosInt() {
		assertEquals(new BigDecimal(1), variantCoding.getCriteria().get(0).getValues().get(0));
	}

	@Test
	void testCriterionValuePosFloat() {
		assertEquals(new BigDecimal("2.0"), variantCoding.getCriteria().get(0).getValues().get(1));
	}

	@Test
	void testCriterionValuePointNotationFloat() {
		assertEquals(new BigDecimal(".3"), variantCoding.getCriteria().get(0).getValues().get(2));
	}

	@Test
	void testCriterionValueExponent() {
		assertEquals(new BigDecimal("4e5"), variantCoding.getCriteria().get(0).getValues().get(3));
	}

	@Test
	void testCriterionValueNegative() {
		assertEquals(new BigDecimal(-7), variantCoding.getCriteria().get(0).getValues().get(4));
	}

	@Test
	void testCriterionValueLogicalTrue() {
		IValue v = variantCoding.getCriteria().get(0).getValues().get(5);
		assertTrue(v instanceof BooleanValue);
		assertTrue(((BooleanValue) v).isValue());
	}

	@Test
	void testCriterionValueLogicalFalse() {
		IValue v = variantCoding.getCriteria().get(0).getValues().get(6);
		assertTrue(v instanceof BooleanValue);
		assertFalse(((BooleanValue) v).isValue());
	}
}
