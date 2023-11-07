/**
 * TileController.java
 * Oct 10, 2023 11:06:10 AM
 */
package org.itson.mvc.tile;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Armando Peña Garcia ID:229185
 * @author Santiago Bojórquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class TileController extends MouseAdapter {

    private TileModel tileModel;
    private TileView tileView;

    /**
     *
     */
    public TileController(TileModel tileModel, TileView tileView) {
        this.tileModel = tileModel;
        this.tileView = tileView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        try {

           
                tileModel.setCordX(e.getX() - 50);
                tileModel.setCordY(e.getY() - 120);
                tileView.drawTile();
            

        } catch (IOException ex) {
            Logger.getLogger(TileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
