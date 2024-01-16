package com.ediwow.supportmodule.interfaces;

import androidx.annotation.NonNull;

public interface ExecutionPhases<T> {
    default void preExecute() {

    }

    @NonNull
    T execute();

    default void postExecute(@NonNull T result) {

    }
}
