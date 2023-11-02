/**
 * GameElement.java
 * Oct 12, 2023 6:01:41 PM
 */
package org.itson.domaincomponent.domain;


import java.util.LinkedList;
import org.itson.domaincomponent.interfaces.Game;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class GameElement {

    /**
     * Set of tiles as contained in the game elements
     */
    protected LinkedList<Tile> tiles;

    /**
     * Interface through which game calls are made
     */
    protected Game game;

    /**
     * Default constructor
     *
     */
    public GameElement() {
    }

    /**
     * Retrieves the LinkedList of tiles associated with this Game.
     *
     * @return The LinkedList of tiles in the game.
     */
    public LinkedList<Tile> getTiles() {
        return tiles;
    }

    /**
     * Sets the LinkedList of tiles for this Game.
     *
     * @param tiles The LinkedList of tiles to associate with the game.
     */
    public void setTiles(LinkedList<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     * Retrieves the Game instance associated with these tiles.
     *
     * @return The Game instance to which these tiles belong.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Associates the provided Game instance with these tiles.
     *
     * @param game The Game instance to associate with these tiles.
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
