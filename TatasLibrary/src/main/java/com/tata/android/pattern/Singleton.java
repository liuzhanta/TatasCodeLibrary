package com.tata.android.pattern;

/**
 * Singleton helper class for lazily initialization.
 *
 * Modeled after frameworks/base/include/utils/Singleton.h
 *
 */
public abstract class Singleton<T> {
    private T mInstance;
    
    protected abstract T create();
    
    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
