package com.yahacode.dsl.statemachine;

public interface Transition<T, E> {

    State<T> getSource();

    State<T> getTarget();

    E getEvent();
}
