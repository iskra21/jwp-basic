package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;

public abstract class JdbcTemplate {

	public void executeUpdate(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = ConnectionManager.getConnection();
        	pstmt = con.prepareStatement(sql);
        	setValue(pstmt);
        	
        	pstmt.executeUpdate();
        } finally {
        	if (pstmt != null) {
        		pstmt.close();
        	}
        	
        	if (con != null) {
        		con.close();
        	}
        }		
	}

    abstract void setValue(PreparedStatement pstmt) throws SQLException;
}
