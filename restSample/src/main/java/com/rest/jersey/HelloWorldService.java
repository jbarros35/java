package com.rest.jersey;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.jersey.bo.StockBo;
import com.rest.jersey.model.Stock;
import com.rest.jersey.pojo.Student;

@Path("/hello")
public class HelloWorldService {
 
	@Autowired
	StockBo stockBo;
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		Stock stock = stockBo.findByStockCode(msg);
		
		String output = "Jersey say : " + stock.getStockName();
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON( @PathParam("name") String name ) {

		Student st = new Student(name, "Diaz",22,1);

		return st;

	}

	@POST	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/send")
	public Response consumeJSON(@FormParam("json") JSONObject student ) {
		
		ObjectMapper mapper = new ObjectMapper();
		Student st = null;
		try {
			st = (Student) mapper.readValue(student.toString(), Student.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(st.toString()).build();
	}
	
	public StockBo getStockBo() {
		return stockBo;
	}

	public void setStockBo(StockBo stockBo) {
		this.stockBo = stockBo;
	}


	
}
