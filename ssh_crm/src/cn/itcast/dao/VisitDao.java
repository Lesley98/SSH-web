package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

	void update(Visit visit);

	Visit findOne(int vid);

	void delete(Visit visit);

	List<Visit> findMoreCondition(Visit visit);

}
