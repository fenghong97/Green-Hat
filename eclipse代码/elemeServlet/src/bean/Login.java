package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class Login {
	public String go(String username, String passworld) throws SQLException {
		System.out.println("NOWINFMATION : " + username + " " + passworld);
		String res = new String("FALSE");
		
		
		//连接数据库
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		
		sql.runq("select * from users where users.Username = '"+username+"'");
		//sql.runq("select * from users");
		rs = sql.getRs();
		if(rs.next()) {
			res = "TRUE";
		}
		else {
			res = "FALSE";
		}
		sql.close();
		return res;
	}
}
