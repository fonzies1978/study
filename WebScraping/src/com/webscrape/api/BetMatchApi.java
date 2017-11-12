package com.webscrape.api;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webscrape.entities.BetMatch;


@Path("/matchs/{data}")
public class BetMatchApi {
	@PersistenceContext(unitName="WebScraping")
	private javax.persistence.EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBets(@PathParam("data") String data) {
		
		System.out.println(data);
		String d1 = data.replace("-", " ");
		Query query = em.createQuery(
		      "SELECT c FROM betmatch AS c where c.data like'%"+d1+"%'"
		      );
		  
		//List <BetMatch> results = query.getResultList();
		ObjectMapper mapper = new ObjectMapper();	        
        
        ArrayNode arrayNode = mapper.createArrayNode();			  
        for ( BetMatch ce : ( List <BetMatch>) query.getResultList()) {			
			//System.out.println("Match: " + ce.getTeama()  + "-" + ce.getTeamb());	        	
	        ObjectNode objectNode1 = mapper.createObjectNode();
	        objectNode1.put("TeamA", 	ce.getTeama() );
	        objectNode1.put("TeamB", 	ce.getTeamb() );
	        objectNode1.put("MatchId", 	ce.getMatchid() );
	        /*ArrayNode arrayNode1 = mapper.createArrayNode();		
	        System.out.println("FirstBet: ");	
	        for ( Bet cc :  ce.getBets()) {
		        System.out.println("InBet: ");	

		        ObjectNode objectNode2 = mapper.createObjectNode();
		        objectNode2.put("Type", 	cc.getType() );
		        objectNode2.put("Share", 	cc.getShare());
		        arrayNode1.add(objectNode2);
	        }*/
	        //objectNode1.putArray("bets").addAll(arrayNode1);
	        arrayNode.add(objectNode1);
		}
		String ret="{}";
		try {
			ret = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok(arrayNode).
				header("Access-Control-Allow-Origin", "*").
				header("Content-Type","application/json; charset=utf-8").
				build();
		//return ret;
				
	}/*
	@OPTIONS
	@Path("{path : .*}")
	public Response options() {
	    return Response.ok("")
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}	*/
}
