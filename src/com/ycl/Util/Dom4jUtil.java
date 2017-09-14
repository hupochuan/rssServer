package com.ycl.Util;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ycl.bean.Website;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Dom4jUtil {
    public List<Website> parserXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        List<Website> list = new ArrayList<Website>();
        try {
            URL url = getClass().getResource("");
            System.out.println(url);
            //System.out.println(url.getPath());
            String path = url.getFile().replace("%20", " ") + fileName;
            Document document = saxReader.read(new File(path));
            Element websites = document.getRootElement();
            for (Iterator i = websites.elementIterator(); i.hasNext(); ) {
                Element employee = (Element) i.next();
                Website website = new Website();

                for (Iterator j = employee.elementIterator(); j.hasNext(); ) {
                    Element node = (Element) j.next();
                    String name = node.getName();
                    // System.out.println(name + ":" + node.getText());
                    String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    //System.out.println(methodName);
                    Method method = website.getClass().getMethod(methodName, String.class);
                   
                    method.invoke(website, node.getText());
                }
                System.out.println(website.getName());
                list.add(website);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}