package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list();

	LinkMan findOne(int linkid);

    void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

	List<LinkMan> findMoreCondition(LinkMan linkMan);

	List<LinkMan> findCondition(LinkMan linkMan);

	List<LinkMan> findAll();


}
