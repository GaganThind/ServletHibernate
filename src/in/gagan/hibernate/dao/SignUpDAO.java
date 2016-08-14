package in.gagan.hibernate.dao;

public interface SignUpDAO {

	public boolean Register(String userName, String firstName, String lastName, String dob, String phoneNumber,
			String passwordHash, String salt);
	
	public String checkIfUsernameExists(String userName);
	
}
