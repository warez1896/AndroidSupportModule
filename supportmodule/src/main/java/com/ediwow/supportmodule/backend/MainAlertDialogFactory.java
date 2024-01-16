package com.ediwow.supportmodule.backend;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ediwow.supportmodule.R;

public class MainAlertDialogFactory {
    private final Context context;
    private final LayoutInflater layoutInflater;

    public MainAlertDialogFactory(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    protected View customView() {
        return layoutInflater.inflate(R.layout.ad_title, null);
    }

    protected TextView customTitle(View view) {
        return view.findViewById(R.id.tv_alert_title);
    }

    public AlertDialog.Builder customDialog(String title, String message, boolean canceleable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = customView();
        TextView tvTitle = customTitle(view);
        tvTitle.setText(title);
        builder.setCustomTitle(view);
        builder.setMessage(message);
        builder.setCancelable(canceleable);
        return builder;
    }

    public AlertDialog.Builder customSelectionDialog(String title, String[] choices, int selectedItem, DialogInterface.OnClickListener listener, boolean canceleable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = customView();
        TextView tvTitle = customTitle(view);
        tvTitle.setText(title);
        builder.setCustomTitle(view);

        builder.setSingleChoiceItems(choices, selectedItem, listener);
        builder.setCancelable(canceleable);
        return builder;
    }

    public AlertDialog.Builder pleaseWait(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = customView();
        TextView tvTitle = customTitle(view);
        tvTitle.setText(R.string.please_wait);
        builder.setCustomTitle(view);
        builder.setMessage(message);
        builder.setCancelable(false);
        return builder;
    }

    protected Context getContext() {
        return this.context;
    }
}
