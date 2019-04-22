package cn.itcast.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class pClass;
	//Construtor
	public BaseDaoImpl() {
		super();
		//1.得到当前运行类的class
		Class clazz = this.getClass();
	    //2.得到当前运行类的父类的 参数化类型	type导java.lang.reflect 
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		//3.得到实际类型参数 就是<>里面的数据类型 返回值是数组 因为可能是Map
		Type[] types = ptype.getActualTypeArguments();
		Class tclass = (Class)types[0];
		this.pClass = tclass;
	}
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	public List<T> findAll() {
		return (List<T>)this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}

}
