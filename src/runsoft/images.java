/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 *
 * @author User
 */
public class images implements HttpHandler {
        String filename;
        
        @Override
        public void handle(HttpExchange arg0) throws IOException {
            File file = new File("src/plus.ico");
            arg0.sendResponseHeaders(200, file.length());
            // TODO set the Content-Type header to image/gif 

            OutputStream outputStream=arg0.getResponseBody();
            Files.copy(file.toPath(), outputStream);
            outputStream.close();
        }
    }
