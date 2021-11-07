package net.alenzen.dcm;

public interface IParserEventHandler {
	void log(long lineNumber, long position, String message);
}
