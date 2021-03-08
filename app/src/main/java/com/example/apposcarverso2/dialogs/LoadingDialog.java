package com.example.apposcarverso2.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.apposcarverso2.R;

public class LoadingDialog {

    private final Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dissmissDialog() {
        dialog.dismiss();
    }

}
