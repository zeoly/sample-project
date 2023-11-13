package com.yahacode.sample.syntactic.lambda;

public interface TestInterface {

    default void print(String a) {
        System.out.println(a);
    }

    default int add(int a, int b) {
        return a + b;
    }
}
