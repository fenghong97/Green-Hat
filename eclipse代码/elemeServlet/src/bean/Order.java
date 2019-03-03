package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
	public String go(String username) {
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		sql.runq
		("select ord.O_Id,commodity.Name,commodity.Imagename,ord.Time,ord.U_Id,ord.IfAccept,commodity.Price,ord.Number "
				+ "from commodity,ord,users "
				+ "where users.Username =\""+username+"\" and ord.U_Id=users.U_Id and commodity.C_Id=ord.C_Id");
		System.out.println("select order.O_Id,commodity.Name,commodity.Imagename,order.Number,order.Time,order.U_Id,order.IfAccept,commodity.Price "
				+ "from commodity,order,users "
				+ "where users.Name =\""+username+"\" and order.U_Id=users.U_Id and commodity.C_Id=order.C_Id");
		rs = sql.getRs();
		String data = new String("");
		try {
			while(rs.next()) {
				data += "&O_Id="+rs.getString("O_Id");
				data += "&Name="+rs.getString("Name");
				data += "&Imagename="+rs.getString("Imagename");
				data += "&Number="+rs.getString("Number");
				data += "&Time="+rs.getString("Time");
				data += "&IfAccept="+rs.getString("IfAccept");
				data += "&U_Id="+rs.getString("U_Id");
				data += "&Price="+rs.getDouble("Price")*rs.getInt("Number");
				data += "\n";
			}
			System.out.println(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.close();
		return data;
	}
}
