package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    private static final String ER_TELEFONO = "[0-9]{9}";
    private static final String ER_CORREO = "[\\w$-&%.]+[@][\\w.-]+[.][a-zA-Z]+";
    private static final String ER_DNI = "([\\d]{8})([a-zA-Z])";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String ER_NIA ="^[a-záéíóúüñ]{4}\\d{3}$"; //"^[a-z]{4}\\d{3}$";
    private static final int MIN_EDAD_ALUMNADO = 16;

    //ATRIBUTOS
    private String nombre;
    private String dni;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String nia;


    //Constructor con parametros:

    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();

    }



    //Constructor copia

    public Alumno(Alumno alumno) {
        if(alumno==null){
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        setNombre(alumno.getNombre());
        setDni(alumno.getDni());
        setCorreo(alumno.getCorreo());
        setTelefono(alumno.getTelefono());
        setFechaNacimiento(alumno.getFechaNacimiento());
        setNia(alumno.getNia());
    }


    //Getter y Setters



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = formateaNombre(nombre);
        setNia();
    }

    public String getNia() {
        return nia;
    }

    private void setNia() {
        if (nombre!= null && dni != null) {
            String generarNia = nombre.substring(0, 4).toLowerCase() + dni.substring(5, 8);
            this.nia = generarNia;
        }
    }

    private void setNia(String nia) {
        if (nia == null) {
            throw new NullPointerException("ERROR: El Nia no puede ser valido.");
        }
        if (nia.isBlank()) {
            throw new IllegalArgumentException("ERROR: El Nia no puede estar vacio.");
        }

        if (!nia.matches(ER_NIA)) {
            throw new IllegalArgumentException("ERROR: El nia no tiene un formato valido.");
        }else{
            this.nia=nia;
        }

    }

    private String formateaNombre(String nombre){
        if (nombre==null)
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");

        String nombreFormateado="";
        String nombreModif = nombre.toLowerCase().trim();
        String[] nombreArray = nombreModif.split("\\s+");

        for (int i = 0; i<nombreArray.length; i++)
            nombreFormateado += nombreArray[i].substring(0,1).toUpperCase() + nombreArray[i].substring(1)+" ";
        return nombreFormateado.trim();
    }


    private boolean comprobarLetraDni(String dni){
        if (dni == null){throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");}
        if (dni.isBlank()){throw new IllegalArgumentException("ERROR: El dni no puede estar vacio.");}

        Pattern patronDNI = Pattern.compile(ER_DNI);
        Matcher m;
        m = patronDNI.matcher(dni);

        if (!m.matches()){throw new IllegalArgumentException("ERROR: El dni no tiene un formato valido");}

        int numeroDni = Integer.parseInt(m.group(1));

        int resultadoDivision = numeroDni % 23;
        String[] tablaLetras = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};

        if (m.group(2).equals(tablaLetras[resultadoDivision])){
            return true;
        }else {
            return false;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null){throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");}
        if (telefono.isBlank()) {throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");}
        if(!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null){throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");}
        if (correo.isBlank())throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        if (!correo.matches(ER_CORREO)) {throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");}
        this.correo = correo;
    }


    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if (dni==null){throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");}
        if (dni.isBlank()){throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");}
        if (!dni.matches(ER_DNI)){throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");}
        if (!comprobarLetraDni(dni)){throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");}
        this.dni=dni;
    }

    private String getIniciales() {
        String[] nombreArray = this.getNombre().split(" ");
        String iniciales = "";

        for (int i = 0; i < nombreArray.length; i++)
            iniciales += nombreArray[i].substring(0, 1);

        return iniciales;
    }


    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento){
        if(fechaNacimiento==null){
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now().minusYears(MIN_EDAD_ALUMNADO))){
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        this.fechaNacimiento=fechaNacimiento;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return "Número de Identificación del Alumnado (NIA)=" + nia + " nombre=" + nombre + " (" + getIniciales() + "), " +
                "DNI=" + dni +
                ", correo=" + correo +
                ", teléfono=" + telefono +
                ", fecha nacimiento=" + fechaNacimiento.format(formatter);
    }

    @Override
    public String toString() {
        return imprimir();
    }
}