package org.itson.domaincomponent;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.random;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.itson.domaincomponent.domain.Board;
import org.itson.domaincomponent.domain.Pool;
import org.itson.domaincomponent.domain.Tile;
import org.itson.domaincomponent.exceptions.PoolException;

public class DominoBoardForm extends JFrame {

    private Board board;

    public DominoBoardForm(Board board) throws PoolException {
        this.board = board;

        // Configurar el formulario
        setTitle("Tablero de Dominó");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int rows = board.getRows();
        int columns = board.getColumns();

        // Crear un GridLayout para organizar las celdas
        setLayout(new GridLayout(rows, columns));

        // Crear paneles para cada celda
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellPanel.addMouseListener(new CellClickListener(i, j));
                add(cellPanel);
            }
        }

        // Generar fichas de dominó aleatorias para cada celda
        Pool pool = Pool.getInstance();
        
        //se crean las fichas
        LinkedList<Tile> tiles = pool.createDominoTiles();
  
         int counter = 0;
        for (int i = 0; i < rows; i++) {
           
            for (int j = 0; j < columns; j++) {
                if (!tiles.isEmpty()) {
                    counter++;
                    board.setTileAtPosition(i, j, tiles.get((int) (Math.random() * 27)));
                }
                
            }
            
        }
    }

    private class CellClickListener extends MouseAdapter {

        private int row;
        private int column;

        public CellClickListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Obtener la ficha de dominó en la celda y mostrar información
            Tile tile = board.getTileAtPosition(row, column);
            if (tile != null) {
                JOptionPane.showMessageDialog(null, "Ficha de Dominó: " + tile.getFirstFace().getValue() + " - " + tile.getSecondFace().getValue());
            }
        }
    }

    private void shuffleTiles(LinkedList<Tile> tiles) {
        // Método para barajar las fichas de dominó
        Random random = new Random();
        for (int i = tiles.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Tile temp = tiles.get(index);
            tiles.set(index, tiles.get(i));
            tiles.set(i, temp);
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

