package com.yahacode.sample.multitenancy.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_data")
@GenericGenerator(name = "td-uuid", strategy = "uuid2")
public class TestData {

    @Id
    @GeneratedValue(generator = "td-uuid")
    private String id;

    private String name = "test";

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
