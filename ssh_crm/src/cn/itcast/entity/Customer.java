package cn.itcast.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer cid;
	private String custName;
	private String custSource;
	private String custLinkman;
	private String custPhone;
	private String custMobile;
    private Dict dictCustLevel;
    
	public Dict getDictCustLevel() {
		return dictCustLevel;
	}
	public void setDictCustLevel(Dict dictCustLevel) {
		this.dictCustLevel = dictCustLevel;
	}
	//客户和联系人是一对多的关系
	//表示一个客户拥有一个由有多个联系人组成的set
	private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
	
	public Set<LinkMan> getSetLinkMan() {
		return setLinkMan;
	}
	public void setSetLinkMan(Set<LinkMan> setLinkMan) {
		this.setLinkMan = setLinkMan;
	}
	//客户被拜访集合
	private Set<Visit> setCusVisit = new HashSet<Visit>();
	public Set<Visit> getSetCusVisit() {
		return setCusVisit;
	}
	public void setSetCusVisit(Set<Visit> setCusVisit) {
		this.setCusVisit = setCusVisit;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
 
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustLinkman() {
		return custLinkman;
	}
	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
}
