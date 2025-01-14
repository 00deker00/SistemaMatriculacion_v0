package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;


    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }


    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]); // Usar el constructor de copia
        }
        return copia;
    }


    public int getCapacidad() {
        return capacidad;
    }


    public int getTamano() {
        return tamano;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos[tamano] = new Alumno(alumno); // Usar el constructor de copia
        this.tamano++;
    }



    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
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



    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return new Alumno(coleccionAlumnos[i]); // Usar el constructor de copia
            }
        }
        return null;
    }


    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }


    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null; // Liberar el espacio al final del array
    }
}
