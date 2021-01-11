/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author User
 */
public class handler implements HttpHandler{
        private String name;
        private String location;
        handler(String l,String n){name = n; location =  "\"" + l + "\"";;}
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            System.out.println(name + " " + location);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            run();
        }
        void run() throws IOException{
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", location);
                Process process = pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        
 }

