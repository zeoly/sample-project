package com.yahacode.sample.multitenancy.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class TestDataController {

    @Autowired
    private TestDataRepository repository;

    @GetMapping("/save")
    public String save() {
        TestData db = repository.save(new TestData());
        return db.getId();
    }
}
