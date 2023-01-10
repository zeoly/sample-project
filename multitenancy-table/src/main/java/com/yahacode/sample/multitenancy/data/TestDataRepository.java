package com.yahacode.sample.multitenancy.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDataRepository extends JpaRepository<TestData, String> {
}
