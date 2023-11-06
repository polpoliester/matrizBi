package org.itson.domaincomponent;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
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
                    JPanel cellPanel = new JPanel();
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    cellPanel.addMouseListener(new CellClickListener(tile, i, j));
                    add(cellPanel);
                }
            }
        }
    }

    private class CellClickListener extends MouseAdapter {

        private Tile tile;
        private int row;
        private int column;

        public CellClickListener(Tile tile, int row, int column) {
            this.tile = tile;
            this.row = row;
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Ficha de Dominó: " + tile.getFirstFace().getValue() + " - " + tile.getSecondFace().getValue() + " (Fila: " + row + ", Columna: " + column + ")");
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


