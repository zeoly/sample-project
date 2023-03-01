package com.yahacode.unittest.impl;

import com.yahacode.unittest.FooRepository;
import com.yahacode.unittest.FooService;

public class FooServiceImpl implements FooService {

    private FooRepository fooRepository;

    private int THRESHOLD;

    @Override
    public boolean isOdd(int a) {
        if (a > 10000) {
            throw new RuntimeException("too big!");
        }
        return a % 2 > 0;
    }

    @Override
    public String getOne(int id) {
        return fooRepository.getOne(id);
    }

    @Override
    public int getThreshold() {
        return THRESHOLD;
    }

}
