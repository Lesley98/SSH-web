package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.VisitDao;
import cn.itcast.entity.Visit;

@Transactional
public class VisitService {

	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void addVisit(Visit visit) {
		visitDao.add(visit);
	}

	public List<Visit> findAll() {
		return visitDao.findAll();
	}

	public void updateVisit(Visit visit) {
		visitDao.update(visit);
	}

	public Visit findOne(int vid) {
		return visitDao.findOne(vid);
	}

	public void delete(Visit visit) {
		visitDao.delete(visit);
	}

	public List<Visit> findMoreCondition(Visit visit) {
		return visitDao.findMoreCondition(visit);
	}
}
