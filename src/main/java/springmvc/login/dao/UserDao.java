package springmvc.login.dao;

import springmvc.login.model.User;

public interface UserDao {
	public int registerUser(User user);

	public String loginUser(User user);
}
