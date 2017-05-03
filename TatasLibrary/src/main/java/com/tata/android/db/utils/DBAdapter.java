package com.tata.android.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBAdapter extends DBTools implements DbUtils {

	public SQLiteDatabase db;
	public MyDBHelper mDBHelper;

	public DBAdapter(Context context) {
		mDBHelper = new MyDBHelper(context);
	}

	/**
	 * 打开数据库
	 * 
	 * @modifiedTime 下午5:55:33
	 * @author lzt
	 */
	public void open() {
		synchronized (this) {
			try {
				db = mDBHelper.getWritableDatabase();
			} catch (SQLiteException e) {
				System.out.println("e:" + e);
				db = mDBHelper.getReadableDatabase();
			}
		}
	}

	/**
	 * 关闭数据库
	 * 
	 * @modifiedTime 下午5:55:51
	 * @author lzt
	 */
	public void close() {
		db.close();
		mDBHelper.close();
	}

	public void execSql(BaseDao dao) {
		this.open();
		dao.execSql();
		this.close();
	}

	public abstract interface BaseDao {
		public abstract void execSql();
	}

}