package org.iesalandalus.programacion.matriculacion.vista;

//import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Consola {



//COMENTADO PORQUE NO APARECE EN EL DIAGRAMA DE CLASES
    //private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                //CAMBIADO POR FORMATO FECHA CLASE ALUMNO
                //fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha introducida no tiene un formato válido.");
            }
        }
        return fecha;
    }

    //CAMBIO V6
//PERMITE AL USUARIO ELEGIR ENTRE GRADO E O GRADO D
    public static TiposGrado leerTiposGrado(){
        TiposGrado tipoDeGradoSeleccionado = null;

        do {
            System.out.println("Selecciona un tipo de grado:");
            //MOSTRAR LAS OPCIONES CON SU ÍNDICE
            for (TiposGrado tipogrado : TiposGrado.values()){
                System.out.println(tipogrado.imprimir());
            }

            int opcion = Entrada.entero();
            if (opcion >= 0 && opcion < TiposGrado.values().length) {
                tipoDeGradoSeleccionado = TiposGrado.values()[opcion];
            } else {
                System.out.println("ERROR: Índice fuera de rango. Inténtalo de nuevo.");
            }

        } while (tipoDeGradoSeleccionado == null);
        return tipoDeGradoSeleccionado;

        /*
        do{

            try{
                System.out.println("Selecciona un tipo de grado:");
                //MOSTRAR LAS OPCIONES CON SU ÍNDICE
                for (TiposGrado tipogrado : TiposGrado.values()){
                    System.out.println(tipogrado.imprimir());
                }

                int opcion = Entrada.entero();
                tipoDeGradoSeleccionado = TiposGrado.values()[opcion];

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Índice fuera de rango. Inténtalo de nuevo.");
            } catch (NullPointerException e) {
                System.out.println("Ha introducido una opción inválida. Introduzca un número entre 0-1. ");
            }
        }while (tipoDeGradoSeleccionado == null);
        */

    }

    //CAMBIO V6
    //PERMITE AL USUARIO ELEGIR ENTRE MODALIDAD PRESENCIAL O SEMIPRESENCIAL
    public static Modalidad leerModalidad() {
        Modalidad modalidadSeleccionada = null;

        do{
            System.out.println("Selecciona una modalidad: ");
            //MOSTRAR LAS OPCIONES CON SU ÍNDICE
            for (Modalidad tipoGrado : Modalidad.values()){
                System.out.println(tipoGrado.imprimir());
            }


            int opcion = Entrada.entero();
            if (opcion >= 0 && opcion < Modalidad.values().length) {
                modalidadSeleccionada = Modalidad.values()[opcion];
            } else {
                System.out.println("ERROR: Índice fuera de rango. Inténtalo de nuevo.");
            }
        } while (modalidadSeleccionada == null);
        return modalidadSeleccionada;



        /*
        do{
            try{
                System.out.println("Selecciona una modalidad: ");
                //MOSTRAR LAS OPCIONES CON SU ÍNDICE
                for (Modalidad tipoGrado : Modalidad.values()){
                    System.out.println(tipoGrado.imprimir());
                }

                int opcion = Entrada.entero();
                modalidadSeleccionada = Modalidad.values()[opcion];

            }catch (IllegalArgumentException e) {
                System.out.println("ERROR: Índice fuera de rango. Inténtalo de nuevo.");
            } catch (NullPointerException e) {
                System.out.println("Ha introducido una opción inválida. Introduzca un número entre 0-1. ");
            }



        }while (modalidadSeleccionada == null);

        return modalidadSeleccionada;
        */
    }


    //CAMBIO V6 EN LEER GRADO:


    public static Grado leerGrado() {
        // Mostrar los grados disponibles
        Grado grado = null;

        TiposGrado tipo = leerTiposGrado();

        if (tipo == TiposGrado.GRADOD){
            System.out.println("Introduzca el nombre del grado:");
            String nombre = Entrada.cadena();
            System.out.println("Introduce el número de años del grado (debe ser 2 o 3)");
            int numAnios = Entrada.entero();
            Modalidad modalidad = leerModalidad();
            grado = new GradoD(nombre, numAnios, modalidad);

        }else if (tipo ==TiposGrado.GRADOE){
            System.out.println("Introduce el nombre del grado");
            String nombre = Entrada.cadena();
            //NOTA AL PROFESOR: ¿ESTO NO ES REDUNDANTE?
            //Es decir, esto podría automatizarse el número de años del grado
            // ya que no hay más opciones.
            System.out.println("Introduce el número de años del grado (solo puede ser 1");
            int numAnios = Entrada.entero();
            System.out.println("Introduce el número de ediciones(Mayor que 0)");
            int numEdiciones = Entrada.entero();
            grado = new GradoE(nombre, numAnios, numEdiciones);

        }

        return grado;





        /*
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


         */
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


    public static void mostrarCiclosFormativos(CicloFormativo[] ciclosFormativos) {
        for (CicloFormativo cicloFormativo : ciclosFormativos) {
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

            //CAMBIO V6
            //AÑADIR GRADO PARA CORREGIR ERRORES DE COMPILACIÓN
            Grado grado = new GradoD("Grado Profesional", 2, Modalidad.SEMIPRESENCIAL);


            return new CicloFormativo(codigo, "FamiliaFicticia", grado, "NombreFicticio", 2000);
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



    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo) {
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
            if (horasAnuales <= 0) {
                throw new IllegalArgumentException("ERROR: El número de horas anuales debe ser mayor que cero.");
            }

            Curso curso = leerCurso();

            System.out.print("Introduce el número de horas de desdoble de la asignatura: ");
            int horasDesdoble = Entrada.entero();
            if (horasDesdoble < 0) {
                throw new IllegalArgumentException("ERROR: El número de horas de desdoble no puede ser negativo.");
            }

            EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();

            //CicloFormativo cicloFormativo = getCicloFormativoPorCodigo();

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
//DESCOMENTADA LA LÍNEA 326 Y COMENTADA LA LÍNEA 325
            //return MainApp.controlador.buscar(new Asignatura(codigo, "NombreFicticio", 150, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1234, "FamiliaFicticia", Grado.GDCFGB, "NombreFicticio", 2000)));

            //CAMBIO V6
            //AÑADIR GRADO PARA CORREGIR ERRORES DE COMPILACIÓN
            //AÑADIR CICLO FORMATIVOO PARA CORREGIR ERRORES DE COMPILACIÓN
            Grado grado = new GradoD("Grado Profesional", 2, Modalidad.SEMIPRESENCIAL);
            CicloFormativo ciclo = new CicloFormativo(2222, "FamiliaFicticia", grado, "DAW", 11);

            return new Asignatura(codigo, "NombreFicticio", 150, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, ciclo);
        } catch (IllegalArgumentException e) {

            throw e;
        }
    }



    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        for (Asignatura asignatura : asignaturas) {
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

//Lee matrícula
    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas)
            throws OperationNotSupportedException {

        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        if (asignaturas == null || asignaturas.size() == 0) {
            throw new NullPointerException("ERROR: Las asignaturas de una matrícula no pueden ser nulas.");
        }

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Matricula matricula;

        System.out.println("Introduzca el Id de la Matrícula.");
        idMatricula = Entrada.entero();

        System.out.println("Introduzca el Curso Académico.");
        System.out.println("El curso academico tiene que tener el formato 24-25");
        cursoAcademico = Entrada.cadena();

        String mensaje = "Introduzca la Fecha de matriculación.";

        System.out.println("La fecha de matriculación como maximo puede ser de 15 días anterior al día actual");
        fechaMatriculacion = leerFecha(mensaje);

        matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas);

        return matricula;
    }

    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas) {
        System.out.print("Introduce el número de asignaturas para la matrícula: ");
        int numAsignaturas = Entrada.entero();
        if (numAsignaturas <= 0) {
            throw new IllegalArgumentException("ERROR: El número de asignaturas debe ser mayor que cero.");
        }

        ArrayList<Asignatura> asignaturasElegidas = new ArrayList<>();
        System.out.println("Asignaturas disponibles:");
        for (Asignatura asignatura : asignaturas){
            System.out.println(asignatura);
        }
        System.out.println("Elige el código una o varias asignatura:");
        int contadorAsignaturas = 0;
         do{
             Asignatura asignaturaFalsa = Consola.getAsignaturaPorCodigo();
             for (Asignatura asignatura : asignaturas){
                 if (asignaturaFalsa.equals(asignatura)){
                     asignaturasElegidas.add(asignatura) ;
                     contadorAsignaturas++;
                 }
             }
         }while(contadorAsignaturas<numAsignaturas);


            return asignaturasElegidas;


//        for (int i = 0; i < numAsignaturas; i++) {
//            System.out.println("Introduce el código de la asignatura " + (i + 1) + ":");
//            String codigo = Entrada.cadena();
//
//            boolean encontrada = false;
//            for (Asignatura asignatura : asignaturas) {
//                if (asignatura.getCodigo().equals(codigo)) {
//                    if (!asignaturaYaMatriculada(coleccionAsignaturas, asignatura)) {
//                        coleccionAsignaturas[i] = asignatura;
//                        encontrada = true;
//                        break;
//                    } else {
//                        System.out.println("ERROR: La asignatura ya está matriculada.");
//                    }
//                }
//            }
//            if (!encontrada) {
//                System.out.println("ERROR: La asignatura con código " + codigo + " no se encuentra disponible.");
//                i--;
//            }
//        }
//        return coleccionAsignaturas;
    }


    // Método para obtener una matrícula por identificador
    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.now();
        Alumno alumno = new Alumno("ficticio apellidoficticio", "12345678Z", "ficticio@ficticio.es", "444444444",
                LocalDate.of(2000, 8, 21));

        System.out.println("Introduzca el ID de la Matrícula(ID del ciclo formativo).");
        idMatricula = Entrada.entero();

        Matricula matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, new Asignaturas().get());

        return matricula;
    }


}
