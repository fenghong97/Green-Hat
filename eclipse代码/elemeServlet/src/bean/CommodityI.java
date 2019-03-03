package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommodityI {
	public String go(String name) {
		String res = new String("");
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		sql.runq("select * from commodity,shop where shop.Name = \""+name+"\"" + " and commodity.S_Id = shop.S_Id");
		System.out.println("select * from commodity,shop where shop.Name = \""+name+"\"" + " and commodity.S_Id = shop.S_Id");
		rs = sql.getRs();
		try {
			while(rs.next()) {
				res += "&Name="+rs.getString("Name");
				res += "&Price="+rs.getString("Price");
				res += "&Sales="+rs.getInt("SalesVolume");
				res += "&Type="+rs.getString("Category");
				res += "&Imagename="+rs.getString("Imagename");
				res += "\n";
			}
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.close();
		return res;
	}
}
