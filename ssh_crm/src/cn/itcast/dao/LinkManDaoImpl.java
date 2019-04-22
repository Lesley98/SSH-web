package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//添加联系人 
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	public List<LinkMan> list() {
		return (List<LinkMan>)this.getHibernateTemplate().find("from LinkMan");
		
	}
	//查询联系人
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class,linkid);
	}

	//修改联系人
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
	}

	//多条件查询
	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		String hpl = "from LinkMan where ";
		List<Object> p = new ArrayList<Object>();
		
		if(linkMan.getLkmGender()!=null && !"".equals(linkMan.getLkmGender())) {
			hpl += " lkmGender = ?";
			p.add(linkMan.getLkmGender());
		}
		return (List<LinkMan>) this.getHibernateTemplate().find(hpl, p.toArray());
	}

	public List<LinkMan> findCondition(LinkMan linkMan) {
			DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
			criteria.add(Restrictions.like("lkmName", "%" + linkMan.getLkmName() + "%"));
			List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
			return list;
		}

	@SuppressWarnings("all")
	public List<LinkMan> findAll() {
			return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
		}
	}

	
	
	
	
	
	
	
	
	
	
