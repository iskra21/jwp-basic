package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) throws Exception {
    	JdbcTemplate template = new JdbcTemplate();
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
    	};
    	String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	template.executeUpdate(sql, pss);
    }
    
    public void update(User user) throws SQLException {
    	JdbcTemplate template = new JdbcTemplate();
    	String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
    	template.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
    	JdbcTemplate template = new JdbcTemplate();
    	RowMapper<List<User>> rm = (ResultSet rs) -> {ArrayList<User> users = new ArrayList<User>();
    	while (rs.next()) { users.add(new User(rs.getString("userId"), rs.getString("password"),
    			rs.getString("name"), rs.getString("email"))); }
    	return users; };
    	String sql = "SELECT userId, password, name, email FROM USERS";
    	return template.executeQuery(sql, rm);
    }

    public User findByUserId(String userId) {
    	JdbcTemplate template = new JdbcTemplate();
    	RowMapper<User> rm = (ResultSet rs) -> { 
    		User user = null;
    		if (rs.next()) { user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
    				rs.getString("email"));
    		}
    		return user; 
    	};
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    	return template.executeQuery(sql, rm, userId);
    }
}
