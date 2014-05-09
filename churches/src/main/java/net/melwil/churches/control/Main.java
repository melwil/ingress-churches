package net.melwil.churches.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.melwil.churches.model.Church;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Espen on 09.05.14.
 */
public class Main {

    public static void main(String[] args) throws IOException {

//        if(Files.exists(Paths.get("churches.json"))) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("File already exists. Are you sure you want to parse everything all over? (Y/N): ");
//            String answer = scanner.nextLine();
//            scanner.close();
//
//            if(answer.equalsIgnoreCase("N")) {
//                System.out.println("Exiting.");
//                System.exit(0);
//            }
//        }
        System.out.println(Paths.get("churches.json").toFile().getAbsolutePath());
        List<Church> churches = ChurchScraper.getChurches();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(churches).toString();
        json = "var data = {'churches':"+ json +"}";
        Files.write(Paths.get("view/churches.json"), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
