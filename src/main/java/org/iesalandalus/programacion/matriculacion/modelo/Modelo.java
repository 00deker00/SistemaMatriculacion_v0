package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;

public class Modelo {


    // ATRIBUTOS
    public static final int CAPACIDAD = 100; // Inicializa con una capacidad adecuada

    // Atributos de clases de negocio
    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    // MÉTODOS

    public void comenzar(){ //INSTANCIAS de negocio
        alumnos = new Alumnos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        System.out.println("Sistema de matriculas iniciado");
    }

    public void terminar(){
        System.out.println("El modelo ha terminado");
    }

    //NOTA: EN EL DIAGRAMA DE CLASES PONÍA OTRO ORDEN, NO OBSTANTE, YO LO HE HECHO ASÍ SIGUIENDO EL ORDEN DE CREACIÓN DEL ENUNCIADO.

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null){
            throw new NullPointerException("ERROR no se puede insertar un alumno nulo.");
        }
        alumnos.insertar(alumno);
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null){
            throw new NullPointerException("ERROR no se puede insertar una asignatura nula.");
        }
        asignaturas.insertar(asignatura);
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null){
            throw new NullPointerException("ERROR no se puede insertar un ciclo formativo nulo.");
        }
        ciclosFormativos.insertar(cicloFormativo);
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null){
            throw new NullPointerException("ERROR no se puede insertar una matrícula nula.");
        }
        matriculas.insertar(matricula);
    }



    public Alumno buscar(Alumno alumno) {
        if (alumno==null){
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        return alumnos.buscar(alumno);
    }

    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura==null){
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        return asignaturas.buscar(asignatura);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo==null){
            throw new NullPointerException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }
        return ciclosFormativos.buscar(cicloFormativo);
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula==null){
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        return matriculas.buscar(matricula);
    }



    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null){
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null){
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null){
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null){
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        matriculas.borrar(matricula);
    }


    //Los que van con el metodo get de cada clase
    public Alumno[] getAlumnos() {
        return alumnos.get();
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas.get();
    }

    public CicloFormativo[] getCicloFormativos() {
        return ciclosFormativos.get();
    }

    public Matricula[] getMatriculas() {
        return matriculas.get();
    }




    public Matricula[] getMatriculas(Alumno alumno) {
        if (alumno == null){
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un alumno nulo.");
        }
        return matriculas.get(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un ciclo formativo nulo.");
        } return matriculas.get(cicloFormativo);
    }


    public Matricula[] getMatriculas(String cursoAcademico) {
        if (cursoAcademico==null){
            throw new NullPointerException(" No se puede buscar matrículas de un curso académico nulo.");
        }
        return matriculas.get(cursoAcademico);
    }

}

