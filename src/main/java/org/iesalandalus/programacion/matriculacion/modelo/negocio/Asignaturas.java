package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {



    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    // Constructor con parámetros que inicializa la capacidad y el array de alumnos
    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Método get que devuelve una copia profunda de la colección
    public Asignatura[] get() {
        return copiaProfundaAlumnos();
    }

    // Método privado que realiza una copia profunda de los alumnos
    private Asignatura[] copiaProfundaAlumnos() {
        Asignatura[] copia = new Asignatura[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]); // Usar el constructor de copia
        }
        return copia;
    }

    // Método que devuelve la capacidad
    public int getCapacidad() {
        return capacidad;
    }

    // Método que devuelve el tamaño actual (número de alumnos)
    public int getTamano() {
        return tamano;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        coleccionAsignaturas[tamano] = new Asignatura(asignatura); // Usar el constructor de copia
        tamano++;
    }

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








    // Método para buscar un alumno
    public Asignatura buscar(Asignatura alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(alumno)) {
                return new Asignatura(coleccionAsignaturas[i]); // Usar el constructor de copia
            }
        }
        return null;
    }

    // Método para borrar un alumno
    public void borrar(Asignatura alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }

        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(alumno)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método privado para desplazar los elementos una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null; // Liberar el espacio al final del array
    }


}

