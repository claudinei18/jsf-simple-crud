package com.hello.entity;

import com.hello.dao.CustomerDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
/**
 * @author javaknowledge
 */
@ManagedBean
@SessionScoped
public class Customer implements java.io.Serializable {
 
    private Integer custId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String sd, msg, selectedname;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
    public Customer() {
    }
 
    public Customer(String firstName, String lastName, String email, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }
 
    public String getSd() {
        return sd;
    }
 
    public void setSd(String sd) {
        this.sd = sd;
    }
 
    public Integer getCustId() {
        return this.custId;
    }
 
    public void setCustId(Integer custId) {
        this.custId = custId;
    }
 
    public String getFirstName() {
        return this.firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return this.lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return this.email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public Date getDob() {
        return this.dob;
    }
 
    public void setDob(Date dob) {
        this.dob = dob;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getSelectedname() {
        return selectedname;
    }
 
    public void setSelectedname(String selectedname) {
        this.selectedname = selectedname;
    }
 
    public void saveCustomer() {
        try {
            //Date d = sdf.parse(sd);
        	System.out.println(dob);
        	Date d = dob;
            System.out.println(d);
            this.dob = d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomerDao dao = new CustomerDao();
        dao.addCustomer(this);
        this.msg = "Member Info Saved Successfull!";
        clearAll();
    }
    public void updateCustomer() {
        try {
            Date d = sdf.parse(sd);
            System.out.println(d);
            this.dob = d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CustomerDao dao = new CustomerDao();
        dao.updateCustomer(this);
        this.msg = "Member Info Update Successfull!";
        clearAll();
    }
    public void deleteCustomer() {
        CustomerDao dao = new CustomerDao();
        dao.deleteCustomer(custId);
        this.msg = "Member Info Delete Successfull!";
        clearAll();
    }
 
    public List<Customer> getAllCustomers() {
        List<Customer> users = new ArrayList<Customer>();
        CustomerDao dao = new CustomerDao();
        users = dao.getAllCustomers();
        return users;
    }
 
    public void fullInfo() {
        CustomerDao dao = new CustomerDao();
        List<Customer> lc = dao.getCustomerById(selectedname);
        System.out.println(lc.get(0).firstName);
        this.custId = lc.get(0).custId;
        this.firstName = lc.get(0).firstName;
        this.lastName = lc.get(0).lastName;
        this.email = lc.get(0).email;
        this.dob = lc.get(0).dob;
        this.sd = sdf.format(dob);
    }
 
    private void clearAll() {
        this.firstName = "";
        this.lastName = "";
        this.sd = "";
        this.email = "";
        this.custId=0;
    }
}
