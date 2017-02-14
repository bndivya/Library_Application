package com.services;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.LibraryDAO;
import com.model.LoginUserModel;

@Path("/libraryService")
public class LibraryService {
	
	@POST
	@Path("/validateLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
//	public Response validateLogin(@FormParam("username") String username, 
//									@FormParam("password") String password) throws ClassNotFoundException, SQLException {
	//public Response validateLogin(User loginUserModel) throws ClassNotFoundException, SQLException {
	public Response validateLogin(String loginUserModel) throws ClassNotFoundException, SQLException, JSONException{
//		String output = "Jersey used ";
//		return Response.status(200).entity(output).build();
		
		//System.out.println("loginUserModel"+loginUserModel.getUsername());
		System.out.println("loginUserModel"+loginUserModel);
		
		JSONObject jsonObj = new JSONObject(loginUserModel);
		
		LibraryDAO dao = new LibraryDAO();
		boolean flag = dao.validateLogin((String)jsonObj.get("username"), (String)jsonObj.get("password"));
		String msg = "";
		if(flag==true)
			msg="Valid credentials";
		else
			msg="Invalid credentials";
		return Response.status(200).entity(msg).build();
	}
	
	
	@POST
	@Path("/signUp")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(LoginUserModel UserModel) throws ClassNotFoundException, SQLException, JSONException{
		
//		System.out.println("in sign up"+UserModel.getFirstname());
		
		/*if(!(UserModel.getPassword().equals(UserModel.getConfirmedPassword())))
		{
			return Response.status(400).entity("Mismatching Password").build();
		}*/
		LibraryDAO dao = new LibraryDAO();
//		JSONObject jo = new JSONObject(UserModel);
		dao.signUp(UserModel);
		return Response.status(200).entity("Sign Up Success").build();
//		return (HashMap) new HashMap().put("response", "okays");
	}
	
//	@GET
//	@Path("/myCart")
//	public Response myCart(String personId) throws ClassNotFoundException, SQLException, JSONException{
//		LibraryDAO dao = new LibraryDAO();
//		dao.getMyCart(personId);
//		return Response.status(200).build();
//	}
}