package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionTest {
	private DcmFile file;
	private FunctionGroup functionGroup;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		functionGroup = file.getFunctionGroups().get(0);

		assertNotNull(functionGroup);
	}
	
	@Test
	void testFunctionGroupsCount() {
		assertEquals(2, file.getFunctionGroups().size());
	}
	
	@Test
	void testFunctionGroupCount() {
		assertEquals(3, functionGroup.getFunctions().size());
	}
	
	@Test
	void testName() {
		assertEquals("nameOfTheFirstFunction", functionGroup.getFunctions().get(0).getName());
		assertEquals("nameOfTheSecondFunction", functionGroup.getFunctions().get(1).getName());
		assertEquals("nameOfTheFifthFunction", functionGroup.getFunctions().get(2).getName());
	}
	
	@Test
	void testVersion() {
		assertEquals("version 42", functionGroup.getFunctions().get(0).getVersion());
		assertEquals("12.4", functionGroup.getFunctions().get(1).getVersion());
	}
	
	@Test
	void testLongname() {
		assertEquals("long----name", functionGroup.getFunctions().get(0).getLongname());
		assertEquals("longerrrrrrrrrr name", functionGroup.getFunctions().get(1).getLongname());
	}
	
	@Test
	void testComments() {
		assertEquals(2, functionGroup.getComments().size());
		assertEquals("* comment number one", functionGroup.getComments().get(0));
		assertEquals("* second line", functionGroup.getComments().get(1));
		assertEquals("* comment", functionGroup.getEndComments().get(0));
	}
	
	@Test
	void testFunctionComments() {
		assertEquals("* comment", functionGroup.getFunctions().get(0).getComments().get(0));
		assertEquals("* comment", functionGroup.getFunctions().get(1).getComments().get(0));
	}
}
