package com.tianque.util.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExcelInit {

	private static Map<String, String> extendSqlMaps = new HashMap<String, String>();
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	private static Map<String, String> propertyColumn = new HashMap<String, String>();

	public static void parseNameSpaceMap() throws Exception {
		readNamespace();
		writeXml();
	}

	private static void writeXml() throws IOException, ParserConfigurationException,
			FileNotFoundException, TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		String relativelyPath = System.getProperty("user.dir");
		System.out.println(relativelyPath);
		File file = new File(relativelyPath + "\\target\\genarate-xml\\");
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		file = new File(relativelyPath + "\\target\\genarate-xml\\namespace.xml");
		if (!file.exists() && !file.isFile()) {
			file.createNewFile();
		}
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		doc.setXmlVersion("1.0");

		Element root = doc.createElement("namespaces");
		for (Entry<String, String> entry : extendSqlMaps.entrySet()) {
			Element e = doc.createElement("namespace");
			e.setAttribute("id", entry.getKey());
			e.setAttribute("path", entry.getValue());
			root.appendChild(e);
		}
		doc.appendChild(root);

		DOMSource domSource = new DOMSource(doc);
		FileOutputStream out = new FileOutputStream(file);
		StreamResult xmlResult = new StreamResult(out);
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transFormer = transFactory.newTransformer();
		transFormer.transform(domSource, xmlResult);
	}

	private static void readNamespace() throws ParserConfigurationException, SAXException,
			IOException {
		String classPath = ClassLoader.getSystemResource("").toString().replace("file:/", "");
		File file = new File(classPath + "sqlmaps");
		File[] files = file.listFiles();
		for (File child : files) {
			if (child.isDirectory()) {
				File[] subFiles = child.listFiles();
				for (File subChild : subFiles) {
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(subChild);
					Element node = doc.getDocumentElement();
					System.out.println(node.getAttribute("namespace") + "::::" + "sqlmaps/"
							+ child.getName() + "/" + subChild.getName());
					extendSqlMaps.put(node.getAttribute("namespace"), "sqlmaps/" + child.getName()
							+ "/" + subChild.getName());
				}
			} else {
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(child);
				Element node = doc.getDocumentElement();
				System.out.println(node.getAttribute("namespace") + "::::" + "sqlmaps/"
						+ child.getName());
				extendSqlMaps.put(node.getAttribute("namespace"), "sqlmaps/" + child.getName());
			}
		}
	}

	public static void parseXml(String xmlName, String resultId) throws Exception {
		String classPath = ClassLoader.getSystemResource("").toString().replace("file:/", "");
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(classPath + xmlName));
		NodeList nl = doc.getElementsByTagName("resultMap");
		for (int i = 0; i < nl.getLength(); i++) {
			Element node = (Element) nl.item(i);
			if (resultId.equals(node.getAttribute("id"))) {
				String extend = node.getAttribute("extends");
				if (null != extend && !extend.trim().isEmpty()) {
					String[] extendName = extend.split("\\.");
					parseXml(extendSqlMaps.get(extendName[0]), extendName[1]);
				}
				NodeList resultNodes = node.getElementsByTagName("result");
				for (int j = 0; j < resultNodes.getLength(); j++) {
					Element result = (Element) resultNodes.item(j);
					propertyColumn.put(result.getAttribute("property"),
							result.getAttribute("column"));
				}
				return;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> propertyColumn = getPropertyColumnMap();
		for (Entry<String, String> entry : propertyColumn.entrySet()) {
			System.out.println("property:" + entry.getKey() + ",column:" + entry.getValue());
		}
	}

	public static Map<String, String> getPropertyColumnMap() throws Exception {
		readXml();
		parseXml("sqlmaps/druggy.xml", "druggyResult");
		return propertyColumn;
	}

	private static void readXml() throws Exception {
		DocumentBuilder builder = factory.newDocumentBuilder();
		String relativelyPath = System.getProperty("user.dir");
		Document doc = builder.parse(new File(relativelyPath
				+ "\\target\\genarate-xml\\namespace.xml"));
		NodeList nl = doc.getElementsByTagName("namespace");
		for (int i = 0; i < nl.getLength(); i++) {
			Element node = (Element) nl.item(i);
			extendSqlMaps.put(node.getAttribute("id"), node.getAttribute("path"));
		}
	}
}
