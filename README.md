# DCMParser

[![Build Status](https://app.travis-ci.com/Luncher91/DCMParser.svg?branch=main)](https://app.travis-ci.com/Luncher91/DCMParser)

This library parses an DCM file into a typed object structure. The object structure represents the complete file including all the comments.

The object structure can be serialized into JSON or can even be written back to the DCM format.

## Java samples

### Parse DCM

```java
DcmParser parser = new DcmParser("freeTest.dcm");

// optional error handler
parser.setEventHandler((line, position, message) -> {
	System.out.println("Line " + line + "@" + position + ": " + message);
});

DcmFile dcm = parser.parse();
```

### DCM to JSON

```java
DcmParser parser = new DcmParser("freeTest.dcm");
DcmFile dcm = parser.parse();

System.out.println(dcm.toJson());
```

### Parse JSON

```java
DcmFile fromJson = DcmFile.fromJson(TESTFILE_A_JSON);
```

## Roadmap

* CLI interface
* equals method for all types
* broader testing with real world data
