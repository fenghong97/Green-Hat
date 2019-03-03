package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;

public class GetShop {
	public void go() {
		//连接数据库
		ResultSet rs = null;
		Sql sql = new Sql();
		sql.link("ysw", "123456", "eleme");
		sql.runq("select * from shop");
		rs = sql.getRs();
		try {
			String data = new String("[\n");
			int cnt = 0;
			while(rs.next()) {
				String temp = new String("");
				if(cnt!=0) temp += ",\n"; ++cnt;
				temp += "{ \"name\":\""+rs.getString("Name")+"\",";
				temp += "\"imageURL\":\""+rs.getString("ImageURL")+"\",";
				temp += "\"score\":\""+rs.getString("Score")+"\",";
				temp += "\"sale\":\""+rs.getString("Sale")+"\",";
				temp += "\"sprice\":\""+rs.getString("MinPrice")+"\",";
				temp += "\"dprice\":\""+rs.getString("Dprice")+"\",";
				temp += "\"distance\":\""+rs.getString("Distance")+"\",";
				temp += "\"time\":\""+rs.getString("GetTime")+"\" }";
				data += temp;
			}
			
			data += "\n]";
			
			//DEBUG
			System.out.println(data);
			
			String to = "H:\\Tomcat\\apache-tomcat-9.0.5\\webapps\\ele\\shopdata.json";
			File file = new File(to);
			if (!file.getParentFile().exists()) {
	            file.getParentFile().mkdirs();
	        }
	        try {
	            file.delete();
	            file.createNewFile();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        try {
	        	FileOutputStream fos = new FileOutputStream(file);
	        	OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	            //FileWriter fw = new FileWriter(file, true);
	            //fw.write(data);
	            //fw.close();
	        	osw.write(data); 
	            osw.flush(); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.close();
	}
}
