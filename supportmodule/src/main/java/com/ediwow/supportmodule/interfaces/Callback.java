package com.ediwow.supportmodule.interfaces;

public interface Callback {
    void call(CallbackEnums callbackEnum, Object... objs);

    enum CallbackEnums {
        ACTION, TOGGLE_CONTROL, JSON_DATA;
    }
}
