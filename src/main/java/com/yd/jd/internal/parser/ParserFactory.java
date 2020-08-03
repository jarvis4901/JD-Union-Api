package com.yd.jd.internal.parser;

public class ParserFactory {
    private static final Parser JSON_PARSER = new JsonParser();
    private static final Parser XML_PARSER = new XmlParser();

    public ParserFactory() {
    }

    public static Parser getJsonParser() {
        return JSON_PARSER;
    }

    public static Parser getXmlParser() {
        return XML_PARSER;
    }
}
