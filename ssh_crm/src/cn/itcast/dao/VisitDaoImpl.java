package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}

	public List<Visit> list() {
		return (List<Visit>)this.getHibernateTemplate().find("from Visit");
	}
	public List<Visit> findAll() {
		return (List<Visit>)this.getHibernateTemplate().find("from Visit");
	}

	public void update(Visit visit) {
		this.getHibernateTemplate().update(visit);
	}

	public Visit findOne(int vid) {
		return this.getHibernateTemplate().get(Visit.class, vid);
	}

	public void delete(Visit visit) {
		this.getHibernateTemplate().delete(visit);
	}

	public List<Visit> findMoreCondition(Visit visit) {
			// 1.使用hiberbateTemplate的find()
			String hpl = "from Visit where 1=1";
			List<Object> p = new ArrayList<Object>();
			if (visit.getVaddress() != null && !"".equals(visit.getVaddress())) {
				hpl += "and vaddress=?";
				p.add(visit.getVaddress());
			}
			if (visit.getVcontent()!= null && !"".equals(visit.getVcontent())) {
				hpl += "and vcontent=?";
				p.add(visit.getVcontent());
			}
			return (List<Visit>) this.getHibernateTemplate().find(hpl, p.toArray());
		}
	}

