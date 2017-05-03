package com.tata.android.utils;

public class CancleableRunnable implements Runnable {
    
    /** a thread that controls thread to be cancled */
    private volatile Thread blinker;
    
    public void cancle() {
        Thread tmpBlinker = blinker;
        blinker = null;
        if (tmpBlinker != null) {
            tmpBlinker.interrupt();
        }
    }
    
    @Override
    public void run() {
        setThread(Thread.currentThread());
    }
    
    private void setThread(Thread thread) {
        while (blinker == thread) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                try {
                    throw new RuntimeException("Interrupted", e);
                } catch (RuntimeException re) {
                }
            }
        }
    }
}
