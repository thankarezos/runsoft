/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import java.io.IOException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.Reader;
import java.io.FileReader;
import java.util.Iterator;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.nio.file.Paths;

public class Runsoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String user = "user";
        String pass = "pass3";
        HttpServer server = server(8000,user,pass);
        HttpContext jsonf = server.createContext("/data.json", new json.jsonhand());
        HttpContext images = server.createContext("/plus", new images());
        jsonf.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(user) && pwd.equals(pass);
            }
        });
        String[] list = new String[100];
        create(server,list);
        HttpContext send = server.createContext("/send", new jsonwrite.jsonhand(server,list));
        send.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(user) && pwd.equals(pass);
            }
        });for (int i = 0;i<list.length;i++){
            try {
                if (list[i] != null ){
                        System.out.println(list[i]);
                }
            } catch (Exception ex) {
            }
        }

        
        
    }
    public static void create(HttpServer server,String[] list){
         for (int i = 0;i<list.length;i++){
            try {
                server.removeContext("/" + list[i]);
            } catch (Exception ex) {
            }
        }	
        software[] softweres = new software[100];
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            JsonReader reader = new JsonReader(new FileReader("src/data.json"));

            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            
            int id = 0;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String nameS = "";
                String locS = "";
                Map<?, ?> mapi = (Map)entry.getValue();
                for (Map.Entry<?, ?> entryi : mapi.entrySet()) {
                    if(((String)entryi.getKey()).equals("name")){
                        nameS = (String) entryi.getValue();
                    }
                    else{
                        locS = (String) entryi.getValue();
                    }
                }
                try {
                    
                    list[id] = nameS;
                } catch (Exception ex) {
                }
                
                softweres[id] = new software(locS,nameS,server);
                id++;
            }
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
        
 
    
    
    public static HttpServer server(int p,String user,String pass) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(p), 0);
        server.setExecutor(null); // creates a default executor
        HttpContext ctx = server.createContext("/", new RootHandler());
        
        ctx.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(user) && pwd.equals(pass);
            }
        });
        server.start();
        return server;
        
    }
    
    public static HttpServer server(int p) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(p), 0);
        server.setExecutor(null); // creates a default executor
        HttpContext ctx = server.createContext("/dwadwadw", new RootHandler());
        server.start();
        
        return server;
    }
    
    
    static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            
            String html = "";
            try {
                File myObj = new File("src/index.html");
                try (Scanner myReader = new Scanner(myObj)) {
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html = html +"\n" + data;
                    }
                }
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            
            String response = html;
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    
   
}

    
    
    

