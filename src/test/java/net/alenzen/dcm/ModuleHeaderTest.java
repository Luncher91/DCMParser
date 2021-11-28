package net.alenzen.dcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModuleHeaderTest {
	private DcmFile file;
	private ModuleHeader moduleHeader;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = DcmFileTest.getTestFile(TestFile.A);
		moduleHeader = file.getModuleHeaders().get(0);

		assertNotNull(moduleHeader);
	}
	
	@Test
	void testName() {
		assertEquals("iAmTheHead", moduleHeader.getName());
	}
	
	@Test
	void testLineNumbers() {
		assertEquals(2, moduleHeader.getLines().size());
	}
	
	@Test
	void testLines() {
		assertEquals("line 1", moduleHeader.getText());
		assertEquals("line 2", moduleHeader.getLines().get(0).getText());
		assertEquals("line 3", moduleHeader.getLines().get(1).getText());
	}
	
	@Test
	void testSecondModuleHeader() {
		ModuleHeader secondHeader = file.getModuleHeaders().get(1);
		assertEquals("iAmTheSecondHead", secondHeader.getName());
		assertEquals(1, secondHeader.getLines().size());
	}
	
	@Test
	void testComments() {
		assertEquals(1, moduleHeader.getComments().size());
		assertEquals("* comment 1", moduleHeader.getComments().get(0));
		assertEquals("*comment 2", moduleHeader.getLines().get(0).getComments().get(0));
	}
}
