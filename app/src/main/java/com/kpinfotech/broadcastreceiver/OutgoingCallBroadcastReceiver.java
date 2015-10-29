package com.kpinfotech.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kpinfotech.global.AppConstant;
import com.kpinfotech.global.AppMethod;

public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        try {

            /*String oldNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            this.setResultData("0123456789");
            final String newNumber = this.getResultData();
            String msg = "Intercepted outgoing call. Old number " + oldNumber + ", new number " + newNumber;*/

            String cNo = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            AppMethod.showToast(context, cNo + " " + AppConstant.OUTGOING_CALL);


        } catch (Exception e) {
            AppMethod.showToast(context, e.getMessage());
        }
    }
}