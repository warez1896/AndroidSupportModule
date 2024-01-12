package com.ediwow.supportmodule.interfaces;

public interface ExecutionPhases2 {
    default void preExecute() {
    }

    void execute();

    default void postExecute() {
    }
}
