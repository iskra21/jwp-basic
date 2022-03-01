package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;

public class JdbcTemplate {
	public <T> T executeQuery(String sql, RowMapper<T> rm, Object...values) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	con = ConnectionManager.getConnection(); 
        	pstmt = con.prepareStatement(sql);
        	for (int i = 0; i < values.length; i++) {
        		pstmt.setObject(i+1, values[i]);
        	}
        	//pss.setValues(pstmt);
        	rs = pstmt.executeQuery();
        	return rm.mapRow(rs);
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        } finally {
        	try {
        		if (rs != null) {
        			rs.close();
        		}

        		if (pstmt != null) {
        			pstmt.close();
        		}

        		if (con != null) {
        			con.close();
        		}
        	} catch (SQLException e) {
        		throw new DataAccessException(e);
        	}
        }
	}
	
	public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	con = ConnectionManager.getConnection(); 
        	pstmt = con.prepareStatement(sql);
        	pss.setValues(pstmt);
        	rs = pstmt.executeQuery();
        	return rm.mapRow(rs);
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        } finally {
        	try {
        		if (rs != null) {
        			rs.close();
        		}

        		if (pstmt != null) {
        			pstmt.close();
        		}

        		if (con != null) {
        			con.close();
        		}
        	} catch (SQLException e) {
        		throw new DataAccessException(e);
        	}
        }
	}

	public void executeUpdate(String sql, Object...values) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
        	for (int i = 0; i < values.length; i++) {
        		pstmt.setObject(i+1, values[i]);
        	}
        	
        	pstmt.executeUpdate();
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }
	}	
	
	public void executeUpdate(String sql, PreparedStatementSetter pss) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
        	pss.setValues(pstmt);
        	
        	pstmt.executeUpdate();
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }
	}

}
