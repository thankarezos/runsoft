/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author User
 */
public class getIp {
   
    public static String ip() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }
       
}
    
