package com.yahacode.sample.gpx.controller;

import com.yahacode.hiddenblade.geo.GeoJson;
import com.yahacode.hiddenblade.tool.utils.GeoUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GeoJsonController {

    @GetMapping("geojson")
    public String getGeoJson() throws DocumentException, IOException, IllegalAccessException {
        List<File> files = Files.list(Paths.get("E://骑行gpx")).map(Path::toFile).collect(Collectors.toList());
        List<LayerModel> result = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        for (File file : files) {
            System.out.println("file: " + file.getName());
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            Element trk = (Element) rootElement.elements("trk").get(0);
            Element trkseg = (Element) trk.elements("trkseg").get(0);

            Coordinate[] coordinates = new Coordinate[trkseg.elements().size()];
            int i = 0;
            for (Object o : trkseg.elements()) {
                Element element = (Element) o;
                double[] point = GeoUtil.wgs84ToGcj02(Double.parseDouble(element.attribute("lon").getValue()), Double.parseDouble(element.attribute("lat").getValue()));
                coordinates[i] = new Coordinate(point[0], point[1]);
                i++;
            }
            LineString lineString = geometryFactory.createLineString(coordinates);
            LayerModel layerModel = new LayerModel("name", lineString);
            result.add(layerModel);
        }
        return GeoJson.write(result);
    }
}
