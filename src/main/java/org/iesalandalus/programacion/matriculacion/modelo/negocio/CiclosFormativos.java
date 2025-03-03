package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class CiclosFormativos {


    private ArrayList<CicloFormativo> coleccionCiclosFormativos;


    public CiclosFormativos() {

        this.coleccionCiclosFormativos = new ArrayList<CicloFormativo>();
    }


    public ArrayList<CicloFormativo> get() {

        return copiaProfundaCiclosFormativos();
    }


    private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
        ArrayList<CicloFormativo> copiaCicloFormativo = new ArrayList<>();
        for (CicloFormativo ciclo : coleccionCiclosFormativos){
            copiaCicloFormativo.add((new CicloFormativo(ciclo)));
        }
        return copiaCicloFormativo;
    }




    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }


    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }

        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
    }


    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }

        if (coleccionCiclosFormativos.indexOf(cicloFormativo) == -1){
            return null;
        }else{
            CicloFormativo cicloformativoBuscado = coleccionCiclosFormativos.get(coleccionCiclosFormativos.indexOf(cicloFormativo));
            return new CicloFormativo(cicloformativoBuscado);
        }


    }




    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }

        if(coleccionCiclosFormativos.indexOf(cicloFormativo) == -1){
            throw new IllegalArgumentException("ERROR: No existe el ciclo formativo a borrar");
        }else{
            coleccionCiclosFormativos.remove(cicloFormativo);
        }

    }


}
