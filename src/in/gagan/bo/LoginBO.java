package in.gagan.bo;

import in.gagan.hibernate.dao.LoginDAO;

public class LoginBO {

	public boolean Authenticate(String userName, String password) {
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.Authenticate(userName, password);
	}
}
