package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado {

    private Modalidad modalidad;

    public GradoD (String nombre, int numAnios, Modalidad modalidad){
        //CONSTRUCTOR DE LA CLASE PADRE (GRADO)
        super(nombre);
        setNumAnios(numAnios);
        setModalidad(modalidad);
    }

    public Modalidad getModalidad(){
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad){
        if(modalidad==null){
            throw new NullPointerException("La modalidad no puede ser nula.");
        }

        this.modalidad=modalidad;
    }

    public void setNumAnios(int numAnios){
        if (numAnios < 2 || numAnios > 3){
            throw new IllegalArgumentException("El número de grados de este tipo solo podrá ser 2 o 3.");
        }
        //heredado
        this.numAnios = numAnios;
    }

    @Override
    public String toString() {
        return super.toString() +
                "- Años: " + numAnios +
                "- Modalidad: " + modalidad;
    }
}
