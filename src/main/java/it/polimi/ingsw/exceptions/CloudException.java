package it.polimi.ingsw.exceptions;

import java.util.Objects;

public class CloudException extends Exception {
    public CloudException(String message) {
        super(message);
    }
}
