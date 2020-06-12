package springmvc.login.dao;

//import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;

import springmvc.login.util.DBConnection;

//import org.springframework.jdbc.core.JdbcTemplate;

import springmvc.login.model.User;
import springmvc.login.dao.UserDao;
public class UserDaoImpl implements UserDao {

//	private JdbcTemplate jdbcTemplate;
	@Autowired
	Connection con;
	
	public UserDaoImpl() {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.setDataSource(dataSource);
		con = DBConnection.createConnection(); 
	}

	@Override
	public int registerUser(User user) {
		
//		String sql = "INSERT INTO userInfo VALUES(?,?)";
		
		String userName  = user.getUserId();
		String password = user.getPassword();
		
		try {
			System.out.println("======================="+ userName);
//			int counter = jdbcTemplate.update(sql, user.getUserId(), user.getPassword());
			
			String query1 = "select email,password from userInfo where email='"+userName+"'";
			
        	PreparedStatement pst = con.prepareStatement(query1);
        	
        	ResultSet rs = pst.executeQuery();
        	
			if(rs.next()) {
				return -1;
			}
			query1 = "insert into userInfo values('"+userName+"', '"+password+"')";
			pst = con.prepareStatement(query1);
			
			pst.executeUpdate();
			
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String loginUser(User user) {
		
//		String sql = "SELECT email FROM userInfo WHERE email=? AND password=?";
		
		String userName  = user.getUserId();
		String password = user.getPassword();
//		try {
//
//			String userId = jdbcTemplate.queryForObject(sql, new Object[] {
//					user.getUserId(), user.getPassword() }, String.class);
//
//			return userId;
//			
//		} catch (Exception e) {
//			return null;
//		}
		try
        {
            Statement statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            ResultSet resultSet = statement.executeQuery("select email,password from userInfo"); //the table name is users and userName,password are columns. Fetching all the records and storing in a resultSet.

            while(resultSet.next()) // Until next row is present otherwise it return false
            {
             String userNameDB = resultSet.getString("userName"); //fetch the values present in database
             String passwordDB = resultSet.getString("password");

              if(userName.equals(userNameDB) && password.equals(passwordDB))
              {
                 return userName; //If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
              }
            }
        } catch(SQLException e)
            {
               e.printStackTrace();
            }
        return null; // Return appropriate message in case of failure
	}
}
