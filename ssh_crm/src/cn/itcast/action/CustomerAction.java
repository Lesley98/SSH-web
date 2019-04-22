package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;
import cn.itcast.entity.PageBean;
import cn.itcast.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	public Customer getModel() {
		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 条件查询的方法
	public String listcondition() {
		if (customer.getCustName() != null && !"".equals(customer.getCustName())) {
			// 不为空
			List<Customer> list = customerService.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list", list);
		} else {
			list = customerService.findAll();
		}
		return "listcondition";
	}

	
	//统计客户来源
	public String countSource() {
		List list = customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	//统计客户级别
	public String countLevel() {
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	// 到多条件查询客户页面
	public String toSelectCustomerPage() {
		return "toSelectCustomerPage";
	}

	// 多条件查询
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}

	// 使用属性封装获取当前页
	private Integer currentPage;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	// 分页的方法
	public String listPage() {
		PageBean pageBean = customerService.listpage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listPage";
	}

	// 1 到添加页面
	public String toAddPage() {
		List<Dict> listDict = customerService.findAllDictCustLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}

	// 2 添加的方法
	public String add() {
		// 添加逻辑
		customerService.add(customer);
		return "add";
	}

	// 定义list变量
	private List<Customer> list;

	// 生成变量的get方法
	public List<Customer> getList() {
		return list;
	}

	// 3 客户列表的方法
	public String list() {
		// 返回的list放到值栈中
		list = customerService.findAll();
		return "list";
	}

	// 4 删除的方法
	public String delete() {
		// 使用模型驱动获取表单提交的cid的值
		int cid = customer.getCid();
		// 删除的规范做法 1.根据id查询 2.删除
		Customer c = customerService.findOne(cid);
		customerService.delete(c);
		return "delete";
	}

// 		针对低版本火狐浏览器提出的解决方案
//		if(c != null) {
//			customerService.delete(c);
//		}
//		return "delete";
//	}

//	5 修改-根据id查询
	public String showCustomer() {
		int cid = customer.getCid();
		Customer c = customerService.findOne(cid);
		// 根據id查到的對象將其返回到頁面中 將其放到域對象中
		ServletActionContext.getRequest().setAttribute("customer", c);
		List<Dict> listCustDict = customerService.findAllDictCustLevel();
		ServletActionContext.getRequest().setAttribute("listCustDict", listCustDict);
		return "showCustomer";
	}

	// 6 修改的方法
	public String update() {
		customerService.update(customer);
		return "update";
	}
}