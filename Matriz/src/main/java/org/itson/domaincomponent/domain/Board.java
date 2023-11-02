/**
 * Board.java
 * Oct 12, 2023 5:59:50 PM
 */
package org.itson.domaincomponent.domain;


import java.util.LinkedList;
import org.itson.domaincomponent.exceptions.BoardException;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class Board extends GameElement {

    /**
     * Private static attribute that holds a single instance of the Board class.
     */
    private static Board board;

    /**
     * Private constructor for the Board class. Initializes the `tiles`
     * attribute as a new LinkedList.
     */
    private Tile[][] dominoMatrix;//arreglo bidimensional
    
    private Board() {
        this.tiles = new LinkedList();
    }
    //metodos para modificar mtriz
    public Board(int rows, int columns) {
        dominoMatrix = new Tile[rows][columns];
    }

    public Tile getTileAtPosition(int row, int column) {
        return dominoMatrix[row][column];
    }

    public void setTileAtPosition(int row, int column, Tile tile) {
        dominoMatrix[row][column] = tile;
    }
    
    public int getRows() {
        return dominoMatrix.length;
    }
    
    public int getColumns() {
        if (dominoMatrix.length > 0) {
            return dominoMatrix[0].length;
        } else {
            return 0;
        }
    }

    /**
     * Adds a tile to the beginning of the list of tiles, considering the type
     * of the first tile and its compatibility with the new tile.
     *
     * @param newTile The tile to be added to the beginning.
     * @throws BoardException if the new tile cannot be added due to
     * compatibility issues.
     */
    public void addTileBeginning(Tile newTile) throws BoardException {
        if (newTile == null) {
            throw new BoardException("Cannot add a null tile to the board.");
        }

        if (this.tiles.isEmpty() && !newTile.isMule()) {
            throw new BoardException("Cannot add a non-mule tile as the first tile.");
        }

        if (this.tiles.isEmpty() && newTile.isMule()) {
            this.tiles.add(newTile);
            return;
        }

        Tile firstTile = tiles.getFirst();

        if (newTile.isMule() && firstTile.isMule()) {
            throw new BoardException("Two mule tiles cannot be connected.");
        }

        if (firstTile.isMule()) {
            addTileBeginningForMule(newTile, firstTile);
        } else {
            addTileBeginningForNonMule(newTile, firstTile);
        }
    }

    /**
     * Adds a tile to the beginning of the list when the first tile is a mule.
     *
     * @param newTile The tile to be added to the beginning.
     * @param firstTile The first tile in the list.
     */
    private void addTileBeginningForMule(Tile newTile, Tile firstTile) throws BoardException {

        if (newTile.getOrientation() != firstTile.getOrientation()) {
            FaceTile newTileRightFace = newTile.getRightFace();
            FaceTile firstTileFace = firstTile.getFirstFace();

            if (newTileRightFace.getValue() == firstTileFace.getValue()) {
                tiles.addFirst(newTile);
            } else {
                throw new BoardException("The right face must be equal to the mule's values.");
            }
        } else {
            throw new BoardException("The orientation must be perpendicular to the mule.");
        }
    }

    /**
     * Adds a tile to the beginning of the list when the first tile is not a
     * mule.
     *
     * @param newTile The tile to be added to the beginning.
     * @param firstTile The first tile in the list.
     */
    private void addTileBeginningForNonMule(Tile newTile, Tile firstTile) throws BoardException {

        if (!newTile.isMule()) {

            if (newTile.getOrientation() == firstTile.getOrientation()) {

                FaceTile newTileRightFace = newTile.getRightFace();
                FaceTile firstTileLeftFace = firstTile.getLeftFace();

                if (newTileRightFace.getValue() == firstTileLeftFace.getValue()) {
                    tiles.addFirst(newTile);
                } else {
                    throw new BoardException("The right face must be equal to the tile's left face value.");
                }
            } else {
                throw new BoardException("The orientation must be equal to the tile.");
            }
        }

        if (newTile.isMule()) {
            if (newTile.getOrientation() != firstTile.getOrientation()) {
                FaceTile getFirstFace = newTile.getFirstFace();
                FaceTile firstTileLeftFace = firstTile.getLeftFace();

                if (getFirstFace.getValue() == firstTileLeftFace.getValue()) {
                    tiles.addFirst(newTile);
                } else {
                    throw new BoardException("Cannot connect a mule tile to another tile if the left face values do not match.");
                }
            } else {
                throw new BoardException("Cannot connect a mule tile to another tile with the same orientation.");
            }
        }

    }

    /**
     * Adds a tile to the end of the list of tiles, considering the type of the
     * last tile and its compatibility with the new tile.
     *
     * @param newTile The tile to be added to the end.
     * @throws BoardException if the new tile cannot be added due to
     * compatibility issues.
     */
    public void addTileEnd(Tile newTile) throws BoardException {
        if (newTile == null) {
            throw new BoardException("Cannot add a null tile to the board.");
        }

        if (this.tiles.isEmpty() && !newTile.isMule()) {
            throw new BoardException("Cannot add a non-mule tile as the first tile.");
        }

        if (this.tiles.isEmpty() && newTile.isMule()) {
            this.tiles.add(newTile);
            return;
        }

        Tile lastTile = tiles.getLast();

        if (newTile.isMule() && lastTile.isMule()) {
            throw new BoardException("Two mule tiles cannot be connected.");
        }

        if (lastTile.isMule()) {
            addTileEndForMule(newTile, lastTile);
        } else {
            addTileEndForNonMule(newTile, lastTile);
        }
    }

    /**
     * Adds a tile to the end of the list when the last tile is a mule.
     *
     * @param newTile The tile to be added to the end.
     * @param lastTile The last tile in the list.
     * @throws BoardException If the left face is not equal to the tile's left
     * face value or if the orientation is not different from the last tile's
     * orientation.
     */
    private void addTileEndForMule(Tile newTile, Tile lastTile) throws BoardException {
        if (newTile.getOrientation() != lastTile.getOrientation()) {
            FaceTile newTileLeftFace = newTile.getLeftFace();
            FaceTile lastTileFace = lastTile.getFirstFace();
            if (newTileLeftFace.getValue() == lastTileFace.getValue()) {
                tiles.addLast(newTile);
            } else {
                throw new BoardException("The left face must be equal to the mule's values.");
            }
        } else {
            throw new BoardException("The orientation must be perpendicular to the mule.");
        }
    }

    /**
     * Adds a tile to the end of the list when the last tile is not a mule.
     *
     * @param newTile The tile to be added to the end.
     * @param lastTile The last tile in the list.
     * @throws BoardException If the left face is not equal to the tile's left
     * face value or if the orientation is not different from the last tile's
     * orientation.
     */
    private void addTileEndForNonMule(Tile newTile, Tile lastTile) throws BoardException {

        if (!newTile.isMule()) {

            if (newTile.getOrientation() == lastTile.getOrientation()) {

                FaceTile newTileLeftFace = newTile.getRightFace();
                FaceTile firstTileRightFace = lastTile.getLeftFace();

                if (newTileLeftFace.getValue() == firstTileRightFace.getValue()) {
                    tiles.addFirst(newTile);
                } else {
                    throw new BoardException("The left face must be equal to the tile's right face value.");
                }
            } else {
                throw new BoardException("The orientation must be equal to the tile.");
            }
        }

        if (newTile.isMule()) {
            if (newTile.getOrientation() != lastTile.getOrientation()) {
                FaceTile getFirstFace = newTile.getFirstFace();
                FaceTile firstTileRightFace = lastTile.getLeftFace();

                if (getFirstFace.getValue() == firstTileRightFace.getValue()) {
                    tiles.addFirst(newTile);
                } else {
                    throw new BoardException("Cannot connect a mule tile to another tile if the right face values do not match.");
                }
            } else {
                throw new BoardException("Cannot connect a mule tile to another tile with the same orientation.");
            }
        }
    }

    /**
     * Returns a singleton instance of the Board class. If the instance doesn't
     * exist, it creates one.
     *
     * @return The singleton instance of the Board.
     */
    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    

}
