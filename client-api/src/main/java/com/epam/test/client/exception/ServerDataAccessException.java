package com.epam.test.client.exception;

/**
 * Created by kushnir on 27.2.17.
 */
public class ServerDataAccessException extends RuntimeException {

    public ServerDataAccessException() {
    }

    public ServerDataAccessException(String message) {
        super(message);
    }

    public ServerDataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
