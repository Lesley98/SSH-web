package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.entity.Visit;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String toSelectVisitPage() {
		return "toSelectVisitPage";
	}
	
	public String moreCondition() {
		List<Visit> list = visitService.findMoreCondition(visit);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	public String toAddPage() {
		List<Customer> listCustomer = customerService.findAll();
		List<User> listUser = userService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		ServletActionContext.getRequest().setAttribute("listUser", listUser);
		return "toAddPage";
	}
	
	public String addVisit() {
		visitService.addVisit(visit);
		return "addVisit";
	}
	
	public String list() {
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}

    public String showVisit() {
    	int vid = visit.getVid();
    	Visit visit = visitService.findOne(vid);
    	List<Customer> listCustomer = customerService.findAll();
    	List<User> listUser = userService.findAll();
    	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
    	ServletActionContext.getRequest().setAttribute("listUser", listUser);
    	ServletActionContext.getRequest().setAttribute("visit", visit);
    	return "showVisit";
    }
    public String updateVisit() {
    	visitService.updateVisit(visit);
    	return "updateVisit";
    }
    public String delete() {
    	int vid = visit.getVid();
    	Visit visit = visitService.findOne(vid);
    	visitService.delete(visit);
    	return "delete";
    }

	
}
