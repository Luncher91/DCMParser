package net.alenzen.dcm;

public class DefaultParserEventHandler implements IParserEventHandler {

	@Override
	public void log(long lineNumber, long position, String message) {
		System.out.println("[Warning] " + lineNumber + "@" + position + ": " + message);
	}
}
