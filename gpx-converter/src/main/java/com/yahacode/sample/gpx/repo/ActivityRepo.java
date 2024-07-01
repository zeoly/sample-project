package com.yahacode.sample.gpx.repo;

import com.yahacode.sample.gpx.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity, String> {
}
