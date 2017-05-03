package com.tata.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * 
 * 名称： AppFirstInstall.java
 * 
 * 描述：程序初次安装信息
 * 
 * 修改时间：2013-6-17 下午5:05:09
 * 
 * @author lzt
 * 
 */
public class AppFirstInstall {
	/**
	 * 存放程序是否初次安装值
	 * 
	 * @param context
	 * @param isFirstInstall
	 * @modifiedTime：2013-6-17 下午5:04:48
	 * @author lzt
	 */
	public static void setFirstInstall(Context context, boolean isFirstInstall) {
		SharedPreferences sp = context.getSharedPreferences("init",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean("isFirstInstall", isFirstInstall);
		edit.commit();
	}

	/**
	 * 程序是否是初次
	 * 
	 * @param context
	 * @return true 是初次安装 ,false 非 初次安装
	 * @modifiedTime：2013-6-17 下午5:04:28
	 * @author lzt
	 */
	public static boolean isFirstInstall(Context context) {
		SharedPreferences sp = context.getSharedPreferences("init",
				Context.MODE_PRIVATE);
		return !sp.contains("isFirstInstall");
	}
}
