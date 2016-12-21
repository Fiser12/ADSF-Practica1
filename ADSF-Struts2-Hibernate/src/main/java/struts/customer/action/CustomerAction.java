package struts.customer.action;

import struts.customer.model.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerAction extends ActionSupport 
	implements ModelDriven{

	Customer customer = new Customer();
	List<Customer> customerList = new ArrayList<Customer>();
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public Object getModel() {
		return customer;
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	Session session = null;

	//save customer
	public String addCustomer() throws Exception{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Customer customer = new Customer();
		customer.setCreatedDate(new Date());
        customer.setAddress("Prueba");
        customer.setName("Hola");
		session.save(customer);
		tx.commit();
		customerList = null;
		customerList = session.createQuery("from Customer").list();
		return SUCCESS;
	}
	//list all customers
	public String listCustomer() throws Exception{
		
		//get hibernate session from the servlet context
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		session = sessionFactory.openSession();

		customerList = session.createQuery("from Customer").list();
		
		return SUCCESS;
	
	}
	
}