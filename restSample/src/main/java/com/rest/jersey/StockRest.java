package com.rest.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.rest.jersey.bo.StockBoImpl;
import com.rest.jersey.model.Stock;
import com.rest.jersey.pojo.Student;
import com.sun.jersey.api.core.InjectParam;

@Component
@Path("/stockService")
public class StockRest {
 
	@InjectParam
	StockBoImpl stockBo;
	
	
	@GET
	@Path("/find/{param}")
	public Response find(@PathParam("param") String msg) {
 
		Stock stock = stockBo.findByStockCode(msg);
		
		String output = stock != null ? stock.getStockName() : "stock not found";
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/insert/{param1}/{param2}")
	public Response insert(@PathParam("param1") String stockCode, @PathParam("param2") String stockName) {
 
		Stock stock = new Stock();
		stock.setStockCode(stockCode);
		stock.setStockName(stockName);
		stockBo.save(stock);
		
		String output = stock != null ? "id stock:"+stock.getStockId() : "stock not found";
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON( @PathParam("name") String name ) {

		Student st = new Student(name, "Diaz",22,1);

		return st;

	}
	
}