package com.yahacode.sample.multidatasource.foo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestFooRepository extends JpaRepository<TestFoo, String> {
}
