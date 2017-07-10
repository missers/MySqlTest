package com.company;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

/**
 * 由dom4j解析xml
 *
 * Created by 黄文旗 on 2017/7/10.
 */
public class TestDom4j {
    public static void main(String[] args) throws DocumentException, IOException {
//        getAllName();
//        getFirstName();
//        getSecondName();
//        addElement();
        AddsElement();

    }

    /**
     * 在指定位置添加元素
     * @throws DocumentException
     * @throws IOException
     */
    private static void AddsElement() throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/test.xml");
        Element root = document.getRootElement();
        //获得people下的第一个name
        Element name = root.element("name");
        //获得name下的所有元素
        List<Element> list = name.elements();
        //创建school元素，用DocumentHelper中的createrElement()创建
        Element element = DocumentHelper.createElement("school");
        element.addText("zzuli");
        //在list集合的第一个位置下添加shool元素
        list.add(0, element);
        //回写操作
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/test.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 添加元素
     *
     * @throws DocumentException
     * @throws IOException
     */
    private static void addElement() throws DocumentException, IOException {
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //得到Document
        Document document = saxReader.read("src/test.xml");
        //得到根节点
        Element root = document.getRootElement();
        //获的根节点下第一个name元素
        Element name = root.element("name");
        //添加元素
        Element sex = name.addElement("sex");
        sex.setText("nan");
        //回写操作
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/test.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 查询第二个oldname的值
     *
     * @throws DocumentException
     */
    private static void getSecondName() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/test.xml");
        //获得根节点下的第一层name所有元素
        List<Element> list = document.getRootElement().elements("name");
        //获得第一个name下的第一个oldname的值
        String str = list.get(1).element("oldname").getText();
        System.out.println(str);
    }

    /**
     * 查询第一个oldname的值
     *
     * @throws DocumentException
     */
    private static void getFirstName() throws DocumentException {
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //得到Document
        Document document = saxReader.read("src/test.xml");
        //得到根节点
        Element root = document.getRootElement();
        //获得根节点下的第一个name元素节点
        Element name = root.element("name");
        //由name元素得到其第一个oldname
        Element oldname = name.element("oldname");
        String str = oldname.getText();
        System.out.println(str);
    }

    /**
     * 查询oldname元素
     * @throws DocumentException
     */
    private static void getAllName() throws DocumentException {
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //用解析器得到document
        Document document = saxReader.read("src/test.xml");
        //得到根节点
        Element root = document.getRootElement();
        //由根节点得到people下的name元素，注意只包含一层
        List<Element> list = root.elements("name");
        //遍历list集合得到name下的oldname元素的值
        for (Element element : list) {
            Element name1 = element.element("oldname");
            String str = name1.getText();
            System.out.println(str);
        }
    }
}
