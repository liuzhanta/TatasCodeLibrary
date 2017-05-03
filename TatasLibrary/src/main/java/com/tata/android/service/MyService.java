package com.tata.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    
    public final String TAG = "MyService";
    public static final String ACTION = "com.tata.sevice";
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "onCreate");
        new Thread(test).start();
    }
    
    private Runnable test = new Runnable()
    {
        
        @Override
        public void run() {
            Log.d(TAG, "线程运行中。。。");
            int count = 0;
            while (true) {
                Log.d(TAG, "RUN——" + count++);
                try {
                    Thread.sleep(1 * 2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "#onStartCommand");
        Log.d(TAG, "#startId =  " + startId);
//        return START_REDELIVER_INTENT;
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
    
}
