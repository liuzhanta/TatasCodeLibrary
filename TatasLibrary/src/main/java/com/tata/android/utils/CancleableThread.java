package com.tata.android.utils;

public  class CancleableThread extends Thread implements CancleHandler {
    
    public CancleableThread() {
        super();
        // TODO Auto-generated constructor stub
    }


    /** a thread that controls thread to be cancled */
    private volatile Thread blinker;
    
    @Override
    public void cancle() {
        Thread tmpBlinker = blinker;
        blinker = null;
        if (tmpBlinker != null) {
            tmpBlinker.interrupt();
        }
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
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

interface CancleHandler {
    void cancle();
}