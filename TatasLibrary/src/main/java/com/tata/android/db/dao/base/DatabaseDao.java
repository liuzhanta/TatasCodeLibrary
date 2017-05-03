package com.tata.android.db.dao.base;

import java.util.List;
import java.util.Map;

import android.os.Handler;

public interface DatabaseDao {

	/**
	 * 将 map类型 的数据集插入到指定表中
	 * 
	 * @author luyuzhi
	 * @param tableName
	 *            表名
	 * @param map
	 *            数据集
	 * @return
	 */
	public boolean insertMap(Map<String, Object> map);

	/**
	 * 将bean对象插入指定表中
	 * 
	 * @author luyuzhi
	 * @param obj
	 * @return
	 */
	public boolean insertObj(Object obj);

	/**
	 * 删除表中指定id的记录
	 * 
	 * @param whereClause
	 *            表中ID的字段名
	 * @param id
	 * @return
	 * @modifiedTime：2013-6-7 下午3:25:48
	 * @author lzt
	 */
	public boolean delectById(String columnId, String id);

	/**
	 * 修改指定表中的指定id的指定字段的内容
	 * 
	 * @param columnId
	 * @param id
	 * @param params
	 *            params[0] 需要修改的字段名 params[1] 需要修改的字段值
	 * @return
	 * @modifiedTime：2013-6-8 下午4:34:28
	 * @author lzt
	 */
	public boolean updateByParams(String columnId, String id, String... params);

	/**
	 * 根据id修改指定表中的一组数据
	 * 
	 * @param map
	 * @param whereID
	 *            表中的id字段
	 * @param id
	 * @return
	 * @modifiedTime：2013-6-7 下午3:42:55
	 * @author lzt
	 */
	public boolean updateByMap(Map<String, Object> map, String whereID,
			String id);

	/**
	 * 查看指定表中的指定数据，并将结果接封装到实体bean中
	 * 
	 * @param cls
	 * @param columnId
	 * @return
	 * @modifiedTime：2013-6-8 下午2:09:00
	 * @author lzt
	 */
	public List<Object> selectByClass(Class<?> cls, String columnId, String id);

	/**
	 * 查看指定表中的指定字段一组数据
	 * 
	 * @param columns
	 * @param columnId
	 *            表中id名字
	 * @param id
	 *            传进来id值
	 * @return
	 * @modifiedTime：2013-6-8 下午4:12:26
	 * @author lzt
	 */
	public Map<String, Object> selectByMap(List<String> columns,
			String columnId, String id);

	/**
	 * 查看指定表中指定字段下的值
	 * 
	 * @param param
	 * @param columnId
	 *            表中的id
	 * @param id
	 * @return
	 * @modifiedTime：2013-6-7 下午4:25:17
	 * @author lzt
	 */
	public String selectByParams(String param, String columnId, String id);

	/**
	 * 
	 * @author luyuzhi
	 * @param param
	 * @param columnId
	 * @param id
	 * @return
	 */
	public List<Object> selectByParamsReturnSet(String param, String columnId,
			String id);

	/**
	 * 查看指定下的值通过当params[0] = params[1]
	 * 
	 * @param columnName
	 *            要查询的字段
	 * @param params
	 *            params[0]代表字段名， params[1]代表值
	 * @return key =字段名，value查询的值
	 * @modifiedTime：2013-6-7 下午4:07:33
	 * 
	 * @author lzt
	 */
	public String selectByParams(String columnName, String... params);

	/**
	 * 指定一段sql语句
	 * 
	 * @author luyuzhi
	 * @param sql
	 * @return
	 */
	public boolean execSql(String sql);

	public void setTableName(String tableName);

	/**
	 * 检查该记录是否存在
	 * 
	 * @author luyuzhi
	 * @param whereID
	 *            字段名
	 * @param id
	 *            字段值域
	 * @return
	 */
	public boolean checkIdExit(String whereID, String id);

	/**
	 * 
	 * @author luyuzhi
	 * @param obj
	 * @param whereID
	 * @param id
	 * @return
	 */
	public boolean updateByClass(Object obj, String whereID, String id);

	/**
	 * 通过制定数据段查询数据库信息，返回所有查询值
	 * 
	 * @author fzs
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> selectByParams(String... params);

	/**
	 * 通过sql语句查询数据
	 * 
	 * @author fzs
	 * @param sql
	 * @return
	 */
	public String selectBySql(String param, String sql);

	/**
	 * 查看指定表中的指定字段一组数据
	 * 
	 * @param columns
	 * @param columnId
	 *            表中id名字
	 * @param id
	 *            传进来id值
	 * @return
	 * @modifiedTime：2013-6-8 下午4:12:26
	 * @author luyuzhi
	 */
	public List<Map<String, Object>> selectByList(List<String> columns,
			String columnId, String id);

	/**
	 * 执行多条sql语句
	 * 
	 * @param sqlList
	 * @return
	 * @modifiedTime：2013-6-20 上午11:51:12
	 * @author lzt
	 */
	public boolean execMultiSQL(List<String> sqlList);

	/**
	 * 执行多条sql语句
	 * 
	 * @param sqlArray
	 * @return
	 * @modifiedTime 上午10:06:40
	 * @author lzt
	 */
	public boolean execMultiSQL(String[] sqlArray);
	/**
	 * 执行多条sql语句
	 * 
	 * @param sqlArray
	 * @return
	 * @modifiedTime 上午10:06:40
	 * @author lzt
	 */
	public boolean execMultiSQL(String[] sqlArray,Handler handler);
	/**
	 * 
	 * @author luyuzhi
	 * @param columnId
	 * @param id
	 * @return
	 */
	public List<Map<String, String>> selectByParamReturnAllSet(String columnId,
			String id);

	/**
	 * 自己写sql，返回list
	 * 
	 * @author fzs
	 * @param columns
	 * @param Sql
	 * @return
	 */
	List<Map<String, Object>> selectBySql(List<String> columns, String Sql);

	/**
	 * 通过sql查询所有值
	 * 
	 * @author fzs
	 * @param sql
	 * @return
	 */
	List<Map<String, String>> selectAllBySql(String sql);

}
