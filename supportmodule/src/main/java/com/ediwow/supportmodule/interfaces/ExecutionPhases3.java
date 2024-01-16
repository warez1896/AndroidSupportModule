package com.ediwow.supportmodule.interfaces;

import androidx.annotation.Nullable;

public interface ExecutionPhases3<T> {
    default void preExecute(){

    }

    @Nullable
    T execute();

    default void postExecute(@Nullable T result){

    }
}
