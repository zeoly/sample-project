package com.yahacode.sample.gpx.controller;

import org.locationtech.jts.geom.LineString;

public class LayerModel {

    private String name;

    private LineString lineString;

    public LayerModel(String name, LineString lineString) {
        this.name = name;
        this.lineString = lineString;
    }
}
