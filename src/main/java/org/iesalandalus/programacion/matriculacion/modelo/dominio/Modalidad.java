package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Modalidad {
    PRESENCIAL("Presencial"),
    SEMIPRESENCIAL("Semipresencial");

    private final String cadenaAMostrar;

    private Modalidad (String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    };

    //ORDINAL DEVUELVE LAS OPCIONES CON UN NÚMERO EMPEZANDO DESDE 0
    //PONER +1?
    public String imprimir() {
        return ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Modalidad{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }



}
