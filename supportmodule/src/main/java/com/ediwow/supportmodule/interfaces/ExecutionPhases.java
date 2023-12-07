package com.ediwow.supportmodule.interfaces;

import androidx.annotation.Nullable;

public interface ExecutionPhases<T> {
    default void preExecute() {

    }

    @Nullable
    default T execute() {
        return null;
    }

    default void postExecute(@Nullable T resultObj) {

    }
}
