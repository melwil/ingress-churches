package net.melwil.churches.control;

import net.melwil.churches.model.Church;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChurchScraper {

    private static Pattern coordsPattern = Pattern.compile("GPS/POI:\\s+(.*?),(.*?)\\s+");

    public static List<Church> getChurches() throws IOException{

        List<Church> churches = new ArrayList<Church>();

        for(String churchUrl : IndexScraper.scrapeIndex()) {
            try {
                Church church = scrapeChurch(churchUrl);
            }
            catch (Exception e) {
                System.out.println(churchUrl);
                e.printStackTrace();
                continue;
            }
            churches.add(scrapeChurch(churchUrl));
        }

        return churches;
    }


    private static Church scrapeChurch(String churchUrl) throws IOException {
        Church church = new Church();
        System.out.println(churchUrl);
        Document doc = Jsoup.connect(churchUrl).timeout(5000).get();

        System.out.println("Got past it!");

        parseCoordinates(doc, church);
        parseChurchName(doc, church);
        parseChurchImages(doc, church);

        return church;
    }


    private static void parseCoordinates(Document doc, Church church) {
        String lat = "";
        String lng = "";
        Elements mapCaption = doc.select(".map_caption");

        Matcher matcher = coordsPattern.matcher(mapCaption.text());

        if(matcher.find()) {
            lat = matcher.group(1);
            lng = matcher.group(2);
        }

        church.setLat(lat);
        church.setLng(lng);
    }

    private static void parseChurchName(Document doc, Church church) {
        String churchName = doc.select("#gallery_header").text();
        church.setName(churchName);
    }


    private static void parseChurchImages(Document doc, Church church) {
        Elements imagesElement = doc.select(".galleria-thumbnails");

        if(imagesElement.size() == 0) return;

        for(Element imageDiv : imagesElement) {
            String imageUrl = imageDiv.child(0).attr("src");
        }
    }
}
