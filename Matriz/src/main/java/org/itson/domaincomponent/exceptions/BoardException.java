/**
* BoardException.java
* Oct 14, 2023 10:13:09 AM
*/ 

package org.itson.domaincomponent.exceptions;
/**
 * Descripci�n de la clase: 
 * 
 * @author Daniel Armando Pe�a Garcia ID:229185
 */
public class BoardException extends Exception{

    public BoardException() {
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
