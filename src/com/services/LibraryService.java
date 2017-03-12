package com.services;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

		LibraryDAO dao = new LibraryDAO();
//		JSONObject jo = new JSONObject(UserModel);
		int userId = dao.signUp(UserModel);
		
		//return Response.status(200).entity("Sign Up Success").build();
		return Response.status(200).entity(userId).build();
//		return (HashMap) new HashMap().put("response", "okays");
	}
	
	
	
	@GET
	@Path("/myProfile/{userId}")  //For PathParam
	//@Path("/myProfile")  // For QueryParam
	//@PathParam("userId") //can be used here instead of public Response myProfile(@PathParam("userId") String userId) 
	//But need to analyse.Sign up call come into this method if this is used.
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	public Response myProfile(@PathParam("userId") String userId) throws SQLException{
	//public Response myProfile(@QueryParam("userId") String userId) throws SQLException{

		LibraryDAO dao = new LibraryDAO();
		LoginUserModel userObj = dao.fetchMyProfile(userId);		
		return Response.status(200).entity(userObj).build();
	}
	

//	@GET
//	@Path("/myCart")
//	public Response myCart(String personId) throws ClassNotFoundException, SQLException, JSONException{
//		LibraryDAO dao = new LibraryDAO();
//		dao.getMyCart(personId);
//		return Response.status(200).build();
//	}
}