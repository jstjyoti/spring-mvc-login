package springmvc.login.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import springmvc.login.model.User;
import springmvc.login.dao.UserDao;
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	public UserDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.setDataSource(dataSource);
	}

	@Override
	public int registerUser(User user) {
		
		String sql = "INSERT INTO userInfo VALUES(?,?)";

		try {
			System.out.println("======================="+ user.getUserId());
			int counter = jdbcTemplate.update(sql, user.getUserId(), user.getPassword());
			
			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String loginUser(User user) {
		
		String sql = "SELECT email FROM userInfo WHERE email=? AND password=?";
		
		try {

			String userId = jdbcTemplate.queryForObject(sql, new Object[] {
					user.getUserId(), user.getPassword() }, String.class);

			return userId;
			
		} catch (Exception e) {
			return null;
		}
	}
}
