package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.model.LibraryBooks;
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
	CallableStatement cs;
	
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
	public int validateLogin(String username,String password) throws SQLException, ClassNotFoundException
	{	
		//boolean bool=false;
		int userId = 0;
		try{
		     	//stmt = conn.createStatement();
			  ps = conn.prepareStatement("SELECT id, username, password FROM LIBAPP_USER_DETAILS where username = ? and password = ? and isactive = ?");
			  ps.setString(1, username);
			  ps.setString(2, password);
			  ps.setString(3, "Y");
		      rs = ps.executeQuery();
		      if(rs.next())
		    	  userId = rs.getInt("id");
		    	 // bool = true;
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
		//return bool;
		return userId;
	}

	/*public boolean signUp(LoginUserModel UserModel) throws SQLException, ClassNotFoundException, JSONException
	{	
		boolean bool=false;
		String sql = "Insert into LOGIN_USER_DETAILS (username, emailid, password, isactive, firstname, lastname, phonenumber, interests) VALUES (?,?,?,?,?,?,?,?)";
		try{
			  ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			  //ps.setInt(1, 7);
			  /*ps.setString(2, UserModel.getUsername());
			  ps.setString(3, UserModel.getEmailid());
			  ps.setString(4, UserModel.getPassword());
			  ps.setString(5, "Y");
			  ps.setString(6, UserModel.getFirstname());
			  ps.setString(7, UserModel.getLastname());
			  ps.setString(8, UserModel.getPhonenumber());
			  ps.setString(9, UserModel.getInterests());
			  */
			  
			 /* ps = conn.prepareStatement("Insert into LOGIN_USER_DETAILS VALUES (?,?,?,?,?,?,?,?)");
			  ps.setString(1, UserModel.getUsername());
			  ps.setString(2, UserModel.getEmailid());
			  ps.setString(3, UserModel.getPassword());
			  ps.setString(4, "Y");
			  ps.setString(5, UserModel.getFirstname());
			  ps.setString(6, UserModel.getLastname());
			  ps.setString(7, UserModel.getPhonenumber());
			  ps.setString(8, UserModel.getInterests());
			  
			  
			  int affectedRows = ps.executeUpdate();

		        if (affectedRows == 0) {
		            throw new SQLException("Creating user failed, no rows affected.");
		        }
		       
		        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	UserModel.setId(generatedKeys.getLong(1));
		            }
		            else {
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }
		      rs = ps.executeQuery();
//			  rs = ps.getGeneratedKeys();
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
	}*/
	

	public int signUp(LoginUserModel UserModel) throws SQLException, ClassNotFoundException, JSONException
	{	
		boolean bool=false; 
		int userId = 0; 
		int id = 0 ;
		//String sql = "Insert into LIBAPP_USER_DETAILS (username, email, password, isactive, firstname, lastname, phonenumber, interests) VALUES (?,?,?,?,?,?,?,?)";
		
		String sql = "BEGIN Insert into LIBAPP_USER_DETAILS (username, email, password, isactive, firstname, lastname, phonenumber, interests) VALUES (?,?,?,?,?,?,?,?) RETURNING id INTO ?; END;";
		
		//String sql = "Insert into TEST1 (username) VALUES (?)";
		try{
			  /*ps = conn.prepareStatement(sql, new String[]{"id"});
			  ps.setString(1, UserModel.getUsername());
			  ps.setString(2, UserModel.getEmail());
			  ps.setString(3, UserModel.getPassword());
			  ps.setString(4, "Y");
			  ps.setString(5, UserModel.getFirstname());
			  ps.setString(6, UserModel.getLastname());
			  ps.setString(7, UserModel.getPhonenumber());
			  ps.setString(8, UserModel.getInterests());
			  
		      //rs = ps.executeQuery();
			  ps.executeUpdate();
			  ResultSet rs = ps.getGeneratedKeys();
			  
		      if(rs.next())
		      {
		    	  long id = rs.getLong("id");
		      }*/
		     /* if(rs!=null){
		    	  bool = true;
		    	  //userId = rs.getString("id");
		      }*/
			
			//http://stackoverflow.com/questions/3552260/plsql-jdbc-how-to-get-last-row-id
			cs = conn.prepareCall(sql);
			cs.setString(1, UserModel.getUsername());
			  cs.setString(2, UserModel.getEmail());
			  cs.setString(3, UserModel.getPassword());
			  cs.setString(4, "Y");
			  cs.setString(5, UserModel.getFirstname());
			  cs.setString(6, UserModel.getLastname());
			  cs.setString(7, UserModel.getPhonenumber());
			  cs.setString(8, UserModel.getInterests());
		    cs.registerOutParameter(9, Types.NUMERIC);
		    cs.execute();
		    id = cs.getInt(9);
		    return id;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			/* rs.close();
		     ps.close();
		     conn.close();*/
		}
		return id;
	}
			  
	public LoginUserModel fetchMyProfile(String userId) throws SQLException
	{
		LoginUserModel userObj = new LoginUserModel();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM LIBAPP_USER_DETAILS WHERE id = " +userId);
		while (rs.next())
		{
			userObj.setId(rs.getLong("id"));
			userObj.setFirstname(rs.getString("firstname"));
			userObj.setLastname(rs.getString("lastname"));
			userObj.setUsername(rs.getString("username"));
			userObj.setEmail(rs.getString("email"));
			userObj.setInterests(rs.getString("interests"));
			userObj.setPhonenumber(rs.getString("phonenumber"));
		}
		return userObj;
		
	}
	
	public List<LibraryBooks> searchBooks(LibraryBooks libBook) throws SQLException
	{
		List<LibraryBooks> lbArr = new ArrayList<LibraryBooks>();
		try{
			  ps = conn.prepareStatement("SELECT * FROM LIBRARY_BOOKS");
		      rs = ps.executeQuery();
		      while(rs.next())
		      {
		    	  LibraryBooks lb = new LibraryBooks();
		    	  lb.setAuthor(rs.getString("author"));
			      lb.setAvailableunits(rs.getString("availableunits"));
			      lb.setEdition(rs.getString("edition"));
			      lb.setId(rs.getInt("id"));
			      lb.setPublisher(rs.getString("publisher"));
			      lb.setTitle(rs.getString("title"));
			      lbArr.add(lb);
		      }
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		/*finally
		{
			 rs.close();
			 ps.close();
		     conn.close();
		}*/
		return lbArr;
	}
	
	
	public String placeOrder(int userId, List cart) throws SQLException, ClassNotFoundException, JSONException
	{	
		int count =0;
		String msg = "";
		String sql = "Insert into LIBAPP_ORDERS (userid, bookid) VALUES (?,?)";
		try{
			for(int i=0; i<cart.size(); i++)
			{
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, ((LibraryBooks)cart.get(i)).getId());
				count = ps.executeUpdate();
				if(count == 0)
					break;
			}
			if(count==0)
				msg="Something went wrong while placing order";
			else
				msg = "Order placed successfully";
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			/* rs.close();
		     ps.close();
		     conn.close();*/
		}
		return msg;
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
