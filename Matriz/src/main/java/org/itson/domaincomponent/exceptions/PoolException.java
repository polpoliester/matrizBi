/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.domaincomponent.exceptions;

/**
 *
 * @author PC
 */
public class PoolException extends Exception {

    /**
     * Default constructor
     */
    public PoolException() {
        super();
    }

    /**
     * Constructor with the error message
     *
     * @param message Error message
     */
    public PoolException(String message) {
        super(message);
    }

    /**
     * Constructor with the error message and the cause
     *
     * @param message Error message
     * @param cause Cause of the error
     */
    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

}
