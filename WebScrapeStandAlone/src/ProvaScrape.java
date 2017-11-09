import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.scrape.com.Bet;

public class ProvaScrape {

	public static void main(String[] args) {
		String searchQuery = "Iphone 6s" ;
		WebClient client = new WebClient(BrowserVersion.FIREFOX_52);
		//WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false); 
		int i=0;
		try {  
			//String searchUrl = "http://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
			String searchUrl = "http://sports.williamhill.it/bet_ita/it/betting/y/5/tm/0/Calcio.html";
			HtmlPage page = client.getPage(searchUrl);
			// System.out.println(page.asText());
			//System.out.println(page.asXml());
			//page.getByXPath("");

			final List <HtmlTableRow> nodes = page.getByXPath("//tr[@class='rowOdd']");
			//System.out.println(nodes.get(0).asXml());
			for (final HtmlTableRow node: nodes) {
			   //if (i>1)break;
			   //System.out.println(i);
				Bet b = new Bet(node);
			   //i++;
				//final List<?> divs = node.getByXPath("//h4/text()");
			   //System.out.println(node.asXml());
  			   //System.out.println(page.getByXPath("//tr[@class='rowOdd']/@id").get(0));
			}
			
			
	      //get div which has a 'name' attribute of 'John'
	      //final HtmlTableRow div = (HtmlTableRow) page.getByXPath("//tr[@class='rowOdd']").get(0);
		  //System.out.println(div.asXml());
		  //System.out.println(page.getByXPath("//tr[@class='rowOdd']/@id").get(0));
		  //div.getByXPath("");
		  //DomAttr l;
		}catch(Exception e){
		  e.printStackTrace();
		}
	}

}
