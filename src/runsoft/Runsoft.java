/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import java.io.FileReader;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.awt.AWTException;
public class Runsoft {
    public static String dir = System.getProperty("user.dir");
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, AWTException {
        config.getCredentials();
        
        System.out.println(getIp.ip()+":" + config.getPort());
        tray.Tray(args);
        
        System.out.println(dir + "\\resources\\data.json");
        
        HttpServer server = server(config.getPort(),config.getUser(),config.getPass());
        HttpContext jsonf = server.createContext("/data.json", new json.jsonhand());
        jsonf.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(user) && pwd.equals(config.getPass());
            }
        });
        
        HttpContext plus = server.createContext("/plus.ico", new icon("plus.ico"));
        
        
        String[] list = new String[100];
        create(server,list);
        HttpContext send = server.createContext("/send", new jsonwrite.jsonhand(server,list));
        
        
        send.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(config.getUser()) && pwd.equals(config.getPass());
            }
        });for (int i = 0;i<list.length;i++){
            try {
                if (list[i] != null ){
                        
                }
            } catch (Exception ex) {
            }
        }
        reload.images(server);
        Scanner myObj = new Scanner(System.in);
        
        
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
            JsonReader reader = new JsonReader(new FileReader(dir +"\\resources\\data.json"));

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
                
                softweres[id] = new software(locS,nameS,config.getUser(),config.getPass(),server);
                id++;
            }
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
        
 
    
    
    public static HttpServer server(int p,String user,String pass) throws IOException{
        HttpServer server = null;
        try{
            server = HttpServer.create(new InetSocketAddress(p), 0);
            server.setExecutor(null); // creates a default executor
            HttpContext ctx = server.createContext("/", new RootHandler(server));

            ctx.setAuthenticator(new BasicAuthenticator("get") {
                @Override
                public boolean checkCredentials(String user, String pwd) {
                    return user.equals(user) && pwd.equals(pass);
                }
            });
            server.start();
            return server;
        }catch (Exception ex){
            methods.infoBox("Already Opened", "RunSoft");
            System.exit(1); 
            
        }
        
        
        return server;
    }
    
    public static HttpServer server(int p) throws IOException{
        HttpServer server = null;
        try{
            server = HttpServer.create(new InetSocketAddress(p), 0);
            server.setExecutor(null); // creates a default executor
            HttpContext ctx = server.createContext("/", new RootHandler(server));
            server.start();
            return server;
        }catch (Exception ex){
            methods.infoBox("Already Opened", "RunSoft");
            System.exit(1); 
            
        }
        
        
        return server;
    }
    
    
    static class RootHandler implements HttpHandler {
        HttpServer server;
        
        RootHandler(HttpServer s){
            server = s;
            
        }
        @Override
        public void handle(HttpExchange he) throws IOException {
            
            String html = "";
            try {
                File myObj = new File(dir +"\\resources\\index.html");
                html = html +"\n<script>";
                html = html +"\nvar user = \"" + config.getUser() + "\"";
                html = html +"\nvar pass = \"" + config.getPass() + "\"";
                html = html +"\n</script>";
                    
                    
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
            
            reload.images(server);
            
            
            String response = html;
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    
   
}

    
    
    

