package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Asignaturas {



    private ArrayList<Asignatura> coleccionAsignaturas;


    public Asignaturas() {

        this.coleccionAsignaturas = new ArrayList<Asignatura>();
    }

    // Método get que devuelve una copia profunda de la colección
    public ArrayList<Asignatura> get() {return copiaProfundaAsignaturas();
    }


    // Método privado que realiza una copia profunda de los alumnos
    private ArrayList<Asignatura> copiaProfundaAsignaturas() {

        ArrayList<Asignatura> copiaAsignatura = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas){
            copiaAsignatura.add(new Asignatura(asignatura));
        }
        return copiaAsignatura;
    }




    // Método que devuelve el tamaño actual (número de alumnos)
    public int getTamano() {
        return coleccionAsignaturas.size();
    }



    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }

        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        coleccionAsignaturas.add(new Asignatura (asignatura));
    }

/*
    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return i;
            }
        }
        return -1; // Si no se encuentra, devolver -1
    }
*/




    // Método para buscar un alumno
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }


        if(coleccionAsignaturas.indexOf(asignatura) == -1){
            return null;
        }else{
            Asignatura asignaturaBuscada = coleccionAsignaturas.get(coleccionAsignaturas.indexOf(asignatura));
            return new Asignatura (asignaturaBuscada);
        }
    }


    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }

        if(coleccionAsignaturas.indexOf(asignatura) == -1){
            throw new IllegalArgumentException("ERROR: No existe la asignatura a borrar");
        }else{
            coleccionAsignaturas.remove(asignatura);
        }


    }

}

