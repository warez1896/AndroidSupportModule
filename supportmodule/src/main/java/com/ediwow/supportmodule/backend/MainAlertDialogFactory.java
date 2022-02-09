package com.ediwow.supportmodule.backend;


import android.app.Activity;
import android.app.AlertDialog;

public class MainAlertDialogFactory {
    private final Activity activity;

    public MainAlertDialogFactory(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public AlertDialog.Builder customDialog(String title, String message, boolean canceleable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(canceleable);
        return builder;
    }


    public AlertDialog.Builder pleaseWait(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Please wait");
        builder.setMessage(message);
        builder.setCancelable(false);
        return builder;
    }
}
