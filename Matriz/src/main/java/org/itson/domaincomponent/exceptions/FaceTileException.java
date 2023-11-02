/**
 * FaceTileException.java
 * Oct 12, 2023 7:34:25 PM
 */
package org.itson.domaincomponent.exceptions;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class FaceTileException extends Exception {

    /**
     * Default constructor
     */
    public FaceTileException() {
        super();
    }

    /**
     * Constructor with the error message
     *
     * @param message Error message
     */
    public FaceTileException(String message) {
        super(message);
    }

    /**
     * Constructor with the error message and the cause
     *
     * @param message Error message
     * @param cause Cause of the error
     */
    public FaceTileException(String message, Throwable cause) {
        super(message, cause);
    }

}
