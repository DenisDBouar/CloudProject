package com.files;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

@Path("/")
public class Firstpage {
	
	
	@GET
	@Path("/gettable")
	public Response returnPage() throws UnknownHostException {
		/*DB db = (new MongoClient("localhost", 27017).getDB("mybase"));
		DBCollection dbcollection = db.getCollection("Chanel");
		BasicDBObject basicobject =new BasicDBObject();
		 */
		
		MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
		DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
		DBCollection dbcollection = db.getCollection("Cntacts2");
		BasicDBObject basicobject =new BasicDBObject();
		
		String out="";
		boolean first = true;
		DBCursor dbcursor = dbcollection.find(basicobject);
		while(dbcursor.hasNext()){
			out += first ? "" : ",";
			first = false;
			out += dbcursor.next();
		}
		String output = "{\"Cart\":["+ out +"]}";
		basicobject.clear();
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/addproduct")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public void addProdToCart(String json) throws Exception {
			MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
			DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
			DBCollection dbcollection = db.getCollection("Cntacts2");
			DBObject dbObject = (DBObject)JSON.parse(json);
			dbcollection.insert(dbObject);
	}
	
	@PUT
	@Path("/updateproduct/{findName}/{findValue}")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public void updateProdToCart(@PathParam("findName") String findName, @PathParam("findValue") String findValue, String replaceText) throws Exception {
			MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
			DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
			DBCollection dbcollection = db.getCollection("Cntacts2");
			
			BasicDBObject newDocument = new BasicDBObject();
			//new
			String replaceTextArray[] = replaceText.split(",");
			newDocument.put(findName, findValue);
			newDocument.put(replaceTextArray[0], replaceTextArray[1]);
			newDocument.put(replaceTextArray[2], replaceTextArray[3]);
			newDocument.put(replaceTextArray[4], replaceTextArray[5]);
			//find
			BasicDBObject searchQuery = new BasicDBObject().append(findName, findValue);
		 
			dbcollection.update(searchQuery, newDocument);
	}
	
	@DELETE @Path("/deleteproduct/{id}")
	@Produces(MediaType.TEXT_HTML)
	public void remove(@PathParam("id") String id) throws Exception {
		MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
		DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
		DBCollection dbcollection = db.getCollection("Cntacts2");
		DBObject dbObject = (DBObject)JSON.parse(id);
		dbcollection.remove(dbObject);
	}
	
	@GET
	@Path("/serchproduct/{param}")
	@Produces(MediaType.TEXT_HTML)
	public Response searchProduct(@PathParam("param") String param) throws Exception {
		MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
		DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
		DBCollection dbcollection = db.getCollection("Cntacts2");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("emp_no", param);
		DBCursor cursor = dbcollection.find(whereQuery);
		
		String out="";
		boolean first = true;
		while(cursor.hasNext()){
			out += first ? "" : ",";
			first = false;
			out += cursor.next();
		}
		String output = "{\"Cart\":["+ out +"]}";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/serchproduct2/{param}")
	@Produces(MediaType.TEXT_HTML)
	public Response searchProduct2(@PathParam("param") String param) throws Exception {
		MongoClientURI uri = new MongoClientURI("mongodb://user:pass@ds031627.mongolab.com:31627/heroku_app34654503");
		DB db = (new MongoClient(uri).getDB("heroku_app34654503"));
		DBCollection dbcollection = db.getCollection("Cntacts2");
		
		 BasicDBObject andQuery = new BasicDBObject();
		    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		    obj.add(new BasicDBObject("ID", param));
		    obj.add(new BasicDBObject("name", new BasicDBObject("$regex", param)));
		    obj.add(new BasicDBObject("product", new BasicDBObject("$regex", param)));
		    obj.add(new BasicDBObject("emp_no", new BasicDBObject("$regex", param)));
		    andQuery.put("$or", obj);
		    DBCursor cursor = dbcollection.find(andQuery);
		
		String out="";
		boolean first = true;
		while(cursor.hasNext()){
			out += first ? "" : ",";
			first = false;
			out += cursor.next();
		}
		String output = "{\"Cart\":["+ out +"]}";
		return Response.status(200).entity(output).build();
	}
	
	
	//instagram
	//http://localhost:8080/server/rest/getinstagramdata/chicago
	//http://localhost:8080/server
	//AIzaSyB_HMkXbimn3zragWK6313CHj-UJFAwqV8
	@GET
	@Path("/getinstagramdata/{param}/{distance}")
	@Produces(MediaType.TEXT_HTML)
	public Response getInstagramdata(@PathParam("param") String param, @PathParam("distance") String distance) throws Exception {
		
		Instagram insta = new Instagram();
		String instURL = insta.getLocation(param);
 
		return Response.status(200).entity(insta.geImages(instURL, distance)).build();
	}
}
