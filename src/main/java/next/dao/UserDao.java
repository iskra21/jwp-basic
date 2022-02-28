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
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
		    	pstmt.setString(1, user.getPassword());
		    	pstmt.setString(2, user.getName());
		    	pstmt.setString(3, user.getEmail());
		    	pstmt.setString(4, user.getUserId());
			}
    	};
    	String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
    	template.executeUpdate(sql, pss);
    }

    public List<User> findAll() throws SQLException {
    	JdbcTemplate template = new JdbcTemplate();
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				return;
			}
    	};
    	RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				ArrayList<User> users = new ArrayList<User>();
				while (rs.next()) {
					users.add(new User(rs.getString("userId"), rs.getString("password"),
							rs.getString("name"), rs.getString("email")));
				}
				return users;
			}
    	};
    	String sql = "SELECT userId, password, name, email FROM USERS";
    	return (List<User>)template.executeQuery(sql, pss, rm);
    }

    public User findByUserId(String userId) throws SQLException {
    	JdbcTemplate template = new JdbcTemplate();
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
	            pstmt.setString(1, userId);
			}
    	};
    	RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
	            User user = null;
	            if (rs.next()) {
	                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
	                        rs.getString("email"));
	            }

	            return user;
			}
    	};
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    	return (User)template.executeQuery(sql, pss, rm);
    }
}
