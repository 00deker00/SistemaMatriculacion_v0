package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.*;



public class MainApp {

    //TO 5 COMENTADA ESTA LÍNEA1
    //public static Controlador controlador;

    public static void main(String[] args) {
        System.out.println("Programa para la gestion del sistema de matriculacion del IES Al-Ándalus");

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, vista); //LLAMADA CONTROLADOR DESDE AQUI ANTES controlador=new Controlador (modelo, vista);
        controlador.comenzar();

        //System.out.println("Hasta luego!!!");
    }
}