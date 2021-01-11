/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import static runsoft.Runsoft.dir;

/**
 *
 * @author User
 */
public class reload {
    public static void images (HttpServer server){
        
            System.out.println("load");
            File folder = new File(dir +"\\resources\\images");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                String fileNameWithOutExt = FilenameUtils.removeExtension(listOfFiles[i].getName());

                server.createContext("/img" + fileNameWithOutExt + ".png", new images(dir +"\\resources\\images\\" + listOfFiles[i].getName()));
                //server.removeContext(image[i]);
              } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());

              }
            }
    }
    
}
