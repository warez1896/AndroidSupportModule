package com.ediwow.supportmodule.concurrency;

import android.app.Activity;

import com.ediwow.supportmodule.interfaces.ExecutionPhases3;

public abstract class CustomExecutor3<T> implements ExecutionPhases3<T> {
    private final Thread thread;

    public CustomExecutor3(Activity activity) {
        thread = new Thread(() -> {
            T result = execute();
            activity.runOnUiThread(() -> postExecute(result));
        });
    }

    public void startExecution() {
        preExecute();
        thread.start();
    }
}
