package com.yahacode.sample;

public class Position {

    public double juniorCount;

    public double intermediateCount;

    public double seniorCount;

    public Position(double juniorCount, double intermediateCount, double seniorCount) {
        this.juniorCount = juniorCount;
        this.intermediateCount = intermediateCount;
        this.seniorCount = seniorCount;
    }

    public double total() {
        return juniorCount + intermediateCount + seniorCount;
    }
}
