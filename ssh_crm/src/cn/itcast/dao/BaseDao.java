package cn.itcast.dao;

import java.util.List;

/**
 * @author Leslie
 * 定义类型是T 可代表任意类型 
 * 任意大写字母都可表示任意类型
 */
public interface BaseDao<T> {
	void add(T t);
	void update(T t);
	void delete(T t);
	T findOne(int id);
	List<T> findAll();
}
