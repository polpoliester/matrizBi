/**
 * GameInterfaceException.java
 * Oct 14, 2023 5:12:43 PM
 */
package org.itson.domaincomponent.exceptions;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class GameException extends Exception {

    /**
     * Constructs a new GameInterfaceException with no specific message.
     */
    public GameException() {
    }

    /**
     * Constructs a new GameInterfaceException with the specified error message.
     *
     * @param message The error message to describe the exception.
     */
    public GameException(String message) {
        super(message);
    }

    /**
     * Constructs a new GameInterfaceException with the specified error message
     * and a reference to the underlying cause.
     *
     * @param message The error message to describe the exception.
     * @param cause The cause of the exception, if any.
     */
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
