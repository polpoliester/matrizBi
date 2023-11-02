/**
 * MatchException.java
 * Oct 12, 2023 7:35:12 PM
 */
package org.itson.domaincomponent.exceptions;

/**
 * @author Daniel Armando Pe�a Garcia ID:229185
 * @author Santiago Boj�rquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class MatchException extends Exception {

    public MatchException() {
    }

    public MatchException(String message) {
        super(message);
    }

    public MatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
