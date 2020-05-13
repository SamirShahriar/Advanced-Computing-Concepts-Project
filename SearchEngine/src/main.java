import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import textprocessing.BoyerMoore;
import textprocessing.In;
import textprocessing.StdOut;
import textprocessing.TrieST;

public class main {
	static searchengine engine = new searchengine();
	static In in;
	static TST<Integer> st = new TST<Integer>();
	static ArrayList<String> TSTList = new ArrayList();
	static SET<String> dictionary = new SET<String>();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 1;		
		while (choice == 1) {
			System.out.println("Please enter your choice:");
			System.out.println("1. Search ");
			System.out.println("2. Create DataBase");
			System.out.println("3. Quit");
			System.out.println("");
			int number = input.nextInt();
			switch (number) {
			case 1:
				System.out.println("1 is selected");
				serachword(engine);
				break;
			case 2:
				System.out.println("2 is selected");	
				builddb();
				break;
			case 3:
				System.out.println("Program Ends");
				choice = 0;
			}
			
		}
	}
	public static void serachword(searchengine engine2) {		        		
		float starttimer = 0;
		float endtimer = 0;
		starttimer = System.nanoTime();		
		if (st.size() == 0)
			System.out.println("DataBase is empty, Please select Option 2 before search.");
		else {
			System.out.println("Enter the Keyword to search: ");
			System.out.println("Enter Websites for listing all the website.");
			System.out.println("Enter Emails for listing all the emails.");
			System.out.println("Enter Phones for listing all the phone numbers.");
			Scanner keyinput = new Scanner(System.in);
			String key = keyinput.next();
			System.out.println("Your Searched Keyword is : " + key);			
			if (st.get(key.toLowerCase()) == null && key.toLowerCase() != dictionary.ceiling(key) && 
					!key.toLowerCase().matches("websites") && 
					!key.toLowerCase().matches("emails") && 
					!key.toLowerCase().matches("phones")) {
				if (dictionary.ceiling(key) != null) {
	            	StdOut.println("Did you mean: " + dictionary.ceiling(key));	            	
	            	//StdOut.println(st.get(dictionary.ceiling(key).toLowerCase()));
	            	for (String result : st.keys()) {
	    				if (result.matches(dictionary.ceiling(key).toLowerCase())) {
	    					StdOut.println("Showing Results for " + dictionary.ceiling(key));
	    					StdOut.println("Search " + result + " found at position " + st.get(result));
	    				}
	    	        }
	            }
			}
			else {
				for (String result : st.keys()) {					
					if (key.toLowerCase().matches("websites")) {
						if (result.matches("^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")) {
							StdOut.println("Website " + result + " found at " + st.get(result));
						}
					}
					else if (key.toLowerCase().matches("emails")) {
						if (result.matches("^(.+)@(.+)$")) {
							StdOut.println("Email " + result + " found at " + st.get(result));
						}
					}
					else if (key.toLowerCase().matches("phones")) {
						if (result.matches("(\\\\d){3}-(\\\\d){3}-(\\\\d){4}")) {
							StdOut.println("Phone# " + result + " found at " + st.get(result));
						}
					}
					else if (result.matches(key.toLowerCase())) {
						StdOut.println("Search " + result + " found at position " + st.get(result));
					}					
		        }				
			}					
		endtimer = System.nanoTime();
		StdOut.println("Average Time Taken for search in ns: " + (endtimer - starttimer));
		}
	}
	public static void builddb() {		
		boolean success;
		String webaddress = null;
		
		ArrayList<String> list = new ArrayList();    
		System.out.println("Enter initial website to crawl:");
		System.out.println("Press 1 for: www.google.com");
		System.out.println("Press 2 for: www.w3.org");
		System.out.println("Press 3 for enter url:");
		System.out.println("Press 4 for Database entries");
		Scanner keyinput = new Scanner(System.in);
		int web = keyinput.nextInt();
		switch(web) {
		case 1:
			webaddress = "www.google.com";
			break;
		case 2:
			webaddress = "www.w3.org";
			break;
		case 3:
			System.out.println("Enter the URL:");
			Scanner keyinput2 = new Scanner(System.in);
			String selfaddress = keyinput.next();
			webaddress = selfaddress.trim();
			break;
		case 4:
			if (st.size() != 0) {
				for (String result : st.keys()) {
					StdOut.println(result + " " + st.get(result));
				}
			}
			else {
				StdOut.println("Database is empty.");
				return;
			}			
		}
		https://www.w3.org/standards/	
		try {
			success = engine.listFilesForFolder("https://" + webaddress + "/");			
			if (success == true) {
				System.out.println("WebUrl Correct");				
				System.out.println("Building DataBase....");
			}
			else {
				System.out.println("URL not working, Please confirm");
				return;
			}
		int i = 0;
		while (i < 10) {
			engine.listFilesForFolder(engine.findlinks().get(i)); //We need to add this here
			//System.out.println(i);
			//System.out.println(engine.findlinks().size());
			//System.out.println(engine.findlinks().get(i));						
			engine.findlinks().remove(i);	
			i++;
		}
		//TST
	   List<String> filenames = engine.findfile();
	   while (!filenames.isEmpty()) {
		   try {
			   File fileSort = new File(filenames.get(0));			   
			   Scanner scnrSort = new Scanner(fileSort);
		   } catch (Exception e1) {filenames.remove(0);break;}
		   
		   File fileSort = new File(filenames.get(0));
		   Scanner scnrSort = new Scanner(fileSort);
		   while(scnrSort.hasNextLine()){
			   TSTList.add(scnrSort.nextLine());			   
		   }
		   scnrSort.close();						
		   filenames.remove(0);		   		   
		   	
	   }
	   for (int i1 = 0; i1 < TSTList.size(); i1++) {
		   String[] getChar = TSTList.get(i1).split(" ");
		   for (int i2=0; i2< getChar.length; i2++) {			   
			   try {
			   st.put(getChar[i2], i2);
			   } catch (Exception e) {e.toString();}
		   }		   
       }
	   // read in dictionary of words
       In dict = new In("words.utf-8.txt");
       while (!dict.isEmpty()) {
           String word = dict.readString();
           dictionary.add(word);
       }
	   } catch (IOException e) {e.toString();}
	}
}
