/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

import java.awt.Color;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 *
 * @author thank
 */
public class CustomMenuUI extends BasicMenuItemUI {
    
    public CustomMenuUI(Color BG,Color FG){
        super.selectionBackground = BG;
        super.selectionForeground = FG;
    }
}
