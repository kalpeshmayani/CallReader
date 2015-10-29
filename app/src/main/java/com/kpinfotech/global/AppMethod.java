package com.kpinfotech.global;

import android.content.Context;
import android.widget.Toast;

public class AppMethod {

    // display toast
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}