package com.ediwow.supportmodule.backend;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class CustomAlertDialog {
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    public static final byte POSITIVE_BUTTON = 1;
    public static final byte NEUTRAL_BUTTON = 0;
    public static final byte NEGATIVE_BUTTON = -1;

    public CustomAlertDialog(@NonNull AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public CustomAlertDialog(@NonNull AlertDialog.Builder builder) {
        constructor(builder, false);
    }

    public CustomAlertDialog(@NonNull AlertDialog.Builder builder, boolean createNow) {
        constructor(builder, createNow);
    }

    public AlertDialog getAlertDialog() {
        return this.alertDialog;
    }

    public AlertDialog.Builder getBuilder() {
        return this.builder;
    }

    private void constructor(@NonNull AlertDialog.Builder builder, boolean createNow) {
        this.builder = builder;
        if (createNow) create();
    }

    public void create() {
        if (builder != null) this.alertDialog = this.builder.create();
        else System.out.println("No builder to create from");
    }

    public void setButton(byte mode, String text, DialogInterface.OnClickListener listener) {
        if (builder != null) {
            switch (mode) {
                case 1:
                    builder.setPositiveButton(text, listener);
                    break;
                case 0:
                    builder.setNeutralButton(text, listener);
                    break;
                case -1:
                    builder.setNegativeButton(text, listener);
                    break;
            }
        } else System.out.println("No builder to modify from");
    }

    public boolean show() {
        if (alertDialog != null) {
            if (!alertDialog.isShowing()) {
                alertDialog.show();
                return true;
            } else return false;
        } else {
            System.out.println("No AlertDialog found");
            return false;
        }
    }

    public boolean dismiss() {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
                return true;
            } else return false;
        } else {
            System.out.println("No AlertDialog found");
            return false;
        }
    }

    public void setMessage(String message) {
        if (alertDialog != null) alertDialog.setMessage(message);
        else System.out.println("No AlertDialog found");
    }
}
