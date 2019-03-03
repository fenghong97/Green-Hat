package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommodityI;
import bean.CommodityT;
import bean.GetShop;
import bean.Login;
import bean.Order;
import bean.Sql;
import bean.Submit;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public class HttpSolve extends HttpServlet {
	private static final String CONTENT_TYPE = null;
	//private static final String SEARCH_TYPE = "1";
	//private static final String DELETE_TYPE = "2";
	private static final int LOGIN_TYPE = 1;
	private static final int REGIST_TYPE = 2;
	private static final int GETSHOP_TYPE = 3;
	public static final int COMMODITYT_TYPE = 4;
    public static final int COMMODITYI_TYPE = 5;
    public static final int ORDER_TYPE = 6;
    public static final int SUBMIT_TYPE = 7;
	
	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
 
		PrintWriter out = response.getWriter();
		BufferedReader in = new BufferedReader(request.getReader());
		
		//StringBuffer infSB = new StringBuffer();
		
		//读取客户端发送的数据
		String inf = new String(in.readLine().toString());
		System.out.println(inf);
		Map<String,String> solvedInf = new HashMap<String,String>();
		for(int i = 0;i < inf.length();) {
			String name = new String("");
			String desc = new String("");
			while(i<inf.length()&&inf.charAt(i)!='=') name += inf.charAt(i++); ++i;
			while(i<inf.length()&&(i+1>=inf.length()||inf.charAt(i)!='&'||inf.charAt(i+1)!='&')) desc += inf.charAt(i++);
			solvedInf.put(name,desc);
			System.out.println(name+" "+desc);
			i += 2;
		}
		
		System.out.println(inf);
 
		//返回给客户端的应答消息
		//out.write("SEND BY SERVER");

		//连接数据库
		//Sql sql = new Sql();
		//sql.link("ysw", "123456", "eleme");

		int infType = Integer.parseInt(solvedInf.get("type"));
		String result = new String("NULL");
		System.out.println(infType);
		switch(infType) {
			//登录用户
			case LOGIN_TYPE: {
				System.out.println("LOGIN");
				Login login = new Login();
				try {
					result = login.go(solvedInf.get("username"),solvedInf.get("passworld"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("SQLERROR");
					e.printStackTrace();
				}
			} break;
			
			//从数据库中得到商店信息并写到shopdata.json文件中
			case GETSHOP_TYPE: {
				System.out.println("GETSHOP");
				GetShop getshop = new GetShop();
				getshop.go();
			} break;
			
			//得到对应商店的商品种类
			case COMMODITYT_TYPE: {
				System.out.println("COMMODITYT");
				CommodityT commodityt = new CommodityT();
				result = commodityt.go("张飞牛肉");
			} break;
			
			//得到商品信息
			case COMMODITYI_TYPE: {
				System.out.println("COMMODITYI");
				CommodityI commodityi = new CommodityI();
				result = commodityi.go("张飞牛肉");
			} break;
			
			//得到订单信息
			case ORDER_TYPE: {
				System.out.println("ORDER");
				Order order = new Order();
				result = order.go("15306574725");
			} break;
			
			//提交订单信息
			case SUBMIT_TYPE: {
				System.out.println("SUBMIT");
				Submit submit = new Submit();
				result = submit.go(new HashMap<String,String>(solvedInf));
			} break;
		}
		System.out.println(result);
		out.write(result);
		/*
		try {
			
				sql.runq
				("select * from users");
				rs = sql.getRs();
				String res = new String("");
				while(rs.next()) {
					res += rs.getString("U_Id")+" "+
						  rs.getString("Account") + " "+
						  rs.getString("Password")+" "+
					      rs.getString("Name")+" "+
						  rs.getString("Tel")+" "+
						  rs.getString("RecInfNumber")+"\n";
					//System.out.println(res);
				}
				System.out.println(res);
				out.write(res);
				//sql.runu("insert into fakeshopcar values(" + id + ", 1, 1, "+ rs.getString(4) + ")");
				//sql.runu("insert into shopcar values(1, 1, 1, " + rs.getString(4) + ")");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR!");
			out.write("CONNECT ERROR!");
			e.printStackTrace();
		}*/
		//sql.close();
 
		in.close();
		out.close();
	}

}
