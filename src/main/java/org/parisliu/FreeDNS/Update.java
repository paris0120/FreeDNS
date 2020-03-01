package org.parisliu.FreeDNS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
public class Update 
{
    public static void main( String[] args ) throws IOException
    {
    	File file = new File(System.getProperty("user.home") + File.separator +".FreeDNSToken");
    	List<String> tokens = new ArrayList<String>();
    	if(args.length>0) {
    		for(String arg:args) {
    			tokens.add(arg);
    		}
    	}
    	else if(file.exists()){
    		tokens = Files.readAllLines(file.toPath());
    	}
    	else {
    		System.out.print("Tokens: ");
    		Scanner myObj = new Scanner(System.in);
    		String input = myObj.nextLine();
    		for(String token:input.split(" ")) {
    			tokens.add(token);
    		} 
    	}
    	if(tokens.size()>0) {
    		String output = "";
    		for(String token:tokens) {
    			output+=token +"\r\n";
    		}
    		Files.write(file.toPath(), output.getBytes());
    		

        	while(true) {
        		for(String token:tokens) {
                	Document doc = Jsoup.connect("http://freedns.afraid.org/dynamic/update.php?"+token+"=").get();
                 	System.out.println("Tokens: " + token); 
                   	System.out.println(doc.text());  
               	try {
        				Thread.sleep(1000*60*60);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        		}
        	}
    	}
    }
}
