package cn.itcast.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	// 查询记录数
	@SuppressWarnings("all")
	public int findCount() {
		// 调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		// 从list中把值得到
		if (list != null && list.size() != 0) {
			Object obj = list.get(0);
			// 变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	// 分页查询操作
	@SuppressWarnings("all")
	public List<Customer> findPage(int begin, int pageSize) {

		// 使用离线对象和hibernateTemplate的方法实现
		// 1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

		// 调用hibernateTemplate的方法实现
//		第一个参数是离线对象
//		第二个参数是开始位置
//		第三个参数是每页记录数
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	// 条件查询
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {

		// 第三种方式
		// 1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 2 设置对实体类哪个属性
		criteria.add(Restrictions.like("custName", "%" + customer.getCustName() + "%"));
		// 3 调用hibernateTemplate里面的方法得到list集合
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	// 多条件组合查询
	@SuppressWarnings("all")
	public List<Customer> findMoreCondition(Customer customer) {
		// 1.使用hiberbateTemplate的find()
		String hpl = "from Customer where 1=1";
		List<Object> p = new ArrayList<Object>();
		if (customer.getCustName() != null && !"".equals(customer.getCustName())) {
			// 拼接hpl
			hpl += "and custName=?";
			p.add(customer.getCustName());
		}
		if (customer.getDictCustLevel() != null && !"".equals(customer.getDictCustLevel())) {
			hpl += "and custLevel=?";
			p.add(customer.getDictCustLevel());
			
		}
		if (customer.getCustSource() != null && !"".equals(customer.getCustSource())) {
			hpl += "and custSource=?";
			p.add(customer.getCustSource());
		}
		return (List<Customer>) this.getHibernateTemplate().find(hpl, p.toArray());
	
	}

	public List<Dict> findAllDictCustLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	// 根据客户来源进行统计
	public List findCountSource() {
		// 对于数据库的复杂操作 建议直接用sql语句实现
		// 1.得到sessionFactory
		Session session = this.getSessionFactory().getCurrentSession();
		// 2.create SQL Query Object
		String sql = "SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		return sqlQuery.list();
	}
	//根据客户级别进行统计
	public List findCountLevel() {
		Session session = this.getSessionFactory().getCurrentSession();
		String sql = "SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num, custLevel FROM t_customer GROUP BY custLevel) c,"
				+ "t_dict d WHERE c.custLevel = d.did";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		return sqlQuery.list();
	}
}
