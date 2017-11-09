package com.scrape.com;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class Bet {
	String id;
	String a,b;
	double esX,es1,es2;
	
	public Bet (HtmlTableRow r){	
		String  g= r.getAttribute("id");
		//System.out.println(g);
		
		System.out.println(g.substring(7));
		final List <DomText> nodes = r.getByXPath(".//span[1]/text()");
		for (DomText node: nodes) {
		   //final List<?> divs = node.getByXPath("//h4/text()");
		    //System.out.println(node.asXml());
			
		    try {
		    StringTokenizer st2 = new StringTokenizer(node.asXml(), "-");
			a=st2.nextElement().toString().trim();
			b=st2.nextElement().toString().trim();
			//while (st2.hasMoreElements()) {
			//	System.out.println(st2.nextElement());
			//}
			//System.out.println(a);
			//System.out.println(b);
		    }catch (NoSuchElementException e) {
		    	System.err.println(node.asXml());
		    }
			//System.out.println(page.getByXPath("//tr[@class='rowOdd']/@id").get(0));
		}
		
		final List <DomText> nodes2 = r.getByXPath(".//div/div/text()");
		es1=Double.parseDouble(nodes2.get(0).asText().trim());
		esX=Double.parseDouble(nodes2.get(1).asText().trim());
		es2=Double.parseDouble(nodes2.get(2).asText().trim());
		
		final List <HtmlDivision> nodes3 = r.getByXPath(".//td/div");
		System.out.println(((HtmlDivision)nodes3.get(0)).getAttribute("id").substring(11));
		System.out.println(((HtmlDivision)nodes3.get(1)).getAttribute("id").substring(11));
		System.out.println(((HtmlDivision)nodes3.get(2)).getAttribute("id").substring(11));
		//for (DomText node: nodes2) {
		//	System.out.println(node.asXml());
		//}
	}
	
	@Override
	public String toString(){
		return a+"-"+b+" "+ es1 + " " + esX + " " + es2;
	}
}
