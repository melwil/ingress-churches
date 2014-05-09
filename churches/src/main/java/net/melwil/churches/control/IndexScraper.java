package net.melwil.churches.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.melwil.churches.model.Church;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class IndexScraper {
    
    private static String siteRoot = "http://www.kirkesok.no";
    private static String indexRoot = "http://www.kirkesok.no/content/search/(offset)/";
    private static String searchString = "?SearchText=&byggeaar_fra=900&byggeaar_til=3000&";
    
    public static void main(String[] args) throws IOException {
        
        List<Church> churches = new ArrayList<Church>();
        
        if(Files.exists(Paths.get("churches.json"))) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("File already exists. Are you sure you want to parse everything all over? (Y/N): ");
            String answer = scanner.nextLine();
            scanner.close();
            
            if(answer.equalsIgnoreCase("N")) {
               System.out.println("Exiting.");
               System.exit(0); 
            }
        }
        
        try {
            for(String church : scrapeIndex()) {
                churches.add(new Church(church));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        
        String json = gson.toJson(churches).toString();
        Files.write(Paths.get("churches.json"), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    
    public static List<String> scrapeIndex() throws IOException {
        
        List<String> links = new ArrayList<String>();
        Elements tables = null;
        int offset = -20;
        
        do {
            offset += 20;
            String currentUrl = String.format("%s%s%s", indexRoot, offset, searchString);
            System.out.println("Fetching offset: " + offset);
            
            Document doc = Jsoup.connect(currentUrl).get();
            tables = doc.select("table");
            for(Element tableElement : tables) {
                Elements relevantTableData = tableElement.select("tbody > tr > td");
                String kirke = relevantTableData.get(0).select("a").get(0).attr("href");
                links.add(String.format("%s%s", siteRoot, kirke));
            }
            break;
        }
        while(tables.size() != 0);
        
        return links;
    }
}
