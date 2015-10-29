package com.kpinfotech.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.kpinfotech.global.AppConstant;
import com.kpinfotech.global.AppMethod;

public class CallBroadcastReceiver extends BroadcastReceiver {

    static boolean isRinging = false;
    static boolean isReceived = false;

    static String cNo;

    public void onReceive(Context context, Intent intent) {

        try {

            // Get current phone state
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state == null)
                return;

            // phone is ringing
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                isRinging = true;


                // get caller's number
                Bundle bundle = intent.getExtras();
//                cNo = bundle.getString("incoming_number");
                cNo = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }

            // phone is received
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                isReceived = true;
            }

            // phone is idle
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {

                // detect missed call
                if (isRinging == true && isReceived == false) {
                    isRinging = false;
                    isReceived = false;

                    AppMethod.showToast(context, cNo + " " + AppConstant.MISSED_CALL);

                }
                // detect incoming call
                else if (isRinging == true && isReceived == true) {
                    isRinging = false;
                    isReceived = false;

                    AppMethod.showToast(context, cNo + " " + AppConstant.INCOMING_CALL);

                }
                // detect outgoing call
                else if (isRinging == false && isReceived == true) {
                    isRinging = false;
                    isReceived = false;
                }

            }

        } catch (Exception e) {
            AppMethod.showToast(context, e.getMessage());
        }
    }
}