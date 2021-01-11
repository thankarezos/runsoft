/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class json {
    static class jsonhand implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            StringBuilder sb = new StringBuilder();
            InputStream ios = t.getRequestBody();
            int i;
            while ((i = ios.read()) != -1) {
                sb.append((char) i);
            }
            
            String end = sb.toString();
            
            String html = "";
            try {
                File myObj = new File("resources\\data.json");
                try (Scanner myReader = new Scanner(myObj)) {
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html = html + data + "\n";
                    }
                }
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            String response = html;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
