package Enums;

import java.security.PublicKey;

public enum Classrooms {
    sala_1("Sala 1"),
    sala_2("Sala 2"),
    sala_3("Sala 3"),
    sala_4("Sala 4");

    private final String nameString;

    Classrooms(String nameString){
        this.nameString = nameString;
    }
    @Override
    public String toString(){
        return nameString;
    }
}
