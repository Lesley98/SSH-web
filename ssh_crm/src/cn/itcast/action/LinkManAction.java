package cn.itcast.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.dao.CustomerDaoImpl;
import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan = new LinkMan();

	public LinkMan getModel() {
		return linkMan;
	}

	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 到添加页面
	public String toAddPage() {
		// 将所有客户的list整合到页面的下拉列表中
		List<Customer> listCustomer = customerService.findAll();
		// 将listCustomer传送回页面 方法：将其放到域对象中
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}

	// 到列表页面
	public String list() {
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}	
	//修改界面
	public String showLinkMan() {
		int linkid = linkMan.getLinkid();
		LinkMan link = linkManService.findOne(linkid);
		//获取客户列表
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		ServletActionContext.getRequest().setAttribute("link", link);
		return "showLinkMan";
	}
	//修改功能
	public String updateLinkMan() {
		linkManService.updateLinkMan(linkMan);
		return "updateLinkMan";
	}
	//删除功能
	public String delete() {
		int linkid = linkMan.getLinkid();
		LinkMan linkMan = linkManService.findOne(linkid);
		linkManService.delete(linkMan);
		return "delete";
	}
	//条件查询
	public String listcondition() {
		if (linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())) {
			List<LinkMan> list = linkManService.findCondition(linkMan);
			ServletActionContext.getRequest().setAttribute("list", list);
		} else {
			List<LinkMan> list = linkManService.findAll();
			ServletActionContext.getRequest().setAttribute("list", list);

		}
		return "listcondition";
	}
	//多条件查询
	public String toSelectLinkManPage() {
		return "toSelectLinkManPage";
	}
	public String moreCondition() {
		List<LinkMan> list = linkManService.findMoreCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	// 上传文件
	private File upload;	//文件上传项的name值  表示文件
	private String uploadFileName;	//命名规范  表示文件名 
	
	// 添加数据到数据库
	public String addLinkMan() throws Exception {
		if(upload != null ) {
			File serverFile = new File("D:\\testfile"+"\\"+uploadFileName);
			FileUtils.copyFile(upload, serverFile);//把upload复制到serverFile中
		}
		linkManService.addLinkMan(linkMan);
		return "addLinkMan";
	}
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
