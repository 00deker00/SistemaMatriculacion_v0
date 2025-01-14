package org.iesalandalus.programacion.matriculacion.vista;

import com.sun.tools.javac.Main;
import org.iesalandalus.programacion.matriculacion.MainApp;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;



public class Consola {



    //private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Constructor privado para evitar instanciación
    private Consola() {}

    // Método para mostrar el menú con las opciones
    public static void mostrarMenu() {
        System.out.println("Opciones disponibles:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    // Método para elegir una opción del menú
    public static Opcion elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.print("Elige una opción: ");
            ordinalOpcion = Entrada.entero();
        } while (ordinalOpcion < 0 || ordinalOpcion >= Opcion.values().length);



        switch (ordinalOpcion){
            case 0:
                return Opcion.SALIR;
            case 1:
                return Opcion.INSERTAR_ALUMNO;
            case 2:
                return Opcion.BUSCAR_ALUMNO;
            case 3:
                return Opcion.BORRAR_ALUMNO;
            case 4:
                return Opcion.MOSTRAR_ALUMNOS;
            case 5:
                return Opcion.INSERTAR_CICLO_FORMATIVO;
            case 6:
                return Opcion.BUSCAR_CICLO_FORMATIVO;
            case 7:
                return Opcion.BORRAR_CICLO_FORMATIVO;
            case 8:
                return Opcion.MOSTRAR_CICLOS_FORMATIVOS;
            case 9:
                return Opcion.INSERTAR_ASIGNATURA;
            case 10:
                return Opcion.BUSCAR_ASIGNATURA;
            case 11:
                return Opcion.BORRAR_ASIGNATURA;
            case 12:
                return Opcion.MOSTRAR_ASIGNATURAS;
            case 13:
                return Opcion.INSERTAR_MATRICULA;
            case 14:
                return Opcion.BUSCAR_MATRICULA;
            case 15:
                return Opcion.ANULAR_MATRICULA;
            case 16:
                return Opcion.MOSTRAR_MATRICULAS;
            case 17:
                return Opcion.MOSTRAR_MATRICULAS_ALUMNO;
            case 18:
                return Opcion.MOSTRAR_MATRICULAS_CICLO_FORMATIVO;
            case 19:
                return Opcion.MOSTRAR_MATRICULAS_CURSO_ACADEMICO;
            default:
                throw new NullPointerException("ERROR: La opción elegida no puede ser nula");
        }
    }


    public static Alumno leerAlumno() {

        System.out.print("Introduce el nombre del alumno: ");
        String nombre = Entrada.cadena();

        //System.out.print("Introduce el DNI del alumno: ");
        //String dni = Entrada.cadena();
        String dni;
        String DNI = "\\d{8}[A-Za-z]";
        do {
            System.out.print("Introduce el DNI del alumno: ");
            dni = Entrada.cadena();
            if (!dni.matches(DNI)) {
                System.out.println("ERROR: El DNI no es válido. Debe estar compuesto por 8 números seguidos de una letra.");
            }
        } while (!dni.matches(DNI));



        System.out.print("Introduce el correo del alumno: ");
        String correo = Entrada.cadena();

        System.out.print("Introduce el teléfono del alumno: ");
        String telefono = Entrada.cadena();



        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del alumno (dd/MM/yyyy): ");

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }


    public static Alumno getAlumnoPorDni() {
        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();

        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
        }
        if (dni.isBlank()) {
            throw new IllegalArgumentException("ERROR: El DNI no puede estar en blanco.");
        }

        // Creando un alumno con datos ficticios, excepto el DNI
        Alumno alumnoFicticio =  new Alumno("NombreFicticio", dni, "correo@ejemplo.com", "123456789", LocalDate.of(2000, 1, 1));
        return alumnoFicticio;
    }



    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.print(mensaje);
            String fechaStr = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha introducida no tiene un formato válido.");
            }
        }
        return fecha;
    }


    public static Grado leerGrado() {
        // Mostrar los grados disponibles
        Grado[] grados = Grado.values();
        for (int i = 0; i < grados.length; i++) {
            System.out.println(i + ". " + grados[i].imprimir() + " (" + grados[i].toString() + ")");
        }
        int indice=-1;
        boolean opcionValida=false;
        do{
            try {
                System.out.print("Selecciona un grado: ");
                indice = Integer.parseInt(Entrada.cadena());


                if (indice < 0 || indice >= grados.length) {
                    throw new IllegalArgumentException("Índice fuera de rango.");
                }
                opcionValida=true;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Índice fuera de rango. Inténtalo de nuevo.");
            }

        }while(!opcionValida);
        return grados[indice];
    }


    public static CicloFormativo leerCicloFormativo() {
        try {
            System.out.println("Introduce el código del ciclo formativo");
            int codigo;
            do {
                System.out.print("El código tiene que estar compuesto por 4 números.");
                codigo = Entrada.entero();
            }while(codigo < 1000 || codigo > 9999);

            System.out.print("Introduce la familia profesional: ");
            String familiaProfesional = Entrada.cadena();

            Grado grado = leerGrado();

            System.out.print("Introduce el nombre del ciclo formativo: ");
            String nombre = Entrada.cadena();

            System.out.print("Introduce el número de horas del ciclo formativo: ");
            int horas = Entrada.entero();


            return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
        } catch (NullPointerException | IllegalArgumentException e) {
            // Propagamos la excepción para que sea manejada por quien llama al método ¿Lo habré hecho bien?
            throw e;
        }
    }



    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        for (CicloFormativo cicloFormativo : ciclosFormativos.get()) {
            System.out.println(cicloFormativo);
        }
    }


    public static CicloFormativo getCicloFormativoPorCodigo() {
        try {
            System.out.print("Introduce el código del ciclo formativo: ");
            int codigo = Entrada.entero();

            if (codigo <= 0) {
                throw new IllegalArgumentException("ERROR: El código no puede ser menor o igual a cero.");
            }


            return new CicloFormativo(codigo, "FamiliaFicticia", Grado.GDCFGB, "NombreFicticio", 2000);
        } catch (IllegalArgumentException e) {

            throw e;
        }
    }



    public static Curso leerCurso() {
        System.out.println("Cursos disponibles:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso);
        }
        System.out.print("Elige un curso: ");
        int nombreCurso = Entrada.entero();
        return Curso.values()[nombreCurso];
    }


    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Especialidades disponibles:");
        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad);
        }

        EspecialidadProfesorado especialidadSeleccionada = null;
        while (especialidadSeleccionada == null) {
            try {
                System.out.print("Elige una especialidad: ");
                int nombreEspecialidad = Entrada.entero();
                especialidadSeleccionada = EspecialidadProfesorado.values()[nombreEspecialidad];
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Especialidad no válida. Inténtalo de nuevo.");
            }
        }
        return especialidadSeleccionada;
    }



    public static Asignatura leerAsignatura(CiclosFormativos ciclosFormativos) {
        try {
            System.out.print("Introduce el código de la asignatura: ");
            String codigo = Entrada.cadena();
            if (codigo == null || codigo.isBlank()) {
                throw new IllegalArgumentException("ERROR: El código no puede ser nulo o estar en blanco.");
            }

            System.out.print("Introduce el nombre de la asignatura: ");
            String nombre = Entrada.cadena();
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo o estar en blanco.");
            }

            System.out.print("Introduce el número de horas anuales de la asignatura: ");
            int horasAnuales = Entrada.entero();
            //SCANNER.nextLine(); // Limpiar el buffer
            if (horasAnuales <= 0) {
                throw new IllegalArgumentException("ERROR: El número de horas anuales debe ser mayor que cero.");
            }

            Curso curso = leerCurso();

            System.out.print("Introduce el número de horas de desdoble de la asignatura: ");
            int horasDesdoble = Entrada.entero();
            //SCANNER.nextLine(); // Limpiar el buffer
            if (horasDesdoble < 0) {
                throw new IllegalArgumentException("ERROR: El número de horas de desdoble no puede ser negativo.");
            }

            EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();

            CicloFormativo cicloFormativo = getCicloFormativoPorCodigo();

            return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidad, cicloFormativo);
        } catch (NullPointerException | IllegalArgumentException e) {

            throw e;
        }
    }




    public static Asignatura getAsignaturaPorCodigo() {
        try {
            System.out.print("Introduce el código de la asignatura: ");
            String codigo = Entrada.cadena();

            if (codigo == null || codigo.isBlank()) {
                throw new IllegalArgumentException("ERROR: El código no puede ser nulo o estar en blanco.");
            }

            return MainApp.asignaturas.buscar(new Asignatura(codigo, "NombreFicticio", 150, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1234, "FamiliaFicticia", Grado.GDCFGB, "NombreFicticio", 2000)));
            //return new Asignatura(codigo, "NombreFicticio", 150, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1234, "FamiliaFicticia", Grado.GDCFGB, "NombreFicticio", 2000));
        } catch (IllegalArgumentException e) {

            throw e;
        }
    }



    private static void mostrarAsignaturas(Asignaturas asignaturas) {
        for (Asignatura asignatura : asignaturas.get()) {
            System.out.println(asignatura);
        }
    }

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura) {
        for (Asignatura asig : asignaturasMatricula) {
            if (asig != null && asig.equals(asignatura)) {
                return true;
            }
        }
        return false;
    }


    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) {
        try {
            Alumno alumno = getAlumnoPorDni();

            System.out.print("Introduce el curso académico: ");
            String cursoAcademico = Entrada.cadena();
            if (cursoAcademico == null || cursoAcademico.isBlank()) {
                throw new IllegalArgumentException("ERROR: El curso académico no puede ser nulo o estar en blanco.");
            }

            LocalDate fechaMatriculacion = leerFecha("Introduce la fecha de matriculación (dd/MM/yyyy): ");

            System.out.print("Introduce el número de asignaturas para la matrícula: ");
            int numAsignaturas = Entrada.entero();
            if (numAsignaturas <= 0) {
                throw new IllegalArgumentException("ERROR: El número de asignaturas debe ser mayor que cero.");
            }

            Asignatura[] coleccionAsignaturas = new Asignatura[numAsignaturas];
            for (int i = 0; i < numAsignaturas; i++) {
                System.out.println("Introduce los datos de la asignatura " + (i + 1) + ":");
                CiclosFormativos ciclosFormativos = new CiclosFormativos(asignaturas.getCapacidad());  // Crear un objeto CiclosFormativos temporalment

                Asignatura asignatura = getAsignaturaPorCodigo();
                if (!asignaturaYaMatriculada(coleccionAsignaturas, asignatura)) {
                    coleccionAsignaturas[i] = asignatura;
                } else {
                    System.out.println("ERROR: La asignatura ya está matriculada.");
                    i--;
                }
            }

            return new Matricula(1, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas);
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

            throw new RuntimeException("ERROR: No se puede realizar la matrícula: " + e.getMessage());
        }
    }


    // Método para obtener una matrícula por identificador
    public static Matricula getMatriculaPorIdentificador() {
        try {
            System.out.print("Introduce el identificador de la matrícula: ");
            int idMatricula = Entrada.entero();

            if (idMatricula <= 0) {
                throw new IllegalArgumentException("ERROR: El identificador no puede ser menor o igual a cero.");
            }


            return new Matricula(idMatricula, "21-22", LocalDate.now(),
                    new Alumno("NombreFicticio", "12345678A", "correo@ejemplo.com", "123456789", LocalDate.of(2000, 1, 1)),
                    new Asignatura[0]);
        } catch (IllegalArgumentException | OperationNotSupportedException e) {

            throw new RuntimeException("ERROR: No se puede crear la matrícula: " + e.getMessage());
        }
    }


}
