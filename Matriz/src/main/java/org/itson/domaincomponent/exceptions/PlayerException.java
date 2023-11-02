package org.itson.domaincomponent.exceptions;

/**
 * Custom exception class for player-related errors.
 *
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class PlayerException extends Exception {

    /**
     * Constructs a new PlayerException with no specific message.
     */
    public PlayerException() {
    }

    /**
     * Constructs a new PlayerException with the specified error message.
     *
     * @param msg The error message to describe the exception.
     */
    public PlayerException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new PlayerException with the specified error message and a
     * reference to the underlying cause.
     *
     * @param message The error message to describe the exception.
     * @param cause The cause of the exception, if any.
     */
    public PlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
