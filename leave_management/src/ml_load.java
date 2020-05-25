import java.sql.*;  

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.*;


 
 @WebServlet("/ml_load")
 
public class ml_load extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
	//the statements to connect to the default mongo server instance running at localhost with default port..
	
	
    public ml_load() {
        super();
    }

    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at get request ");
		//doPost(request, response);
		try{ 
			String faculty_id = request.getParameter("faculty_id");
			Class.forName("com.mysql.jdbc.Driver");  
			/*Connection con=DriverManager.getConnection(  
			"jdbc:mysql://192.169.197.128:3306/rits_db","rits","Welcome@12#");*/
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/rits_db","root","eadlab"); 
			//here rits_db is database name, rits is username and password  
			Statement stmt=con.createStatement();
			ResultSet rs = null;
			
			
				 rs=stmt.executeQuery("select * from maternity_leave where  faculty_id='"+faculty_id+"'");
							
			
			
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			while(rs.next()) {
				
			JSONObject record = new JSONObject();
			record.put("faculty_id", rs.getString("faculty_id"));
			record.put("ml", rs.getInt("ml"));
		    record.put("bal_ml", rs.getInt("bal_ml"));
		    record.put("avail_ml", rs.getInt("avail_ml"));
		    record.put("no_ml", rs.getInt("no_ml"));
		   array.put(record);
		   
		  System.out.println(array);
			
		}					
				
			String str = array.toString();
			response.getWriter().append(str);
			//response.getWriter().append(str);
			con.close();  
		
			
			
		
		}
		
		
		
			catch(Exception e){ System.out.println(e);}  
		
	}

	
	

	

}
