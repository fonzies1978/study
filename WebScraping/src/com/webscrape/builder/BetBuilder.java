package com.webscrape.builder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.webscrape.entities.Bet;
import com.webscrape.entities.BetMatch;

@Stateless
public class BetBuilder {
	@PersistenceContext(unitName="WebScraping")
	private javax.persistence.EntityManager em;
	
	public int build (HtmlTableRow r){	
		int ret=0;
		String  g= r.getAttribute("id");
		//System.out.println(g);		
		String matchId = g.substring(7);
		
		final List <DomText> nodes = r.getByXPath(".//span[1]/text()");				
		//System.out.println(matchId);
		String data 	= nodes.get(0).asXml();
		String ora  	= nodes.get(1).asXml();
		String teams  	= nodes.get(2).asXml();
				
			/*for (DomText node: nodes) {
				System.out.println(i++);
				System.out.println(node.asXml());
		    	try {
		   	*/
			
			try {
			    
				StringTokenizer st2 = new StringTokenizer(teams, "-");
				String a=st2.nextElement().toString().trim();
				String b=st2.nextElement().toString().trim();
				
				//while (st2.hasMoreElements()) {
				//	System.out.println(st2.nextElement());
				//}
				//System.out.println(a);
				//System.out.println(b);
			    
				BetMatch bm = new BetMatch();
			    //bm.setMatchid(matchId);
			    bm.setData(data);
			    bm.setOra(ora);
			    bm.setTeama(a);
			    bm.setTeamb(b);

				final List <DomText> nodes2 = r.getByXPath(".//div/div/text()");
			
				double es1=Double.parseDouble(nodes2.get(0).asText().trim());
				double esX=Double.parseDouble(nodes2.get(1).asText().trim());
				double es2=Double.parseDouble(nodes2.get(2).asText().trim());
				
				final List <HtmlDivision> nodes3 = r.getByXPath(".//td/div");
				//System.out.println(((HtmlDivision)nodes3.get(0)).getAttribute("id").substring(11));
				//System.out.println(((HtmlDivision)nodes3.get(1)).getAttribute("id").substring(11));
				//System.out.println(((HtmlDivision)nodes3.get(2)).getAttribute("id").substring(11));
							
				Bet bet = new Bet();
				bet.setType("1");
				bet.setShare(es1);
				bet.setMatchid(matchId);
				bet.setBetid(((HtmlDivision)nodes3.get(0)).getAttribute("id").substring(11));
				//bet.setTime((new Date()));
								
				Bet bet1 = new Bet();
				bet1.setType("X");
				bet1.setShare(esX);
				bet1.setMatchid(matchId);
				bet1.setBetid(((HtmlDivision)nodes3.get(1)).getAttribute("id").substring(11));
				//bet1.setTime(new Date());
				
				Bet bet2 = new Bet();
				bet2.setType("2");
				bet2.setShare(es2);
				bet2.setMatchid(matchId);
				bet2.setBetid(((HtmlDivision)nodes3.get(2)).getAttribute("id").substring(11));
				//bet2.setTime(new Date());
				
				em.merge(bm);
				/*em.merge(bet);
				em.merge(bet1);
				em.merge(bet2);*/
				em.persist(bet1);
				em.persist(bet2);
				em.persist(bet);
				
				//em.flush();
				
				//bet.setBetid(nodes3.get(0).asXml().substring(8));
				//System.out.println(nodes3.get(0).asXml());
				//b.setBetid(page.getByXPath("//tr[@class='rowOdd']/@id").get(0));
				//b.setType("1");
				//bm.getBets().add(bet);
				//System.out.println(page.getByXPath("//tr[@class='rowOdd']/@id").get(0));
				ret=1;
		    }catch (NoSuchElementException e) {
		    	//System.err.println(node.asXml());
		    }
			
			return ret;
		//}

	}

	
}
