/**
 * DeleteTileDTO.java
 * Oct 15, 2023 9:15:50 PM
 */
package org.itson.domaincomponent.dtos;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class DeleteTileFromPlayerDTO {

    private Integer id;

    /**
     * Constructs a DeleteTileFromPlayerDTO object with the specified ID.
     *
     * @param id The ID of the tile to be deleted from the player's collection.
     */
    public DeleteTileFromPlayerDTO(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the tile to be deleted.
     *
     * @return The ID of the tile.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the tile to be deleted.
     *
     * @param id The new ID to be assigned for the tile deletion.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
