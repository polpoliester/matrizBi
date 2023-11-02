package org.itson.domaincomponent.domain;

/**
 *
 * @author paulvazquez
 */
public class TileMatrix {

    private Tile[][] matrix;

    public TileMatrix(int rows, int columns) {
        matrix = new Tile[rows][columns];
    }

    public void setTile(int x, int y, Tile tile) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
            matrix[x][y] = tile;
        } else {
            System.out.println("Posición fuera de los límites de la matriz.");
        }
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
            return matrix[x][y];
        } else {
            System.out.println("Posición fuera de los límites de la matriz.");
            return null;
        }
    }

    public void displayMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Tile tile = matrix[i][j];
                if (tile != null) {
                    // Accede a las propiedades de las caras (faces) para obtener el contenido
                    String content = tile.getFirstFace().getValue() + "/" + tile.getSecondFace().getValue();
                    System.out.print(content + " ");
                } else {
                    System.out.print("Empty ");
                }
            }
            System.out.println();
        }
    }
}
