package com.tata.android.db.dao.base.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tata.android.db.dao.base.DatabaseDao;
import com.tata.android.db.reflection.BeanUtils;
import com.tata.android.db.utils.DBAdapter;

public class DatabaseImpl extends DBAdapter implements DatabaseDao {

	private String tableName;
	private String sql;

	public DatabaseImpl(Context context) {
		super(context);
	}

	@Override
	public boolean insertMap(Map<String, Object> map) {
		this.open();
		ContentValues values = convertMapToCV(map);
		long result = db.insert(tableName, null, values);
		this.close();
		return RESULT_INSERT_FAILED == result ? false : true;
	}

	@Override
	public boolean insertObj(Object obj) {
		this.open();
		ContentValues values = ClassToContentValues(obj);
		long result = db.insert(tableName, null, values);
		this.close();
		return judgeResult(result);
	}

	@Override
	public boolean updateByMap(Map<String, Object> map, String whereID,
			String id) {
		this.open();
		ContentValues values = convertMapToCV(map);
		int result = db.update(tableName, values, whereID + "=?",
				new String[] { id });
		this.close();
		return judgeResult(result);
	}

	@Override
	public boolean updateByParams(String columnId, String id, String... params) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(params[0], params[1]);
		int result = db.update(tableName, values, columnId + "=?",
				new String[] { id });
		this.close();
		return judgeResult(result);
	}

	@Override
	public String selectByParams(String columnName, String... params) {
		this.open();
		sql = "select " + columnName + " from " + tableName + " where "
				+ params[0] + "=" + params[1];
		String result = "";
		Cursor cursor = db.rawQuery(sql, null);
		if (judgeCursor(cursor)) {
			result = getString(cursor, columnName);
		}
		this.close();
		return result;
	}

	@Override
	public String selectByParams(String param, String columnId, String id) {
		this.open();
		sql = "select " + param + " from " + tableName + " where " + columnId
				+ "= '" + id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		String result = "";
		if (judgeCursor(cursor)) {
			result = getString(cursor, param);
		}
		this.close();
		return result;
	}

	@Override
	public List<Object> selectByClass(Class<?> cls, String columnId, String id) {
		this.open();
		sql = "select * from " + tableName + " where " + columnId + " = '" + id
				+ "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor == null)
			return null;
		Object obj = null;
		List<Object> objList = new ArrayList<Object>();
		List<String> fieldNameList = BeanUtils.getFields(cls);// �õ�������������,����Ϊ����
		try {
			// ����cursor
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				obj = cls.newInstance();
				// �����ֵ
				for (String fieldName : fieldNameList) {
					Field field = cls.getDeclaredField(fieldName);
					String value = getString(cursor,
							BeanUtils.fieldToParams(fieldName));
					BeanUtils.setValueByRef(obj, fieldName, value);
				}
				objList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return objList;
	}

	@Override
	public Map<String, Object> selectByMap(List<String> columns,
			String columnId, String id) {
		this.open();
		String[] arrays = new String[] {};
		arrays = columns.toArray(arrays);
		Cursor cursor = db.query(tableName, arrays, columnId + "=?",
				new String[] { id }, null, null, null);
		Map<String, Object> map = null;
		if (judgeCursor(cursor)) {
			map = new HashMap<String, Object>();
			for (String key : columns) {
				map.put(key, getString(cursor, key));
			}
		}
		this.close();
		return map;
	}

	@Override
	public List<Map<String, Object>> selectBySql(List<String> columns,
			String Sql) {
		this.open();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.rawQuery(Sql, null);
		if (cursor.getCount() != -1)
			cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (String str : columns) {
				map.put(str, getString(cursor, str));
			}
			list.add(map);
			cursor.moveToNext();
		}
		this.close();
		return list;
	}

	@Override
	public boolean delectById(String columnId, String id) {
		this.open();
		int result = 0;
		result = db.delete(tableName, columnId + "=?", new String[] { id });
		this.close();
		return judgeResult(result);
	}

	@Override
	public boolean execSql(String sql) {
		this.open();
		db.execSQL(sql);
		this.close();
		return false;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public boolean checkIdExit(String whereID, String id) {
		open();
		String sql = "select * from " + tableName + " where " + whereID + "='"
				+ id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		boolean result = cursor.getCount() > 0;
		close();
		return result;
	}

	@Override
	public boolean updateByClass(Object obj, String whereID, String id) {
		open();
		ContentValues values = ClassToContentValues(obj);
		long result = db.update(tableName, values, whereID + "=?",
				new String[] { id });
		this.close();
		return judgeResult(result);
	}

	@Override
	public List<Map<String, String>> selectByParams(String... params) {
		// TODO Auto-generated method stub
		open();
		List<Map<String, String>> listt = new ArrayList<Map<String, String>>();
		String sql = "select * from " + tableName + " where " + params[0]
				+ "='" + params[1] + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() != -1)
			cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, String> map = new HashMap<String, String>();
			for (String str : cursor.getColumnNames()) {
				map.put(str, getString(cursor, str));
			}
			listt.add(map);
			cursor.moveToNext();
		}
		close();
		return listt;
	}

	@Override
	public List<Map<String, String>> selectAllBySql(String sql) {
		// TODO Auto-generated method stub
		open();
		List<Map<String, String>> listt = new ArrayList<Map<String, String>>();
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() != -1)
			cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, String> map = new HashMap<String, String>();
			for (String str : cursor.getColumnNames()) {
				map.put(str, getString(cursor, str));
			}
			listt.add(map);
			cursor.moveToNext();
		}
		close();
		return listt;
	}

	@Override
	public boolean execMultiSQL(List<String> sqlList) {
		boolean result = false;
		this.open();
		if (sqlList != null && sqlList.size() > 0) {
			db.beginTransaction(); // 手动设置开始事务
			System.out.println(">>>>>>>开始执行sql语句<<<<<<<");
			try {
				for (String s : sqlList) {
					db.execSQL(s);
					System.out.println(s);
				}
				db.setTransactionSuccessful();
				result = true;
			} catch (Exception e) {
				result = false;
			} finally {
				db.endTransaction(); // 处理完成
				// result = true;
			}
		} else {
			result = false;
		}
		this.close();
		System.out.println(">>>>>>>执行结束<<<<<<<");
		return result;
	}

	@Override
	public String selectBySql(String param, String sql) {
		// TODO Auto-generated method stub
		open();

		Cursor cursor = db.rawQuery(sql, null);
		String result = null;
		if (judgeCursor(cursor)) {
			result = getString(cursor, param);
		}
		this.close();
		return result;
	}

	@Override
	public List<Map<String, Object>> selectByList(List<String> columns,
			String columnId, String id) {
		this.open();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] arrays = new String[] {};
		arrays = columns.toArray(arrays);
		Cursor cursor = db.query(tableName, arrays, columnId + "=?",
				new String[] { id }, null, null, null);

		if (cursor.getCount() != -1)
			cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (String key : columns) {
				map.put(key, getString(cursor, key));
			}
			list.add(map);
			cursor.moveToNext();
		}
		this.close();
		return list;
	}

	@Override
	public List<Object> selectByParamsReturnSet(String param, String columnId,
			String id) {
		this.open();
		sql = "select " + param + " from " + tableName + " where " + columnId
				+ "= '" + id + "'";
		Cursor cursor = db.rawQuery(sql, null);
		List<Object> list = new ArrayList<Object>();
		if (cursor.getCount() != -1)
			cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			list.add(getString(cursor, param));
			cursor.moveToNext();
		}
		this.close();
		return list;
	}

	@Override
	public List<Map<String, String>> selectByParamReturnAllSet(String columnId,
			String id) {
		sql = "select * from " + tableName + " where " + columnId + "= '" + id
				+ "'";
		open();
		Cursor cursor = db.rawQuery(sql, null);
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		if (judgeCursor(cursor)) {
			while (!cursor.isAfterLast()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					String paramName = cursor.getColumnName(i);
					map.put(paramName, getString(cursor, paramName));
				}
				data.add(map);
				cursor.moveToNext();
			}
		}
		this.close();
		return data;
	}

	/**
	 * 判断数据库是否存在
	 * 
	 * @author luyuzhi
	 * @param context
	 * @return
	 */
	public static boolean databaseIsExist(Context context) {
		String packageName = context.getPackageName();
		String DB_PATH = String.format("/data/data/%1$s/databases/",
				packageName);
		return new File(DB_PATH).exists();
	}

	@Override
	public boolean execMultiSQL(String[] sqlArray) {
		boolean result = false;
		this.open();
		if (sqlArray != null && sqlArray.length > 0) {
			db.beginTransaction(); // 手动设置开始事务
			System.out.println(">>>>>>>开始执行sql语句<<<<<<<");
			try {
				for (String s : sqlArray) {
					db.execSQL(s);
					System.out.println(s);
				}
				db.setTransactionSuccessful();
				result = true;
			} catch (Exception e) {
				Log.e("error", "e=" + e);
				result = false;
			} finally {
				db.endTransaction(); // 处理完成
				this.close();
			}
		} else {
			result = false;
		}
		System.out.println(">>>>>>>执行结束<<<<<<<");
		return result;

	}

	@Override
	public boolean execMultiSQL(String[] sqlArray, Handler handler) {
		boolean result = false;
		int progress = 0;
		int length = 0;
		this.open();
		if (sqlArray != null && sqlArray.length > 0) {
			length = sqlArray.length;
			db.beginTransaction(); // 手动设置开始事务
			System.out.println(">>>>>>>开始执行sql语句<<<<<<<");
			try {
				for (String s : sqlArray) {
					if (s != null && !"".equals(s)) {
						db.execSQL(s);
						progress++;
						Message msg = new Message();
						msg.what = 1;
						msg.getData().putInt("size", progress);
						handler.sendMessage(msg);
						System.out.println(s);
						result = true;
					} else {
						result = false;
					}
				}
				db.setTransactionSuccessful();
				result = true;
			} catch (Exception e) {
				Log.e("error", "e=" + e);
				Message msg = new Message();
				msg.what = -1;
				handler.sendMessage(msg);
				result = false;
			} finally {
				db.endTransaction(); // 处理完成
				this.close();
			}
		} else {
			result = false;
		}
		System.out.println(">>>>>>>执行结束<<<<<<<");
		return result;

	}
}
