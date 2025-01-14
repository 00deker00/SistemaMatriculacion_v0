package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private String cadenaAMostrar;
    private int numero;

    private Curso(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }


    public String imprimir() {
        return numero + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return ordinal() + ".-"+ cadenaAMostrar;
    }
}

