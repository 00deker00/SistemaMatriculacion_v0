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
import java.util.ArrayList;


public class Modelo {


    // ATRIBUTOS
    //public static final int CAPACIDAD = 100; // Inicializa con una capacidad adecuada

    // Atributos de clases de negocio
    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    // MÉTODOS

    public void comenzar(){ //INSTANCIAS de negocio
        alumnos = new Alumnos();
        matriculas = new Matriculas();
        asignaturas = new Asignaturas();
        ciclosFormativos = new CiclosFormativos();
        System.out.println("Sistema de matriculas iniciado");
    }

    public void terminar(){
        System.out.println("El modelo ha terminado");
    }



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


    /*Métodos buscar que devuelven la instancia del elemento encontrado si existe*/

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


    /*Métodos borrar*/
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


    //Métodos get que devuelven la lista de los diferentes elementos del programa
    public ArrayList<Alumno> getAlumnos() {
        return alumnos.get();
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas.get();
    }

    public ArrayList<CicloFormativo> getCicloFormativos() {
        return ciclosFormativos.get();
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas.get();
    }





    public ArrayList<Matricula> getMatriculas(Alumno alumno) {
        if (alumno == null){
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un alumno nulo.");
        }


        ArrayList<Matricula> matriculas = getMatriculas();
        ArrayList<Matricula> matriculasAlumno = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                matriculasAlumno.add(matricula);
            }
        }

        return matriculasAlumno;




        /*
        ArrayList<Matricula> matriculas = getMatriculas();
        ArrayList<Matricula> matriculasAlumno = matriculas.stream()
                .filter(matricula -> matricula != null && matricula.getAlumno().equals(alumno))
                .collect(Collectors.toList());

        return matriculasAlumno;
        */

        /*return matriculas.get(alumno);*/
    }

    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un ciclo formativo nulo.");
        }
        /*ArrayList<Matricula> matriculas =getMatriculas();

        ArrayList<Matricula> matriculasCiclo = matriculas.stream().filter(matricula ->matricula != null && matricula.getColeccionAsignaturas().stream()
                        .anyMatch(asignatura -> asignatura.getCicloFormativo().equals(cicloFormativo)))
                .collect(Collectors.toList());

        return matriculasCiclo;*/


        ArrayList<Matricula> matriculas = getMatriculas();
        ArrayList<Matricula> matriculasCiclo = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            if (matricula != null) {
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                    if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        matriculasCiclo.add(matricula);
                        break;
                    }
                }
            }
        }

        return matriculasCiclo;



}


    public ArrayList<Matricula> getMatriculas(String cursoAcademico) {
        if (cursoAcademico==null){
            throw new NullPointerException(" No se puede buscar matrículas de un curso académico nulo.");
        }


        /*ArrayList<Matricula> matriculasCurso = matriculas.stream()
                .filter(matricula -> matricula != null  && matricula.getCursoAcademico().equals(cursoAcademico))
                .collect(Collectors.toList());
        */

        ArrayList<Matricula> matriculas = new ArrayList<>(getMatriculas());
        ArrayList<Matricula> matriculasCurso = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            if (matricula != null && cursoAcademico.equals(matricula.getCursoAcademico())) {
                matriculasCurso.add(matricula);
            }
        }

        return matriculasCurso;
    }

}

