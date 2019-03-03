package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommodityT {
	public String go(String name) {
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		sql.runq("select distinct commodity.Category from commodity,shop where shop.Name = \""+name+"\"" + " and commodity.S_Id = shop.S_Id");
		System.out.println("select distinct commodity.Category from commodity where shop.Name = \""+name+"\"" + " and commodity.S_Id = shop.S_Id");
		rs = sql.getRs();
		String res = new String("");
		try {
			while(rs.next()) {
				res += rs.getString("Category")+"\n";
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
