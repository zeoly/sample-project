package com.yahacode.sample.multidatasource.foo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class TestFooController {

    @Autowired
    private TestFooRepository repository;

    @GetMapping("/save")
    public String save() {
        TestFoo db = repository.save(new TestFoo());
        return db.getId();
    }
}
