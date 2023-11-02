package org.itson.domaincomponent.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.itson.domaincomponent.dtos.PlayerPickTileDTO;
import org.itson.domaincomponent.enums.Side;
import org.itson.domaincomponent.exceptions.GameException;
import org.itson.domaincomponent.exceptions.PoolException;


/**
 * @author Daniel Armando Peña Garcia ID:229185
 * @author Santiago Bojórquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class Pool extends GameElement {

    /**
     * Private static attribute that holds a single instance of the Pool class.
     */
    private static Pool pool;

    /**
     * Private constructor for the Pool class. Initializes an empty pool of
     * tiles as a new LinkedList.
     */
    Pool() {
        this.tiles = new LinkedList();
    }

    /**
     * Adds tiles to the pool when a player leaves the game. The player's tiles
     * are removed from their collection and placed in the pool.
     *
     * @param tilesList The tile list where the tiles will be added to the pool.
     * @throws PoolException if an error occurs while adding tiles to the pool.
     */
    public void addTiles(LinkedList<Tile> tilesList) throws PoolException {

        if (tilesList == null) {
            throw new PoolException("The tilesList was null");
        } else {
            tiles.addAll(tiles);
        }
    }

    public LinkedList<Tile> createDominoTiles() throws PoolException {

        if (!this.tiles.isEmpty()) {
            throw new PoolException("The Tiles has already created.");
        } else {

            int tileId = 1;

            for (int value1 = 0; value1 <= 6; value1++) {
                for (int value2 = value1; value2 <= 6; value2++) {
                    
                    Tile tile = new Tile(tileId, new FaceTile(Side.TOP, value1), new FaceTile(Side.BOTTOM, value2), isTileMule(value1, value2));
                    tiles.add(tile);

                    tileId++;
                }
            }
            return tiles;
        }
    }

    public boolean isTileMule(Integer value1, Integer value2) {
        return Objects.equals(value1, value2);
    }

    public Tile getHighestMuleOfList(LinkedList<Tile> tiles) throws PoolException {

        if (tiles == null || tiles.isEmpty()) {
            createDominoTiles();
        } else {
            Tile highestMule = tiles.get(0);

            for (int i = 0; i < tiles.size(); i++) {

                Tile tile = tiles.get(i);
                if (tile.isMule()) {
                    if (tile.getFirstFace().getValue() > highestMule.getFirstFace().getValue()
                            && tile.getSecondFace().getValue() > highestMule.getSecondFace().getValue()) {
                        highestMule = tile;
                    }
                }
            }

            return highestMule;
        }
        return null;
    }

    public LinkedList<Tile> getMulesOfPlayerTiles(Player player) throws PoolException {

        if (player.getTiles() == null || player.getTiles().isEmpty()) {
            throw new PoolException("The player doesen't have tiles");
        } else {

            LinkedList<Tile> mulesOfPlayer = new LinkedList<>();
            for (int i = 0; i < player.getTiles().size(); i++) {

                Tile tile = tiles.get(i);

                if (tile == null) {
                    throw new PoolException("The tile recived was null");
                }

                if (tile.isMule()) {
                    mulesOfPlayer.add(tile);
                }
            }

            return mulesOfPlayer;
        }
    }

    /**
     * Retrieves a random tile from the pool.
     *
     * @return A random tile from the pool, or `null` if the pool is empty.
     */
    public Tile getRandomTile() throws PoolException {
        if (!tiles.isEmpty()) {

            Random random = new Random();
            int randomIndex = random.nextInt(tiles.size());
            return tiles.remove(randomIndex);

        } else {
            throw new PoolException("No tiles in the pool to return.");
        }
    }

    /**
     * Adds a tile to the pool.
     *
     * @param tile The tile to be added to the pool.
     */
    public void addTile(Tile tile) throws PoolException {
        if (tile == null) {
            throw new PoolException("The tile reviced was null");
        }
        if (tiles == null) {
            throw new PoolException("The tiles list was null");
        }

        tiles.add(tile);
    }

    /**
     * Checks if the pool is empty
     *
     * @return True if the pool is empty; false otherwise
     * @throws PoolException if the pool of tiles is null (with an optional
     * message)
     */
    public boolean isPoolEmpty() throws PoolException {

        if (this.tiles == null) {
            throw new PoolException("The pool of tiles is null");
        }

        return this.tiles.isEmpty();
    }

    /**
     * Allows a player to pick a tile from the tile pool.
     *
     * @param playerDTO A data transfer object containing player information.
     * @throws PoolException if the tile pool is empty, indicating that no more
     * tiles are available.
     */
    public void pickTileByPlayer(PlayerPickTileDTO playerDTO) throws PoolException {

        if (this.tiles.isEmpty()) {
            throw new PoolException("Tile pool is empty. No more tiles are available.");
        }

        try {
            this.game.pickTileOfPool(playerDTO);
        } catch (GameException e) {
            throw new PoolException(e.getMessage(), e);
        }

    }

    /**
     * Returns a singleton instance of the Pool class. If the instance doesn't
     * exist, it creates one.
     *
     * @return The singleton instance of the Pool.
     */
    public static Pool getInstance() {
        if (pool == null) {
            pool = new Pool();
        }
        return pool;
    }

}
