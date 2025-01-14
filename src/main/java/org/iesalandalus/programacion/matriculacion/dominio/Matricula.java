package org.iesalandalus.programacion.matriculacion.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Matricula {

    //TODO ESTE ES EL BUENO
    //Atributos - constantes
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION=6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
    private static final String ER_CURSO_ACADEMICO="\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA="dd/MM/yyyy";


    //atributos
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    //Atributos del constructor:
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;


    //Constructor
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);

        if(superaMaximoNumeroHorasMatricula(coleccionAsignaturas)){
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (1000 horas).");
        }

        setColeccionAsignaturas(coleccionAsignaturas);


    }



    public Matricula(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        try {
            setColeccionAsignaturas(matricula.getColeccionAsignaturas());
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: El número total de horas de las asignaturas supera el máximo permitido.");
        }
    }



    //METODOS
    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {

        if (idMatricula<=0){
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico== null){
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }
        if (cursoAcademico.isBlank()){
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)){
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion== null){
            throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }

        if (fechaMatriculacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }

//        if (fechaMatriculacion.isBefore(fechaMatriculacion.plusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))){
//            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
//        }
        long diasDeDiferencia = ChronoUnit.DAYS.between(fechaMatriculacion, LocalDate.now());

        if (diasDeDiferencia>MAXIMO_DIAS_ANTERIOR_MATRICULA){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a 15 días.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String fechaFormateada = fechaMatriculacion.format(formatter);
        this.fechaMatriculacion = fechaMatriculacion.parse(fechaFormateada, formatter);
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion==null){
            throw new NullPointerException("ERROR: la fecha de anulación no puede ser nula");
        }
        if (fechaAnulacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }

        if (fechaAnulacion.isBefore(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación."); }

        if (fechaAnulacion.isAfter(fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))){
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }



        long mesesDeDiferencia = ChronoUnit.MONTHS.between(fechaAnulacion, LocalDate.now());
        if(mesesDeDiferencia>=MAXIMO_MESES_ANTERIOR_ANULACION){
            throw new IllegalArgumentException("ERROR: La fecha de anulacion debe ser anterior a 6 meses");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno(){
        return alumno;
    }

    public void setAlumno(Alumno alumno){
        if (alumno == null){
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        this.alumno = alumno;
    }


    public Asignatura[] getColeccionAsignaturas(){
        return coleccionAsignaturas;
    }

    //TODO PUEDE ESTAR MAL
    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }

        if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException("ERROR: El número de asignaturas no puede superar el máximo permitido.");
        }

        this.coleccionAsignaturas = coleccionAsignaturas;
    }



    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula) {
        if (asignaturasMatricula == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }

        // Inicializa una variable para sumar las horas totales
        int totalHoras = 0;

        for (int i = 0; i < asignaturasMatricula.length; i++) {
            if(asignaturasMatricula[i]!=null){
                totalHoras += asignaturasMatricula[i].getHorasAnuales();
            }
        }

        if (totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA ) {
         return true;
        }

        return false;
    }


    //TODO REVISAR
    private String asignaturasMatricula() {
        StringBuilder sb = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            sb.append(asignatura.imprimir()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula matricula)) return false;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String fechaAnulacionFormatted =fechaAnulacion == null ? "": ", fecha anulación="+fechaAnulacion.format(formatter);
        return "idMatricula=" + idMatricula +
                ", curso académico=" + cursoAcademico +
                ", fecha matriculación=" + fechaMatriculacion.format(formatter) +
                 fechaAnulacionFormatted +
                ", alumno=" + alumno +
                ", Asignaturas={ }";



    }


    public String imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return "idMatricula=" + idMatricula +
                ", curso académico=" + cursoAcademico +
                ", fecha matriculación=" + fechaMatriculacion.format(formatter) +
                ", alumno={" + alumno + "}";
    }








}
