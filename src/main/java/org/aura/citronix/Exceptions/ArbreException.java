package org.aura.citronix.Exceptions;

public class ArbreException extends RuntimeException{

    public ArbreException(int id){
        super("Arbre n'exist pas avec id : " + id);
    }
}
