package com.tata.android.db.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import com.tata.android.utils.StringUtils;

public class BeanUtils {

	/**
	 * 通过反射机制得到所给对象的值
	 * 
	 * @param srcObj
	 * @param fieldName
	 * @return
	 * @modifiedTime：2013-6-7 下午3:13:30
	 * @author lzt
	 */
	public static Object getValueByRef(Object srcObj, String fieldName) {
		Object value = null;
		Class<?> objClass = srcObj.getClass();
		fieldName = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName
				.substring(0, 1).toUpperCase());
		String getMethodName = "get" + fieldName;
		try {
			Method method = objClass.getMethod(getMethodName);
			value = method.invoke(srcObj);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}

	/**
	 * 通过反射机制给对象赋值
	 * 
	 * @param srcObj
	 * @param fieldName
	 * @return
	 * @modifiedTime：2013-6-7 下午3:13:30
	 * @author lzt
	 */
	public static void setValueByRef(Object srcObj, String fieldName,
			Object value) {
		Class<?> objClass = srcObj.getClass();
		try {
			Field field = objClass.getDeclaredField(fieldName);
			fieldName = fieldName.replaceFirst(fieldName.substring(0, 1),
					fieldName.substring(0, 1).toUpperCase());
			String setMethodName = "set" + fieldName;
			Method method = objClass.getDeclaredMethod(setMethodName,
					new Class[] { field.getType() });
			if (value != null) {
				value = formatValue(field.getType(), value);
			}
			method.invoke(srcObj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转化数据类型
	 * 
	 * @author luyuzhi
	 * @param classType
	 * @param value
	 * @return
	 */
	public static Object formatValue(Class<?> classType, Object value) {
		if (classType.equals(Long.class)) {
			if (StringUtils.isEmpty(value.toString())) {
				return null;
			}
			return Long.valueOf(value.toString());
		} else if (classType.equals(Date.class)) {
			try {
				return new Date(Date.parse(value.toString().replace("-", "/")));
			} catch (Exception e) {
				return null;
			}
		} else if (classType.equals(String.class)) {
			return value;
		} else if (classType.equals(Integer.class)) {
			try {
				return Integer.valueOf(value.toString());
			} catch (NumberFormatException e) {
				return null;
			}
		} else if (classType.equals(Float.class)) {
			try {
				return Float.valueOf(value.toString());
			} catch (NumberFormatException e) {
				return null;
			}
		} else if (classType.equals(Double.class)) {
			try {
				return Double.valueOf(value.toString());
			} catch (NumberFormatException e) {
				return null;
			}
		} else if (classType.equals(Boolean.class)) {
			try {
				return Boolean.parseBoolean(value.toString());
			} catch (Exception e) {
			}
		}
		return value;
	}

	/**
	 * 得到对象的所有属性名
	 * 
	 * @param obj所传对象
	 * @return
	 * @modifiedTime：2013-6-7 下午2:35:37
	 * @author lzt
	 */
	public static ArrayList<String> getFields(Class<?> cls) {
		ArrayList<String> fieldList = new ArrayList<String>();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			fieldList.add(f.getName());
		}
		return fieldList;
	}

	/**
	 * bean变量值转化为数据库中字段值
	 * 
	 * @author luyuzhi
	 * @param fieldName
	 * @return
	 */
	public static String fieldToParams(String fieldName) {

		for (char a : fieldName.toCharArray()) {
			if (a >= 65 && a <= 90) {
				fieldName = fieldName.replaceFirst(String.valueOf(a), "_" + a);
			}
		}
		fieldName = fieldName.toUpperCase();
		return fieldName;
	}

	/**
	 * 将数据库字段值转化为bean变量名
	 * 
	 * @author luyuzhi
	 * @param params
	 * @return
	 */
	public static String paramsToField(String params) {
		params = params.toLowerCase();

		while (params.contains("_")) {
			int index = params.indexOf("_");
			params = params.replaceFirst("_", "");
			String mess = params.subSequence(index, index + 1).toString();
			char[] tempArray = params.toCharArray();
			int temp = mess.toCharArray()[0] - 32;
			tempArray[index] = (char) temp;
			params = String.valueOf(tempArray);
		}
		// Pay Attention : In the state of the Bean Class ' s taskId is not
		// equals to the database's
		params = "taskno".equals(params) ? "taskNo" : params;
		return params;
	}

	public static Object getData(String params, Class classParams, Object obj)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method m[] = classParams.getDeclaredMethods();
		for (int i = 0; i < m.length; i++) {
			// System.out.println(m[i].getName());
			String methodName = m[i].getName();
			boolean flag = (!methodName.startsWith("set"))
					&& methodName
							.contains(params.substring(1, params.length()));
			if (flag) {
				Method method = classParams.getDeclaredMethod(methodName);
				return method.invoke(obj);
			}
		}
		return null;
	}

	/**
	 * 
	 * 为bean对象的字段赋值
	 * 
	 * @author luyuzhi
	 * @param params
	 *            字段名
	 * @param classParams
	 *            类对象
	 * @param obj
	 *            bean对象
	 * @param value
	 *            所要的修改的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
	public static void setData(String params, Class classParams, Object obj,
			Object value) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException {
		Method m[] = classParams.getDeclaredMethods();

		for (int i = 0; i < m.length; i++) {

			String methodName = m[i].getName();
			if (methodName.startsWith("set")
					&& methodName
							.contains(params.substring(1, params.length()))) {
				//
				Field field = classParams.getDeclaredField(params);
				Method method = classParams.getDeclaredMethod(methodName,
						new Class[] { field.getType() });
				method.invoke(obj, value);
				return;
			}
		}
	}

}
