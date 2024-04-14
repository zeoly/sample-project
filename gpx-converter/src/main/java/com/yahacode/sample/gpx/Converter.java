package com.yahacode.sample.gpx;

import com.yahacode.hiddenblade.tool.utils.GeoUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Converter {
    public static void main(String[] args) throws IOException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("D://Zepp20230527142103.gpx"));
        Element rootElement = document.getRootElement();
        Element trk = (Element) rootElement.elements().get(0);
        Element trkseg = (Element) trk.elements("trkseg").get(0);
        for (Object o : trkseg.elements()) {
            Element element = (Element) o;
            double[] point = GeoUtil.gcj02ToWgs84(Double.parseDouble(element.attribute("lon").getValue()), Double.parseDouble(element.attribute("lat").getValue()));
            element.attribute("lat").setValue(String.valueOf(point[1]));
            element.attribute("lon").setValue(String.valueOf(point[0]));
        }
        Files.write(Paths.get("D://test.gpx"), rootElement.asXML().getBytes());
    }
}