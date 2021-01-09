/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author User
 */
public class software {
        
    private String location;
    private String name;
    private HttpServer server;
    private String user;
    private String pass;
    HttpHandler handler;
    HttpContext ctx;
    handler myhandler;
    software(String l,String n,String u,String p,HttpServer s){
        location = l; 
        server = s;
        name = n;
        user = u;
        pass = p;
        try {
            server.removeContext("/" + name);
        } catch (Exception ex) {
        }
        
        myhandler = new handler(location,name);
        ctx = server.createContext("/" + name, myhandler);
        ctx.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(user) && pwd.equals(pass);
            }
        });
    }
    software(String l,String n,HttpServer s) throws IOException{
        location = l; 
        server = s;
        name = n;
        try {
            server.removeContext("/" + name);
        } catch (Exception ex) {
        }
        
        myhandler = new handler(location,name);
        ctx = server.createContext("/" + name, myhandler);
    }
    
    
    
    @Override
    public String toString(){
        return "inside " + name + " " + location;
    }
    
    
    
    
    
}
