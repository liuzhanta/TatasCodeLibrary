package com.tata.android.utils;

import android.content.Context;

public class SharedPreferenceManager {
    
    public static class StringPreference extends AbstractPreference<String> {
        
        public StringPreference(Context context, String key) {
            super(context, key);
        }
        
        @Override
        public void setValue(String data) {
        }
        
        @Override
        public String getValue() {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
