/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class getIp {
   
    public static String ip() throws SocketException, UnknownHostException, IOException {
        //System.out.println(InetAddress.getLocalHost().getHostAddress());
        Enumeration Interfaces = NetworkInterface.getNetworkInterfaces();
        while(Interfaces.hasMoreElements())
        {
            NetworkInterface Interface = (NetworkInterface)Interfaces.nextElement();
            Enumeration Addresses = Interface.getInetAddresses();
            while(Addresses.hasMoreElements())
            {
                InetAddress Address = (InetAddress)Addresses.nextElement();
                //System.out.println(Address.getHostAddress());
            }
         }
       
        return "";
    }

    private static void displaySubInterfaces(NetworkInterface netIf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
