package org.itson.domaincomponent;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.itson.domaincomponent.domain.Board;
import org.itson.domaincomponent.domain.Pool;
import org.itson.domaincomponent.domain.Tile;
import org.itson.domaincomponent.exceptions.PoolException;
import org.itson.mvc.tile.TileController;
import org.itson.mvc.tile.TileModel;
import org.itson.mvc.tile.TileView;

public class DominoBoardForm extends JFrame {

    private Board board;

    public DominoBoardForm(Board board) throws PoolException {
        this.board = board;

        setTitle("Tablero de Dominó");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int rows = board.getRows();
        int columns = board.getColumns();

        setLayout(new GridLayout(rows, columns));

        LinkedList<Tile> tiles = Pool.getInstance().createDominoTiles();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!tiles.isEmpty()) {
                    Tile tile = tiles.poll();
                    
                    // Crear una instancia de TileModel, TileView y TileController
                    TileModel tileModel = new TileModel(tile); // Debes crear un constructor que acepte un Tile
                    TileView tileView = new TileView(tileModel);
                    TileController tileController = new TileController(tileModel, tileView);

                    // Configurar el panel de celda
                    JPanel cellPanel = new JPanel();
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    cellPanel.addMouseListener(tileController);
                    add(cellPanel);
                }
            }
        }
    }

    public static void main(String[] args) throws PoolException {
        int rows = 8;
        int columns = 8;

        Board board = new Board(rows, columns);
        DominoBoardForm form = new DominoBoardForm(board);
        form.pack();
        form.setVisible(true);
    }
}
