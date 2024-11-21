package org.aura.citronix.Exceptions;

public class DetailRecolteException extends RuntimeException {
    public DetailRecolteException(int id) {
        super("Ce detail recolte n'exist pas avec id : " + id);
    }
}
