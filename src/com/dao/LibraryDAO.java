package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;

import com.model.LoginUserModel;

public class LibraryDAO {
	
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String USER = "hr";
	static final String PASS = "hr";
	Connection conn = null;
	Statement stmt = null;   
	ResultSet rs ;
	PreparedStatement ps ;
	
	public LibraryDAO()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch(Exception e)
		{
			System.out.print("Error creating database connection");
		}
	    
	}
	public boolean validateLogin(String username,String password) throws SQLException, ClassNotFoundException
	{	
		boolean bool=false;
		try{
		     	//stmt = conn.createStatement();
			  ps = conn.prepareStatement("SELECT username, password FROM LOGIN_USER_DETAILS where username = ? and password = ? and isactive = ?");
			  ps.setString(1, username);
			  ps.setString(2, password);
			  ps.setString(3, "Y");
		      rs = ps.executeQuery();
		      if(rs.next())
		    	  bool = true;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			 rs.close();
		     //stmt.close();
			 ps.close();
		     conn.close();
		}
		return bool;
	}

	public boolean signUp(LoginUserModel UserModel) throws SQLException, ClassNotFoundException, JSONException
	{	
		boolean bool=false;
		try{
			  ps = conn.prepareStatement("Insert into LOGIN_USER_DETAILS VALUES (?,?,?,?,?,?,?,?,?)");
			  ps.setInt(1, 6);
			  ps.setString(2, UserModel.getUsername());
			  ps.setString(3, UserModel.getEmailid());
			  ps.setString(4, UserModel.getPassword());
			  ps.setString(5, "Y");
			  ps.setString(6, UserModel.getFirstname());
			  ps.setString(7, UserModel.getLastname());
			  ps.setString(8, UserModel.getPhonenumber());
			  ps.setString(9, UserModel.getInterests());
			  
		      rs = ps.executeQuery();
		      if(rs!=null)
		    	  bool = true;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			 rs.close();
		     ps.close();
		     conn.close();
		}
		return bool;
	}
	
	public boolean getMyCart(String UserModel) throws SQLException, ClassNotFoundException, JSONException
	{	
		boolean bool=false;
		try{
			
			JSONArray jsonArray=  new JSONArray(UserModel);
			  ps = conn.prepareStatement("Insert into LOGIN_USER_DETAILS VALUES (?,?,?,?,?,?,?,?,?)");
			  ps.setInt(1, 5);
//			  ps.setString(2, x);
//			  ps.setString(3, x);
//			  ps.setString(4, x);
//			  ps.setString(5, x);
//			  ps.setString(6, x);
//			  ps.setString(7, x);
//			  ps.setString(8, x);
//			  ps.setString(9, x);
			  
		      rs = ps.executeQuery();
		      if(rs!=null)
		    	  bool = true;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			 rs.close();
		     ps.close();
		     conn.close();
		}
		return bool;
	}
}
