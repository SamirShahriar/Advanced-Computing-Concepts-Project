import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import textprocessing.In;
import textprocessing.StdOut;
import textprocessing.TST;
import textprocessing.TrieST;

public class searchengine {
	final File folder = new File("W3C Web Pages");
	private List<String> links = new LinkedList<String>(); 
	private List<String> linkfile = new LinkedList<String>();
	Document doc;
	In in;
	
	private static final String USER_AGENT =
    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/535.1";
    
	public  boolean listFilesForFolder(String url) throws IOException {
	            try {
	            	Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);						
					Document doc = connection.get();
					Elements elements = doc.select("a[href]");
					for (Element element : elements) {
						//System.out.println(element.absUrl("href"));
						links.add(element.absUrl("href"));
					}
					FileWriter fr;					
		    	    String filename = "DataBase\\" + doc.title() + ".txt"; 		    	    
				try {
					linkfile.add(filename);
					fr = new FileWriter(filename);
					fr.write(doc.body().text().toLowerCase().trim()); 						
					fr.close();		
				} catch (IOException e) {
					// TODO Auto-generated catch block						
				}		           				
					return true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block					
				}
				return false;
	}
	
	public List<String> findlinks(){
		return links;
	}
	public List<String> findfile(){
		return linkfile;
	}
}
