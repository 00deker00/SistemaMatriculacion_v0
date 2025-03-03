package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;




public class Matriculas {


    private ArrayList<Matricula> coleccionMatriculas;


    public Matriculas() {

        this.coleccionMatriculas = new ArrayList<Matricula>();
    }


    public ArrayList<Matricula> get() {
        return copiaProfundaMatriculas();
    }


    private ArrayList<Matricula> copiaProfundaMatriculas() {

        ArrayList<Matricula> copiaMatricula = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            copiaMatricula.add(new Matricula (matricula));
        }

        return copiaMatricula;

    }




    public int getTamano() {
        return coleccionMatriculas.size();
    }


    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matricula nula.");
        }

        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matricula con ese código.");
        }
        coleccionMatriculas.add(new Matricula (matricula));

    }










/*
    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nulo.");
        }


        return coleccionMatriculas.indexOf(matricula);

    }
*/




    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matricula nula.");
        }



        if(coleccionMatriculas.indexOf(matricula) == -1){
            return null;
        }else{
            Matricula matriculaBuscada = coleccionMatriculas.get(coleccionMatriculas.indexOf(matricula));
            return new Matricula (matriculaBuscada);
        }


        /*
        Alumno alumnoA = coleccionAlumnos.get(coleccionAlumnos.indexOf(alumno));
        return new Alumno (alumnoA);

        if (buscarIndice(matricula) == -1){
            return null;
        }else{
            return matriculaBuscada;
        }

         */

    }


    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matricula nula.");
        }

        if(coleccionMatriculas.indexOf(matricula) == -1){
            throw new IllegalArgumentException("ERROR: No existe la matrícula a borrar");
        }else{
            coleccionMatriculas.remove(matricula);
        }

    }



    public ArrayList<Matricula> get(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un alumno nulo.");
        }

        ArrayList<Matricula> listaMatricula = new ArrayList<>();

        for (Matricula mt : coleccionMatriculas) {
            if (mt != null && mt.getAlumno().equals(alumno)){
                listaMatricula.add(new Matricula(mt));
            };
        }

        return listaMatricula;
    }






    //TODO ESTO NI IDEA DE COMO HACERLO, Spoiler, parece que funciona
    public ArrayList<Matricula> get(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un curso académico nulo.");
        }

        ArrayList<Matricula> listaCursoAcademico = new ArrayList<>();

        for (Matricula matri : coleccionMatriculas){
            if(matri != null && matri.getCursoAcademico().equals(cursoAcademico)){
                listaCursoAcademico.add(new Matricula(matri));
            };
        }

        return listaCursoAcademico;

    }
    //TODO ME queda algo por hacer
    //TODO: Lo de abajo es lo que había antes

/*
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

*/

    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar matrículas de un ciclo formativo nulo.");
        }

        ArrayList<Matricula> listaCiclosFormativos = new ArrayList<>();

        for (Matricula matricula : coleccionMatriculas){
            if(matricula != null){
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()){
                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)){
                        listaCiclosFormativos.add(matricula);
                        break;
                    }
                }
            }
        }
        return listaCiclosFormativos;

    }






}

