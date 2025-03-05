package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {

    GRADOD("Grado D"),
    GRADOE("Grado E");

    private final String cadenaAMostrar;

    private TiposGrado (String cadenaAMostrar){
        this.cadenaAMostrar= cadenaAMostrar;
    }

    //EL ORDINAL DEVUELVE EL NUMERO EMPEZANDO DESDE 0
    public String imprimir(){
        return ordinal() + ".-" + cadenaAMostrar;
    }


}
