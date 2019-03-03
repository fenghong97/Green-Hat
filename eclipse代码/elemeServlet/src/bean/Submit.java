package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Submit {
	public String go(Map<String,String> f) {
		String res = new String("FALSE");
		//连接数据库
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		sql.runq("select * from commodity "
				+ "where commodity.Name=\""+f.get("Name")+"\"");
		System.out.println("select * from commodity "
				+ "where commodity.Name=\""+f.get("Name")+"\"");
		rs = sql.getRs();
		String C_Id = null, O_Id = null, U_Id = null, R_Id = null, IfAccept = null, Time = null, Number = null;
		
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
        SimpleDateFormat sdf = (SimpleDateFormat)DateFormat.getDateTimeInstance();
        System.out.println("当前日期时间：" + df.format(date));
        System.out.println("当前日期时间：" + sdf.format(date));
        
        Time = df.format(date).toString();
        try {
        	rs.next();
			C_Id = rs.getString("C_Id");
			System.out.println(C_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        U_Id = "1";
        R_Id = "1";
        IfAccept = "1";
        Number = f.get("Number");
        System.out.println(""+C_Id+" "+O_Id+" "+U_Id+" "+R_Id+" "+IfAccept+" "+Time+" "+Number );
		sql.runu("insert into ord values(null"
				+ ","+C_Id
				+ ","+U_Id
				+ ","+R_Id
				+ ","+IfAccept
				+ ",\""+Time+"\""
				+ ","+Number
				+ ")");
		System.out.println("insert into ord values(null"
				+ ","+C_Id
				+ ","+U_Id
				+ ","+R_Id
				+ ","+IfAccept
				+ ",\""+Time+"\""
				+ ","+Number
				+ ")");
		//sql.runq("select * from users");
		sql.close();
		return res;
	}
}
