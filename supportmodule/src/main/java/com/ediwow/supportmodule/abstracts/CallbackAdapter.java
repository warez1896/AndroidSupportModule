package com.ediwow.supportmodule.abstracts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ediwow.supportmodule.interfaces.Callback;

public abstract class CallbackAdapter implements Callback {
    @Override
    public void call(@NonNull CallbackEnums callbackEnum, @Nullable Object... objs) {
        System.out.println("Please override method if you want to use it");
    }
}
