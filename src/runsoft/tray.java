/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 *
 * @author thank
 */
public class tray {
    public static Color BG = new Color(42,41,38);
    public static Color TEXT = Color.WHITE;
    public static void Tray(String[] args) throws IOException, AWTException{
        
        
        if(!SystemTray.isSupported()){
            System.out.println("System tray is not supported !!! ");
            return ;
        }
        //get the systemTray of the system
        SystemTray systemTray = SystemTray.getSystemTray();
        //get default toolkit
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        //get image 
        
        
        InputStream input =  Thread.currentThread().getContextClassLoader().getResourceAsStream("icon.png");
        Image image = ImageIO.read(input);
        JTrayIcon Jtray = new JTrayIcon(image,"Runsoft");
        
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(BG);
        
        popup.setBorder(BorderFactory.createMatteBorder(
                                  -1, -1, -1, -1, Color.BLACK));
        JMenuItem items[] = new JMenuItem[4];
        CustomMenuUI menuUI[] = new CustomMenuUI[4];
        for (int i=0;i<items.length;i++){
            
            items[i] = new JMenuItem();
            menuUI[i] = new CustomMenuUI(new Color(24,24,22),Color.WHITE);
            items[i].setUI(menuUI[i]);
            items[i].setBackground(BG);
            items[i].setForeground(TEXT);
            
            //items[i].setBorder(BorderFactory.createLineBorder(Color.black));
            
        }
        
        JMenu subMenu = new JMenu("Config");
        
        CustomSubmenuUI subMenuUi = new CustomSubmenuUI(new Color(24,24,22),Color.WHITE);
        subMenu.setUI(subMenuUi);
        
        
        
        JMenuItem subMenuItems[] = new JMenuItem[3];
        CustomMenuUI SubmenuItemsUI[] = new CustomMenuUI[3];
        for (int i=0;i<subMenuItems.length;i++){
            
            subMenuItems[i] = new JMenuItem();
            SubmenuItemsUI[i] = new CustomMenuUI(new Color(24,24,22),Color.WHITE);
            
            subMenuItems[i].setUI(SubmenuItemsUI[i]);
            subMenuItems[i].setBackground(BG);
            subMenuItems[i].setForeground(TEXT);
            
            //items[i].setBorder(BorderFactory.createLineBorder(Color.black));
            
        }
        
        
        
        
        popup.add(items[0]);
        popup.add(new JSeparator());
        popup.add(subMenu);
        popup.add(new JSeparator());
        subMenu.setForeground(TEXT);
        popup.add(items[1]);
        popup.add(items[2]);
        
        
        
        items[0].setText("IP:  " + getIp.ip()+ ":" + config.getPort());
        items[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    methods.openWebpage("http://" + getIp.ip() + ":" + config.getPort());
                }
                catch (Exception ex){
                        
                }
            }
        });
        
        
        items[1].setText("Restart");
        items[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    methods.restartApplication(args);
                }
                catch (Exception ex){
                        
                }
            }
        });
        
        items[2].setText("Close");
        items[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    System.exit(0);
                }
                catch (Exception ex){
                        
                }
            }
        });
        
        subMenu.add(subMenuItems[0]);
        subMenu.add(subMenuItems[1]);
        popup.add(new JSeparator());
        subMenu.add(subMenuItems[2]);
        
        subMenuItems[0].setText("Change Username");
        subMenuItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    config.setUser(JOptionPane.showInputDialog(null,
                        "Change Username", "Username",1));
                    config.setProperties();
                    methods.restartApplication(args);
                }
                catch (Exception ex){
                        
                }
            }
        });
        subMenuItems[1].setText("Change Password");
        subMenuItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JPasswordField jpf = new JPasswordField(24);
                    Box box = Box.createHorizontalBox();
                    box.add(jpf);
                    int x = JOptionPane.showConfirmDialog(null, box, "Change Password", JOptionPane.OK_CANCEL_OPTION);

                    if (x == JOptionPane.OK_OPTION) {
                      config.setPass(jpf.getText());
                    }
                    config.setProperties();
                    methods.restartApplication(args);
                }
                catch (Exception ex){
                        
                }
            }
        });
        
        subMenuItems[2].setText("Set Port");
        subMenuItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    config.setPort(parseInt(JOptionPane.showInputDialog(null,
                        "Change port","port", 1)));
                        config.setProperties();
                        methods.restartApplication(args);
                }
                catch (Exception ex){
                        
                }
            }
        });
        
        Jtray.setImageAutoSize(true);
        Jtray.setJPopupMenu(popup);
        systemTray.add(Jtray);
        Jtray.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                try {
                    methods.openWebpage("http://" + getIp.ip() + ":" + config.getPort());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Runsoft.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }); 
        
       Jtray.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    popup.setLocation(e.getX(), e.getY()-100);
                    popup.setInvoker(popup);
                    popup.setVisible(true);
                }
            }
        });
       
       Jtray.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    popup.setLocation(e.getX(), e.getY()-100);
                    popup.setInvoker(popup);
                    popup.setVisible(true);
                }
            }
        });
        
    }
    
}
