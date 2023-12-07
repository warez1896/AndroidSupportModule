package com.ediwow.supportmodule.backend;


import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class MainAlertDialogFactory {
    private final Activity activity;

    public MainAlertDialogFactory(Activity activity) {
        this.activity = activity;
    }

    public AlertDialog.Builder customDialog(String title, String message, boolean canceleable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(canceleable);
        return builder;
    }

    public AlertDialog.Builder customSelectionDialog(String title, String[] choices, int selectedItem, DialogInterface.OnClickListener listener, boolean canceleable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setSingleChoiceItems(choices, selectedItem, listener);
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

    protected Activity getActivity(){
        return activity;
    }
}
