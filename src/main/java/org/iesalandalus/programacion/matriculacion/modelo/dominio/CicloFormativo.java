package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;

public class CicloFormativo {


    public static final int MAXIMO_NUMERO_HORAS= 2000;

    //ATRIBUTOS
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;


    public CicloFormativo (int codigo, String familiaProfesional, Grado grado, String nombre, int horas){
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }



    public CicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo==null){
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }
        setCodigo(cicloFormativo.getCodigo());
        setFamiliaProfesional(cicloFormativo.getFamiliaProfesional());
        setGrado(cicloFormativo.getGrado());
        setNombre(cicloFormativo.getNombre());
        setHoras(cicloFormativo.getHoras());
    }



    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        String codigoHechoCadena = String.valueOf(codigo);
        if (codigoHechoCadena.length() != 4 || !codigoHechoCadena.matches("\\d{4}")){
            throw new IllegalArgumentException("ERROR: El código tiene que estar formado por 4 números.");
        }
        this.codigo=codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional==null){
            throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        }
        if (familiaProfesional.isBlank()){
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado==null){
            throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        }
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if(horas<=0 || horas > MAXIMO_NUMERO_HORAS){
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a 2000.");
        }
        this.horas = horas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CicloFormativo that)) return false;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }


    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo +
                ", familia profesional=" + familiaProfesional +
                ", grado=" + grado +
                ", nombre ciclo formativo=" + nombre +
                ", horas=" + horas;
    }


    public String imprimir(){
        return "Código ciclo formativo=" + codigo + ", nombre ciclo formativo=" + nombre;
    }

}
