/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runsoft;

/**
 *
 * @author thank
 */
import java.awt.Window;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class TranslucentPopup extends JPopupMenu {

    {
        // need to disable that to work
        setLightWeightPopupEnabled(false);
    }


}
