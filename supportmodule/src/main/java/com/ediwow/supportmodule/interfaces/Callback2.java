package com.ediwow.supportmodule.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Callback2 {
    default void call(@NonNull Callback.CallbackEnums callbackEnum, @Nullable Object... objs) {
        System.out.println("Please override method if you want to use it");
    }
}
