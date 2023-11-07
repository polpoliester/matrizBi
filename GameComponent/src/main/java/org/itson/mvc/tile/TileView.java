/**
 * TileView.java
 * Oct 10, 2023 11:05:56 AM
 */
package org.itson.mvc.tile;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This enumerate class represents a tile's side
 *
 * @author Daniel Armando Peña Garcia ID:229185
 * @author Santiago Bojórquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class TileView extends JPanel {

    private TileModel tileModel;
    private BufferedImage tile;
    private BufferedImage fondo;

    /**
     *
     */
    public TileView(TileModel tileModel) {
        this.tileModel = tileModel;
        try {
            drawTile(); // Carga la imagen de la ficha al crear la vista
        } catch (IOException e) {
            Logger.getLogger(TileController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void drawTile() throws IOException {
        BufferedImage firstFace = ImageIO.read(new File(tileModel.getFirstFacePath()));
        BufferedImage secondFace = ImageIO.read(new File(tileModel.getFirstFacePath()));
        this.fondo = ImageIO.read(new File("src/main/resources/match/Fondo.jpeg"));

        this.tile = new BufferedImage(tileModel.getWidth(), tileModel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = this.tile.createGraphics();
        g2d.drawImage(firstFace, 0, 0, null);
        g2d.drawImage(secondFace, 0, firstFace.getHeight(), null);
        g2d.dispose();
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(fondo, 0, 0, null);
        g.drawImage(this.tile, tileModel.getCordX(), tileModel.getCordY(), null);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 720);
    }

}
