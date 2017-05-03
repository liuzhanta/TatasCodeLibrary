package com.tata.android.db.utils;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper implements DbUtils {
	

	public static final String DATABASE_NAME = "hdxf.db";
	public static final int DATABASE_VERSION = 4; 

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
//			db.execSQL(CREATE_FH_TABLE);
//			db.execSQL(CREATE_FILE_TABLE);
//			db.execSQL(CREATE_LOCATION_TABLE);
//			db.execSQL(CREATE_USER_TABLE);
//			db.execSQL(CREATE_PUBLICITY_TABLE);
//			db.execSQL(CREATE_NOTIFICATION_TABLE);
//			db.execSQL(CREATE_VERIFY_TABLE);
//			db.execSQL(CREATE_MISSIONINFO_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
