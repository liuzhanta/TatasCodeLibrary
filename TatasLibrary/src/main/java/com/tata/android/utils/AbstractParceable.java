package com.tata.android.utils;

import android.os.Parcel;
import android.os.Parcelable;

public  class AbstractParceable implements Parcelable {
    
    private int mData;
    private String mStr;
    
    public AbstractParceable(Parcel in) {
        mData = in.readInt();
        mStr = in.readString();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
    }
    
    // 用来创建自定义的Parcelable的对象
    public static final Creator<AbstractParceable> CREATOR = new Creator<AbstractParceable>()
    {

        public AbstractParceable createFromParcel(Parcel in) {
            return new AbstractParceable(in);
        }
        
        public AbstractParceable[] newArray(int size) {
            return new AbstractParceable[size];
        }
        
    };
}
