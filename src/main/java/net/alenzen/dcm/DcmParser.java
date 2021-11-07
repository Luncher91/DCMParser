package net.alenzen.dcm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

import net.alenzen.dcm.antlr.dcmLexer;
import net.alenzen.dcm.antlr.dcmParser;

public class DcmParser {
	private IParserEventHandler eventHandler = (line, position, message) -> {
		System.err.println("Line " + line + "@" + position + ": " + message);
	};

	private InputStream filecontent;

	public DcmParser(String filename) throws FileNotFoundException {
		this.filecontent = new FileInputStream(new File(filename));
	}

	public DcmParser(File dcmFile) throws FileNotFoundException {
		this.filecontent = new FileInputStream(dcmFile);
	}
	
	public DcmParser(InputStream filecontent) {
		this.filecontent = filecontent;
	}

	private ANTLRErrorListener createANTLRErrorListener(IParserEventHandler eventHandler) {
		return new ANTLRErrorListener() {

			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				eventHandler.log(line, charPositionInLine, msg);
			}

			@Override
			public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					int prediction, ATNConfigSet configs) {
			}

			@Override
			public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					BitSet conflictingAlts, ATNConfigSet configs) {
			}

			@Override
			public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
					BitSet ambigAlts, ATNConfigSet configs) {
			}
		};
	}

	public DcmFile parse() throws IOException {
		ANTLRErrorListener listener = createANTLRErrorListener(eventHandler);
		CharStream chStream = determineCharStream();
		// lexing file
		dcmLexer lexer = new dcmLexer(chStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		// parsing tokens
		dcmParser parser = new dcmParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree tree = parser.dcm_file();

		// visit ParseTree to create usable object structure
		return (DcmFile) new DcmVisitor(eventHandler).visit(tree);
	}

	private CharStream determineCharStream() throws IOException {
		return determineCharStream(filecontent);
	}

	private static CharStream determineCharStream(InputStream input) throws IOException {
		CharStream chStream;
		try (BOMInputStream is = createBOMInputStream(input)) {
			Charset fileEncoding = determineCharset(is);
			chStream = CharStreams.fromStream(is, fileEncoding);
		}
		return chStream;
	}

	private static BOMInputStream createBOMInputStream(InputStream in) {
		return new BOMInputStream(in, ByteOrderMark.UTF_8, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE,
				ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE);
	}

	private static Charset determineCharset(BOMInputStream is) throws IOException {
		if (!is.hasBOM()) {
			return StandardCharsets.ISO_8859_1;
		}

		if (is.hasBOM(ByteOrderMark.UTF_8)) {
			return StandardCharsets.UTF_8;
		}

		if (is.hasBOM(ByteOrderMark.UTF_16LE)) {
			return StandardCharsets.UTF_16LE;
		}

		if (is.hasBOM(ByteOrderMark.UTF_16BE)) {
			return StandardCharsets.UTF_16BE;
		}

		if (is.hasBOM(ByteOrderMark.UTF_32LE)) {
			return Charset.forName("UTF-32LE");
		}

		if (is.hasBOM(ByteOrderMark.UTF_32BE)) {
			return Charset.forName("UTF-32BE");
		}

		throw new IOException("Unknown charset spepcified in the BOM area: " + is.getBOMCharsetName());
	}
	
	public void setEventHandler(IParserEventHandler eh) {
		this.eventHandler = eh;
	}
}
