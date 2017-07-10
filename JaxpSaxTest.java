package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 由jaxp的sax解析方式解析xml
 * Created by 黄文旗 on 2017/7/9.
 */
public class JaxpSaxTest {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //创建SAX解析器工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //创建SAX解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //用解析器中的parse方法解析xml
        saxParser.parse("src/test.xml", new Mydefaule());

    }
}

class Mydefaule extends DefaultHandler {

    boolean flag = false;
    int count = 1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.print("<"+qName+">");
        if ("name".equals(qName)) {
            flag = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        System.out.print(new String(ch, start, length));
        if (flag && count == 2) {
            System.out.println(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.print("<"+qName+">");
        if ("name".equals(qName)) {
            flag = false;
            count++;
        }
    }
}

