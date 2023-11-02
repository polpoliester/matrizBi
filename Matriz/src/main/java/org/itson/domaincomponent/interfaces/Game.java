/**
 * Game.java
 * Oct 12, 2023 6:01:55 PM
 */
package org.itson.domaincomponent.interfaces;


import org.itson.domaincomponent.dtos.PlayerPickTileDTO;
import org.itson.domaincomponent.exceptions.GameException;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public interface Game {

    void pickTileOfPool(PlayerPickTileDTO playerDTO) throws GameException;
}
