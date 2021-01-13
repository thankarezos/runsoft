/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import static org.apache.commons.io.IOUtils.DEFAULT_BUFFER_SIZE;

/**
 *
 * @author User
 */
public class icon implements HttpHandler {
        
        String path;
        icon (String p){
            path = p;
        }
        @Override
        public void handle(HttpExchange arg0) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);) {

            File file = new File("icon");

            copyInputStreamToFile(inputStream, file);
            
            arg0.sendResponseHeaders(200, file.length());
            // TODO set the Content-Type header to image/gif 

            OutputStream outputStream=arg0.getResponseBody();
            Files.copy(file.toPath(), outputStream);
            outputStream.close();

        }

            
            
            
            
            
            
        }
        void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

        }
    }
