package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.ArrayList;

public abstract class Grado {

    protected String nombre;
    protected String iniciales;
    protected int numAnios;



    public Grado (String nombre){
        setNombre(nombre);
    }

    public String getNombre(){
        return nombre;
    }


    protected void setNombre(String nombre){
            if (nombre == null) {
                throw new IllegalArgumentException("El nombre de un grado no puede ser nulo.");
            }

            if (nombre.isBlank() || nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre de un grado no puede estar vacío.");
            }
            this.nombre = nombre;
            //ACTUALIZA LAS INICIALES CADA VEZ QUE SE MODIFIQUE EL NOMBRE DEL GRADO
            setIniciales();
    }


//EL METO DO ESTABLECE EL ATRIBUTO INICIALES QUE COGE EL 1ER CARÁCTER DEL NOMBRE DEL GRADO
    // Y TAMBIÉN LO PASA A MAYÚSCULAS.
    private void setIniciales(){
        String[] nombreSeparadoPorEspaciosArray = nombre.split("\\s+"); // DIVIDIR NOMBRE EN PALABRAS CADA VEZ QUE HAYA UN ESPACIO

        ArrayList <String> palabrasLista = new ArrayList<>();
        for (String palabra : nombreSeparadoPorEspaciosArray){ //Y SE AÑADE A UN ARRAYLIST
            palabrasLista.add(palabra);
        }


        String iniciales = "";
        for (String palabra : palabrasLista) {
            iniciales += palabra.charAt(0); // TOMAR LA PRIMERA LETRA DE CADA PALABRA Y SE AÑADE A LA CADENA DE INICIALES
        }

        this.iniciales = iniciales.toUpperCase();
    }

    //MÉTODO PARA ESTABLECER EL NÚMERO DE AÑOS
    public abstract void setNumAnios(int numAnios);

    @Override
    public String toString() {
        return "(" + iniciales + ")" + " - " + nombre;
    }


//ESTO ES LO QUE HABÍA ANTES
    /*
        GDCFGB("GDCFGB"),
        GDCFGM("GDCFGM"),
        GDCFGS("GDCFGS");

        private int numero;
        private String cadenaAMostrar;

        private Grado(String cadenaAMostrar){
            this.cadenaAMostrar= cadenaAMostrar;
        }

        public String imprimir(){
            return numero + ".-" + cadenaAMostrar;
        }

    @Override
    public String toString() {
        return "Grado{" +
                "numero=" + numero +
                ", cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
 */


}