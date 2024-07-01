package com.yahacode.sample.gpx.controller;

import com.yahacode.hiddenblade.geo.GeoJson;
import com.yahacode.hiddenblade.tool.utils.GeoUtil;
import com.yahacode.sample.gpx.model.Activity;
import com.yahacode.sample.gpx.repo.ActivityRepo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GeoJsonController {

    @Autowired
    private ActivityRepo activityRepo;

    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @GetMapping("/geojson")
    public String getGeoJson(@RequestParam(required = false) boolean wgs84) throws DocumentException, IOException, IllegalAccessException {
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
                if (wgs84) {
                    coordinates[i] = new Coordinate(Double.parseDouble(element.attribute("lon").getValue()), Double.parseDouble(element.attribute("lat").getValue()));
                } else {
                    double[] point = GeoUtil.wgs84ToGcj02(Double.parseDouble(element.attribute("lon").getValue()), Double.parseDouble(element.attribute("lat").getValue()));
                    coordinates[i] = new Coordinate(point[0], point[1]);
                }
                i++;
            }
            LineString lineString = geometryFactory.createLineString(coordinates);
            LayerModel layerModel = new LayerModel("name", lineString);
            result.add(layerModel);
        }
        return GeoJson.write(result);
    }

    @GetMapping("/import")
    public void importData() throws IOException, DocumentException {
        List<File> files = Files.list(Paths.get("E://骑行gpx")).map(Path::toFile).collect(Collectors.toList());
        GeometryFactory geometryFactory = new GeometryFactory();
        for (File file : files) {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            Element trk = (Element) rootElement.elements("trk").get(0);
            Element nameElement = (Element) trk.elements("name").get(0);
            String name = nameElement.getStringValue();

            Element trkseg = (Element) trk.elements("trkseg").get(0);
            Coordinate[] coordinates = new Coordinate[trkseg.elements().size()];
            int i = 0;
            ZonedDateTime startTime = ZonedDateTime.now(), endTime = ZonedDateTime.now();
            for (Object o : trkseg.elements()) {
                Element element = (Element) o;
                if (i == 0) {
                    String startTimeString = ((Element) element.elements("time").get(0)).getStringValue();
                    startTime = ZonedDateTime.parse(startTimeString, dtf);
                    if (!name.contains("行者")) {
                        startTime = startTime.withZoneSameInstant(ZoneId.systemDefault());
                    }
                } else if (i == coordinates.length - 1) {
                    String endTimeString = ((Element) element.elements("time").get(0)).getStringValue();
                    endTime = ZonedDateTime.parse(endTimeString, dtf);
                    if (!name.contains("行者")) {
                        endTime = endTime.withZoneSameInstant(ZoneId.systemDefault());
                    }
                }

                coordinates[i] = new Coordinate(Double.parseDouble(element.attribute("lon").getValue()), Double.parseDouble(element.attribute("lat").getValue()));
                i++;
            }
            MultiPoint multiPoint = geometryFactory.createMultiPointFromCoords(coordinates);
            Activity activity = new Activity(name, startTime.toLocalDateTime(), endTime.toLocalDateTime(), multiPoint);
            activityRepo.save(activity);
        }
    }
}
