package com.ediwow.supportmodule.concurrency;

import android.app.Activity;

import com.ediwow.supportmodule.interfaces.ExecutionPhases;

import org.json.JSONObject;

public abstract class CustomExecutor implements ExecutionPhases {
    private final Thread thread;

    public CustomExecutor(Activity activity) {
        thread = new Thread(() -> {
            JSONObject resultObj = execute();
            activity.runOnUiThread(() -> postExecute(resultObj));
        });
    }

    public void startExecution() {
        preExecute();
        thread.start();
    }
}
