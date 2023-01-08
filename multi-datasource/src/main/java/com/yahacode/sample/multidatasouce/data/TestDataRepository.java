package com.yahacode.sample.multidatasouce.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDataRepository extends JpaRepository<TestData, String> {
}
