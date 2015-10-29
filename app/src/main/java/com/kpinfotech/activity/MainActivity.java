package com.kpinfotech.activity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;

import com.kpinfotech.R;
import com.kpinfotech.global.AppMethod;

import java.util.Date;

public class MainActivity extends Activity {

    TextView tvcalllog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvcalllog = (TextView) findViewById(R.id.tvcalllog);
        getCallDetails();
    }

    private void getCallDetails() {

        try {

            StringBuffer sb = new StringBuffer();

            //reading all data in descending order according to DATE
            String strOrder = android.provider.CallLog.Calls.DATE + " DESC";

            Uri callUri = Uri.parse("content://call_log/calls");
            Cursor cursor = getContentResolver().query(callUri, null, null, null, strOrder);
            // loop through cursor

            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            cursor.moveToFirst();
            while (cursor.moveToNext()) {

                String callNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date(Long.valueOf(callDate));
                String callDuration = cursor.getString(duration);

                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }
                sb.append("\nPhone Number:--- " + callNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
                sb.append("\n----------------------------------");

            }
            cursor.close();
            tvcalllog.setText(sb);

        } catch (Exception e) {
            AppMethod.showToast(MainActivity.this, e.getMessage());
        }

    }
}