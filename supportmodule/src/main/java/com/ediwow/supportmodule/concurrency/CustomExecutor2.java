package com.ediwow.supportmodule.concurrency;

import android.app.Activity;

import com.ediwow.supportmodule.interfaces.ExecutionPhases2;

public abstract class CustomExecutor2 implements ExecutionPhases2 {
    private final Thread thread;

    public CustomExecutor2(Activity activity) {
        thread = new Thread(() -> {
            execute();
            activity.runOnUiThread(this::postExecute);
        });
    }

    public void startExecution() {
        preExecute();
        thread.start();
    }
}
