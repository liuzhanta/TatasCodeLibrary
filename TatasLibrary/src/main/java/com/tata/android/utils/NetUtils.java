package com.tata.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetUtils {
	/**
	 * 检查网络是否通
	 * 
	 * @param context
	 * @return
	 * @Author lzt
	 */
	public static boolean isNetAvialable(Context context) {
		return isWiFiActive(context) || isNetworkAvailable(context);
	}

	/**
	 * 检查wifi是否激活
	 * 
	 * @param inContext
	 * @return
	 * @Author lzt
	 */
	public static boolean isWiFiActive(Context inContext) {
		WifiManager mWifiManager = (WifiManager) inContext
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
		int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
		if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
			System.out.println("**** WIFI is on");
			return true;
		} else {
			System.out.println("**** WIFI is off");
			return false;
		}
	}

	/**
	 * 检查网络是否连通
	 * 
	 * @param context
	 * @return
	 * @Author lzt
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			System.out.println("**** newwork is off");
			return false;
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info == null) {
				System.out.println("**** newwork is off");
				return false;
			} else {
				if (info.isAvailable()) {
					System.out.println("**** newwork is on");
					return true;
				}
			}
		}
		System.out.println("**** newwork is off");
		return false;
	}

	/**
	 * 判断是否是gprs网络
	 * 
	 * @param context
	 * @return
	 * @modifiedTime：2013-4-25 下午3:57:49
	 * @author lzt
	 */
	public static boolean isGPRS(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			System.out.println("GPRS IS ON");
			return true;
		}
		System.out.println("GPRS IS OFF");
		return false;
	}
}
