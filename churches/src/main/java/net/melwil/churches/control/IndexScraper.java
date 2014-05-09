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
