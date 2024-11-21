package org.aura.citronix.Exceptions;

public class RecolteException extends RuntimeException {
    public RecolteException(int id) {
        super("Recolte n'exist pas avec id : " + id);
    }
}
