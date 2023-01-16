package com.yahacode.sample.multidatasource.bar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bar")
public class TestBarController {

    @Autowired
    private TestBarRepository repository;

    @GetMapping("/save")
    public String save() {
        TestBar db = repository.save(new TestBar());
        return db.getId();
    }
}
