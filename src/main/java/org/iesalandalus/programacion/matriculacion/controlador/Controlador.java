package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Controlador {
    private Vista vista;
    private Modelo modelo;


    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null || vista == null) {
            throw new NullPointerException("ERROR: Ni el modelo ni la vista pueden ser nulos.");
        }
        this.modelo = modelo;
        this.vista = vista;
        //LLAMAR A CONTROLADOR
        vista.setControlador(this);
    }

    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();

    }

    public void terminar(){
        modelo.terminar();
        vista.terminar();

    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        modelo.insertar(alumno);
    }

    public Alumno buscar (Alumno alumno){
        if (alumno==null){
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        return modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException{
        modelo.borrar(alumno);
    }

    //OBTIENE A TODOS LOS ALUMNOS REGISTRADOS EN EL MODELO.
    public ArrayList<Alumno> getAlumnos(){
        return modelo.getAlumnos();
    }

    public void insertar (Asignatura asignatura)throws OperationNotSupportedException{
        modelo.insertar(asignatura);
    }

    public Asignatura buscar(Asignatura asignatura){
        if (asignatura==null){
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        return modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura)throws OperationNotSupportedException{
        modelo.borrar(asignatura);
    }

    //OBTIENE TODAS LAS ASIGNATURAS REGISTRADAS EN EL MODELO.
    public ArrayList<Asignatura> getAsignaturas(){
        return modelo.getAsignaturas();
    }


    public void insertar(CicloFormativo cicloFormativo)throws OperationNotSupportedException{
        modelo.insertar(cicloFormativo);
    }

    public CicloFormativo buscar(CicloFormativo cicloformativo){
        if(cicloformativo==null){
            throw new NullPointerException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }
        return modelo.buscar(cicloformativo);
    }

    public void borrar(CicloFormativo cicloFormativo)throws OperationNotSupportedException{
        modelo.borrar(cicloFormativo);
    }

    //OBTIENE A TODOS LOS CICLOS FORMATIVOS REGISTRADOS EN EL MODELO.
    public ArrayList<CicloFormativo> getCiclosFormativos(){
        return modelo.getCicloFormativos();
    }


    public void insertar(Matricula matricula)throws OperationNotSupportedException{
        modelo.insertar(matricula);
    }

    public Matricula buscar(Matricula matricula){
        if (matricula==null){
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        return modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        modelo.borrar(matricula);
    }

    //OBTIENE TODAS LAS MATRICULAS REGISTRADAS EN EL MODELO.
    public ArrayList<Matricula> getMatriculas(){
        return modelo.getMatriculas();
    }


    //OBTIENE LAS MATRICULAS DE UN ALUMNO CONCRETO.
    public ArrayList<Matricula> getMatriculas(Alumno alumno){
        if(alumno == null){
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un alumno nulo");
        }
        return modelo.getMatriculas(alumno);
    }

    //OBTIENE LAS MATRICULAS DE UN CICLO FORMATIVO EN CONCRETO.
    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo){
        if(cicloFormativo==null){
            throw new NullPointerException("ERROR: No se puede buscar matriculas de un ciclo formativo nulo.");
        }
        return modelo.getMatriculas(cicloFormativo);
    }

    //OBTIENE LAS MATRICULAS DE UN CURSO ACADÉMICO EN CONCRETO.
    public ArrayList<Matricula> getMatriculas(String cursoAcademico){
        if (cursoAcademico==null){
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un curso académico nulo");
        }
        return modelo.getMatriculas(cursoAcademico);
    }
















}


