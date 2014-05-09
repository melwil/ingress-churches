package net.melwil.churches.gps;

import org.im4java.core.*;

import java.io.IOException;

/**
 * @author Håvard Slettvold
 */
public class GPS {

    public static void main(String[] args) {

        String filename = "img/hagebostad-kirke.jpg";
        double lat = 58.3407;
        double lng = 7.2056;

        markWithGpsTag(filename, lat, lng);

        /*
        try {
            Info imageInfo = new Info("/Users/melwil/git/ingress-churches/churches/img/hagebostad-kirke.jpg", true);
            System.out.println("Format: " + imageInfo.getImageFormat());
            System.out.println("Width: " + imageInfo.getImageWidth());
            System.out.println("Height: " + imageInfo.getImageHeight());
            System.out.println("Geometry: " + imageInfo.getImageGeometry());
            System.out.println("Depth: " + imageInfo.getImageDepth());
            System.out.println("Class: " + imageInfo.getImageClass());
        } catch (InfoException e) {
            e.printStackTrace();
        }
        */
    }

    public static boolean markWithGpsTag(String filename, double lat, double lng) {
        ExiftoolCmd cmd = new ExiftoolCmd();

        ETOperation op = new ETOperation();
        op.setTags(
                "gps:GPSLatitudeRef='N'",
                "gps:GPSLatitude='"+lat+"'",
                "gps:GPSLongitudeRef='W'",
                "gps:GPSLongitude='"+lng+"'"
        );
        op.addImage();

        try {
            cmd.run(op, filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }

        return true;
    }
}
