package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterTest {
	private DcmFile file;
	private Parameter parameter;

	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		parameter = file.getParameters().get(0);

		assertNotNull(parameter);
	}

	@Test
	void testName() {
		assertEquals("parameter1", parameter.getName());
	}

	@Test
	void testLongName() {
		assertEquals("FESTWERT parameter 1", parameter.getLongName());
	}

	@Test
	void testDisplayName() {
		assertEquals("asam_Parameter1", parameter.getDisplayName());
	}

	@Test
	void testVariantValues() {
		assertEquals(1, parameter.getVariantValues().size());
		assertEquals("var1", parameter.getVariantValues().get(0).getName());
		assertEquals(new NumberValue("1.0"), parameter.getVariantValues().get(0).getValue());
	}

	@Test
	void testFunction() {
		assertEquals("nameOfTheFirstFunction", parameter.getFunction());
	}

	@Test
	void testUnitW() {
		assertEquals("m/s²", parameter.getUnitW());
	}

	@Test
	void testValue() {
		assertEquals(new NumberValue("7.123", "* comment 12"), parameter.getValue());
	}
	
	@Test
	void testTextValue() {
		assertEquals("text value", file.getParameters().get(1).getValue().toString());
		assertEquals("* comment 12", file.getParameters().get(1).getValue().getComments().get(0));
	}
	
	@Test
	void testBooleanValue() {
		assertTrue(file.getParameters().get(2).getValue() instanceof BooleanValue);
		BooleanValue b = (BooleanValue) file.getParameters().get(2).getValue();
		assertTrue(b.isValue());
		assertEquals("* comment 12", b.getComments().get(0));
	}
	
	@Test
	void testComments() {
		assertEquals("* comment 12", parameter.getComments().get(0));
		assertEquals("* comment 12", parameter.getEndComments().get(0));
		assertEquals("* comment 12", parameter.getLongNameComments().get(0));
		assertEquals("* comment 12", parameter.getDisplayNameComments().get(0));
		assertEquals("* comment 12", parameter.getFunctionComments().get(0));
		assertEquals("* comment 12", parameter.getUnitWComments().get(0));
		assertEquals("* comment 12", parameter.getVariantValues().get(0).getComments().get(0));
		assertEquals("* comment 12", parameter.getValue().getComments().get(0));
	}
}
