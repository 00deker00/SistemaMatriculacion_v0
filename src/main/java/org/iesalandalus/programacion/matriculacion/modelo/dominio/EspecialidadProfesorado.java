package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum EspecialidadProfesorado {


    INFORMATICA("INFORMÁTICA"),
    SISTEMAS("SISTEMAS"),
    FOL("FOL");

    private String cadenaAMostrar;
    private int numero;

    private EspecialidadProfesorado(String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    public String imprimir(){
        return numero + ".-" + cadenaAMostrar;
    }


    @Override
    public String toString() {
        return ordinal() + ".- EspecialidadProfesorado=" + cadenaAMostrar;
    }
}

