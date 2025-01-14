package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
//TODO ES D COLOR
public class Matriculas {




    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;


    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }


    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }


    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]); // Usar el constructor de copia
        }
        return copia;
    }


    public int getCapacidad() {
        return capacidad;
    }


    public int getTamano() {
        return tamano;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matricula nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matriculas.");
        }
        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matricula con ese código.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula); // Usar el constructor de copia
        tamano++;
    }


    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return i;
            }
        }
        return -1; // Si no se encuentra, devolver -1
    }

    private boolean tamanoSuperado(int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("ERROR: El índice no puede ser negativo.");
        }
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("ERROR: El índice no puede ser negativo.");
        }
        return indice >= capacidad;
    }







    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return new Matricula(coleccionMatriculas[i]); // Usar el constructor de copia
            }
        }
        return null;
    }


    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matricula nula.");
        }

        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matricula como la indicada.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método privado para desplazar los elementos una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null; // Liberar el espacio al final del array
    }


    public Matricula[] get(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un alumno nulo.");
        }
        int count = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                count++;
            }
        }
        Matricula[] coleccionPorAlumno = new Matricula[count];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                coleccionPorAlumno[indice++] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return coleccionPorAlumno;
    }


    public Matricula[] get(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un curso académico nulo.");
        }
        int count = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                count++;
            }
        }
        Matricula[] coleccionPorCursoAcademico = new Matricula[count];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                coleccionPorCursoAcademico[indice++] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return coleccionPorCursoAcademico;
    }


    public Matricula[] get(CicloFormativo cicloFormativo) {
//        if (cicloFormativo == null) {
//            throw new NullPointerException("ERROR: No se puede buscar matrículas de un ciclo formativo nulo.");
//        }
        int count = 0;
        for (int i = 0; i < tamano; i++) {
            Asignatura[] asignaturas = coleccionMatriculas[i].getColeccionAsignaturas();
            for (Asignatura asignatura : asignaturas) {
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    count++;
                    break;
                }
            }
        }
        Matricula[] coleccionPorCicloFormativo = new Matricula[count];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            Asignatura[] asignaturas = coleccionMatriculas[i].getColeccionAsignaturas();
            for (Asignatura asignatura : asignaturas) {
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    coleccionPorCicloFormativo[indice++] = new Matricula(coleccionMatriculas[i]);
                    break;
                }
            }
        }
        return coleccionPorCicloFormativo;
    }






}

