package com.webscrape.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.webscrape.builder.BetBuilder;

/**
 * Servlet implementation class ScrapeServlet
 */
@WebServlet("/ScrapeServlet")
public class ScrapeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private BetBuilder builder;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				
		WebClient client = new WebClient(BrowserVersion.FIREFOX_52);
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false); 
		
		int tot=0;		
		int w = 0;
		
		try {  
			
			for (int j=1;j<7;j++) {
				
				String searchUrl = "http://sports.williamhill.it/bet_ita/it/betting/y/5/tm/"+j+"/Calcio.html";
				
				HtmlPage page = client.getPage(searchUrl);
								
				final List <HtmlTableRow> nodes = page.getByXPath("//tr[@class='rowOdd']");
				
				tot += nodes.size();
				
				for (final HtmlTableRow node: nodes) {
					//System.out.println(node.asXml());
					w += builder.build(node);
				}
			}
			
		}catch(Exception e){
		  e.printStackTrace();
		}
		
		client.close();		
		response.getWriter().append("Elaborati: "+w + "/" + tot);
		//.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
