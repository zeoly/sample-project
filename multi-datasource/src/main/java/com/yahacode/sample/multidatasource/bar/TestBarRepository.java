package com.yahacode.sample.multidatasource.bar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBarRepository extends JpaRepository<TestBar, String> {
}
