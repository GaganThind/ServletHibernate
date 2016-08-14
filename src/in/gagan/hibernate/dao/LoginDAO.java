package in.gagan.hibernate.dao;

import java.util.Map;

public interface LoginDAO {

	public Map<String,String> getUserPasswordAndSalt(String userName);
	
}
