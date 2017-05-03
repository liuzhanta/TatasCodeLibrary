package com.tata.android.db.dao.impl;

import java.util.List;

import android.content.Context;

import com.tata.android.db.dao.UserDao;
import com.tata.android.db.utils.DBAdapter;
import com.tata.android.db.utils.DBAdapter.BaseDao;

public class UserDaoImpl extends DBAdapter implements UserDao {

	public UserDaoImpl(Context context) {
		super(context);
	}

	@Override
	public List<String> selectAll() {
		this.execSql(new BaseDao() {

			@Override
			public void execSql() {
				// 在里执行sql语句的操作
			}
		});
		return null;
	}

}
