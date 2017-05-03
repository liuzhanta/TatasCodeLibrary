package com.tata.android.db.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentValues;
import android.database.Cursor;

import com.tata.android.db.reflection.BeanUtils;

public class DBTools {

	public static final long RESULT_INSERT_FAILED = -1;// 数据库插入失败
	public static final int RESULT_UPDATE_FAILED = 0;// 数据库更新失败

	/**
	 * 根据cursor，列名获取值
	 * 
	 * 
	 * @param cursor
	 * @param columnName
	 * @return
	 * @modifiedTime：2013-6-5 下午6:10:24
	 * @author lzt
	 */
	public String getString(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		if (index >= 0) {
			return cursor.getString(index);
		} else {
			return "";
		}
	}

	/**
	 * 判断Cusor是否为空
	 * 
	 * @param cursor
	 * @return
	 * @modifiedTime：2013-5-15 下午5:48:58
	 * @author lzt
	 */
	public boolean judgeCursor(Cursor cursor) {
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断数据库插入操作结果
	 * 
	 * @param result
	 * @return
	 * @modifiedTime：2013-6-7 下午3:52:29
	 * @author lzt
	 */
	public boolean judgeResult(long result) {
		return result == RESULT_INSERT_FAILED ? false : true;
	}

	/**
	 * 判断数据库更新操作结果
	 * 
	 * @param result
	 * @return
	 * @modifiedTime：2013-6-7 下午3:52:29
	 * @author lzt
	 */
	public boolean judgeResult(int result) {
		return result == RESULT_UPDATE_FAILED ? false : true;
	}

	/**
	 * 将map对象转换ContentValues对象
	 * 
	 * @param map
	 * @return
	 * @modifiedTime：2013-6-7 下午3:40:35
	 * @author lzt
	 */
	public ContentValues convertMapToCV(Map<String, Object> map) {
		ContentValues values = new ContentValues();
		Set<String> clauses = map.keySet();
		for (String s : clauses) {
			Object fieldValue = map.get(s);
			if (fieldValue instanceof Date) {
//				values.put(s, com.tata.utils.StringUtils
//						.formatDateValues((Date) fieldValue));
			} else {
				values.put(s, fieldValue == null ? "" : fieldValue.toString());
			}
		}
		return values;
	}

	/**
	 * 将实体bean转化为contenvalues对象
	 * 
	 * @author luyuzhi
	 * @param obj
	 * @return
	 */
	public ContentValues ClassToContentValues(Object obj) {
		List<String> filedList = BeanUtils.getFields(obj.getClass());
		ContentValues values = new ContentValues();
		for (String fName : filedList) {
			Object fieldValue = BeanUtils.getValueByRef(obj, fName);
			if (fieldValue instanceof Date) {
//				values.put(fName, com.tata.utils.StringUtils
//						.formatDateValues((Date) fieldValue));
			} else {
				values.put(fName,
						fieldValue == null ? "" : fieldValue.toString());
			}

		}
		return values;
	}
}
