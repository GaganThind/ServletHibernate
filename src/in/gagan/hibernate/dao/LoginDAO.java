package in.gagan.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import in.gagan.common.util.HibernateUtil;
import in.gagan.hibernate.dto.UserLogin;

public class LoginDAO {
	public boolean Authenticate(String userName, String password) {

		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(UserLogin.class);
		criteria.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password));
		@SuppressWarnings("unchecked")
		List<UserLogin> user = (List<UserLogin>) criteria.list();
		session.getTransaction().commit();
		session.close();

		if (user.size() > 0 && !user.isEmpty()) {
			return true;
		}
		return false;
	}
}
