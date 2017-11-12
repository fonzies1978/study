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
import com.webscrape.entities.Bet;

@Path("/bets/{id}")
public class BetApi {
	@PersistenceContext(unitName="WebScraping")
	private javax.persistence.EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBets(@PathParam("id") String id) {
		
		Query query = em.createQuery(
		      "SELECT c FROM bet AS c where matchid='"+id+"' order by c.type asc, c.time desc"
		      );

		ObjectMapper mapper = new ObjectMapper();	         
		
        ArrayNode arrayNode = mapper.createArrayNode();			  
        for ( Bet ce : ( List <Bet>) query.getResultList()) {			

        	ObjectNode objectNode1 = mapper.createObjectNode();
	        objectNode1.put("type", 	ce.getType() );
	        objectNode1.put("share", 	ce.getShare() );
	        objectNode1.put("MatchId", 	ce.getMatchid() );
	        objectNode1.put("time", 	ce.getTime().toString() );
	        
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
			e.printStackTrace();
		}

		return Response.ok(arrayNode).
				header("Access-Control-Allow-Origin", "*").
				header("Content-Type","application/json; charset=utf-8").
				build();

	}
}
