package com.xulee.kandota.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;

import com.xulee.kandota.R;


public class DialogUtils {

    private static Dialog loadingDialog = null;

    public static void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public static void showLoading(Activity act) {
        if (loadingDialog == null) loadingDialog = buildLoading(act);
        loadingDialog.show();
    }

    private static Dialog buildLoading(Activity context) {
        loadingDialog = new Dialog(context, R.style.dialog);
        View progressView = LayoutInflater.from(context).inflate(R.layout.loading, null);
        loadingDialog.setContentView(progressView);
        loadingDialog.setCancelable(true);
        loadingDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                loadingDialog = null;
            }
        });
        return loadingDialog;
    }

    public static void ShowDialog(Context context, String title, String message, String sure,
                                  String cancle, final DialogCallBack callBack) {
        Builder builder = new Builder(context);
        if (message != null) {
            builder.setMessage(message);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setPositiveButton(sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callBack.callBack();
            }
        });

        builder.setNegativeButton(cancle, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public interface DialogCallBack {
        public void callBack();
    }

}
