package com.yahacode.sample.gpx.model;

import org.locationtech.jts.geom.MultiPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "act")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private MultiPoint geometry;

    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, MultiPoint geometry) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public MultiPoint getGeometry() {
        return geometry;
    }

    public void setGeometry(MultiPoint geometry) {
        this.geometry = geometry;
    }
}
