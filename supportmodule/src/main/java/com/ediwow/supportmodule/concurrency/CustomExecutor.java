package com.ediwow.supportmodule.concurrency;

import android.app.Activity;

import com.ediwow.supportmodule.interfaces.ExecutionPhases;

import org.json.JSONObject;

public abstract class CustomExecutor<T> implements ExecutionPhases<T> {
    private final Thread thread;

    public CustomExecutor(Activity activity) {
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
