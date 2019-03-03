package bean;

import java.sql.*;

public class Sql {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String url = "";
	//private String driverName="com.mysql.jdbc.Driver";
	//private String tableName="commodity";
	public void link(String userName, String userPasswd, String dbName) {
		try {
			//String url = "jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password"+userPasswd;
			url = "jdbc:mysql://localhost/" + dbName + "?useSSL=false&characterEncoding=utf-8";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, userPasswd);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//out.println("1");
			e.printStackTrace();
		}//
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//out.println("2");
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void runq(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void runu(String sql) {
		try {
			//System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("ERROR!");
			e.printStackTrace();
		}
	}
	public ResultSet getRs() {
		return rs;
	}
}
