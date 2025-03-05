package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoE extends Grado {
    private int numEdiciones;

    public GradoE (String nombre, int numAnios, int numEdiciones){
        super(nombre);
        setNumAnios(numAnios);
        setNumEdiciones(numEdiciones);
    }

    public int getNumEdiciones(){
        return numEdiciones;
    }

    public void setNumEdiciones(int numEdiciones){
        if (numEdiciones < 0){
            throw new IllegalArgumentException("EL número de ediciones debe ser mayor que 0.");

        }
        this.numEdiciones = numEdiciones;
    }

    public void setNumAnios(int numAnios){
        if (numAnios != 1){
            throw new IllegalArgumentException("El número de años de los grados de este tipo solo podrá ser 1.");
        }
        this.numAnios = numAnios;

    }


    @Override
    public String toString() {
        return super.toString() +
                " - Años: " + numAnios +
                " - Número de ediciones: " + numEdiciones;

    }



}
