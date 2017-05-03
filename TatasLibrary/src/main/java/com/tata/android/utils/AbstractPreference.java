package com.tata.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public abstract class AbstractPreference<T> {
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private T value;
    private String key;
    private static final String DEFAULT_SP_FILE_NAME = "com.tata.sharedpreferences";
    
    public AbstractPreference(Context context, String key) {
        this.context = context;
        sp = context.getSharedPreferences(DEFAULT_SP_FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    
    public synchronized SharedPreferences getSharePreferences() {
        return sp;
    }
    
    public synchronized SharedPreferences.Editor getEditor() {
        return editor;
    }
    
    public abstract void setValue(T data);
    
    public abstract T getValue();
    
    public void save(String key, Object value) {
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            // editor.putStringSet(key, (Set<String>) value);
        }
        editor.commit();
    }
}