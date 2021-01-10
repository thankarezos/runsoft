/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static runsoft.Runsoft.create;

/**
 *
 * @author User
 */
public class jsonwrite {
    static class jsonhand implements HttpHandler {
        HttpServer server;
        String[] list;
        jsonhand(HttpServer s,String[] l){
            server = s;
            list = l;
        }
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            StringBuilder sb = new StringBuilder();
            InputStream ios = t.getRequestBody();
            int i;
            while ((i = ios.read()) != -1) {
                sb.append((char) i);
            }
            
            String end = sb.toString();
            
            try {
                    FileWriter myWriter = new FileWriter("src/data.json");
                    myWriter.write(end);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                  } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
            String response = "ok";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            create(server,list);
            
        }
    }
    
    
}
