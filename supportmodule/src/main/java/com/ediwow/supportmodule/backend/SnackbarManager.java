package com.ediwow.supportmodule.backend;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarManager {
    private final View container;
    private Snackbar snackbar;

    public SnackbarManager(View view) {
        this.container = view;
    }

    public void showSnackbar(String text, int length) {
        snackbar = Snackbar.make(container, text, length).setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void dismissSnackbar() {
        if (snackbar != null && snackbar.isShown())
            snackbar.dismiss();
    }

}
