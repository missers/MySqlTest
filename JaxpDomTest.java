package com.company;

import com.sun.xml.internal.messaging.saaj.soap.impl.ElementFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

/**
 * 用jaxp中的DOM解析方式解析xml
 */
public class JaxpDomTest {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // write your code here
//        selectName();
//        addElement();
        delete();

    }

    /**
     * 在第一个p1下面（末尾）添加节点
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException
     */
    private static void addElement() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        //创建DOM解析器工厂类
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //创建DOM解析器
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //由parse得到Document
        Document document = documentBuilder.parse("src/test.xml");
        //得到p1的所有NodeList结合
        NodeList list = document.getElementsByTagName("p1");
        //得到第一个p1
        Node node = list.item(0);
        //创建标签元素,用document下的creartElement()
        Element em =document.createElement("sex");
        //创建文本
        Text text = document.createTextNode("nan");
        //文本绑定到标签元素sex上
        em.appendChild(text);
        //标签元素绑定到p1
        node.appendChild(em);
        //回写操作
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/test.xml"));
    }

    /**
     * 查询xml中所有height的值
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static void selectName() throws ParserConfigurationException, SAXException, IOException {
        //创建DOM解析器工厂
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //创建Dom解析器
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //由Dom解析器解析指定的XML
        Document document = documentBuilder.parse("src/test.xml");
        //通过Document中getElementByTagName()获得所有height节点
        NodeList list = document.getElementsByTagName("height");
        //遍历NodeList集合
        for(int i=0; i< list.getLength(); i++){
            System.out.println(list.item(i).getTextContent());
        }
    }

    /**
     * 删除节点
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    private static void delete() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/test.xml");
        //要找到父节点，然后删除本节点
        Node node = document.getElementsByTagName("height").item(0);
        node.getParentNode().removeChild(node);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/test.xml"));

    }
}
