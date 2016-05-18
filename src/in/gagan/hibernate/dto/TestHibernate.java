package in.gagan.hibernate.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="test";
		UserDetails user = new UserDetails();
		UserLogin userLogin = new UserLogin();
		Address temporaryAddress = new Address();
		Address permanentAddress = new Address();
		
		userLogin.setUserName(name);
		userLogin.setPassword(name);
		
		temporaryAddress.setCity(name);
		temporaryAddress.setCountry(name);
		temporaryAddress.setFlatNo(name);
		temporaryAddress.setPincode(name);
		temporaryAddress.setState(name);
		temporaryAddress.setStreetName(name);
		
		permanentAddress.setCity(name);
		permanentAddress.setCountry(name);
		permanentAddress.setFlatNo(name);
		permanentAddress.setPincode(name);
		permanentAddress.setState(name);
		permanentAddress.setStreetName(name);
		
		user.setDob(new Date());
		user.setFirstName(name);
		user.setLastName(name);
		user.setPermanentAddress(permanentAddress);
		user.setPhoneNumber(name);
		user.setTemporaryAddress(temporaryAddress);
		user.setUserName(name);
		user.setUserLogin(userLogin);
		
		SessionFactory sessionFactroy = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactroy.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		session.getSessionFactory().close();
		
	}

}
