package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.sql.Array;
import java.util.ArrayList;

public class Alumnos {

    private ArrayList<Alumno> coleccionAlumnos;


    public Alumnos() {

        this.coleccionAlumnos = new ArrayList<Alumno>();
    }


    public ArrayList<Alumno> get() {
        return copiaProfundaAlumnos();

    }

    private ArrayList<Alumno> copiaProfundaAlumnos() {

        ArrayList<Alumno> copiaAlumno = new ArrayList<>();
        for (Alumno aaa : coleccionAlumnos) {
            copiaAlumno.add(new Alumno (aaa));
        }

        return copiaAlumno;
    }





    public int getTamano() {
        return coleccionAlumnos.size();
    }



    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos.add(new Alumno (alumno));
    }


/*
    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }

        return coleccionAlumnos.indexOf(alumno);

    }

*/

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }


        if (coleccionAlumnos.indexOf(alumno) == -1){
            return null;
        }else{
            Alumno alumnoA = coleccionAlumnos.get(coleccionAlumnos.indexOf(alumno));
            return new Alumno (alumnoA);
        }

    }


    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        if(coleccionAlumnos.indexOf(alumno) ==-1){
            throw new IllegalArgumentException("ERROR: No existe el alumno a borrar.");
        }

        coleccionAlumnos.remove(alumno);


    }

}
