package in.gagan.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import in.gagan.common.util.HibernateUtil;
import in.gagan.common.util.LoggingUtil;
import in.gagan.hibernate.dto.UserLogin;

public class LoginDAO {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	public boolean Authenticate(String userName, String password) {
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(UserLogin.class);
			criteria.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password));
			@SuppressWarnings("unchecked")
			List<UserLogin> user = (List<UserLogin>) criteria.list();
			if (user.size() > 0 && !user.isEmpty()) {
				return true;
			}			
		}catch(Exception e){
			logger.error("LoginDAO.Authentication error. Cannot authenticate user" + e);
			session.getTransaction().rollback();
		}finally {
			HibernateUtil.commitTransaction(session);	
		}
		return false;		
	}
}
