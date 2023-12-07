package com.ediwow.supportmodule.interfaces;

public interface Callback {
    void call(CallbackEnums callbackEnum, Object... objs);

    enum CallbackEnums {
        ACTION, TOGGLE_CONTROL, TOAST, START_INDEFINITE_TOAST, STOP_INDEFINITE_TOAST, JSON_DATA;
    }
}
