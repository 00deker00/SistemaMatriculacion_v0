package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;


import java.time.LocalDate;

import static org.iesalandalus.programacion.matriculacion.vista.Consola.*;


public class MainApp {



    public static final int CAPACIDAD = 3; // Capacidad inicial
    public static Alumnos alumnos = new Alumnos(CAPACIDAD); // Inicialización de la colección de alumnos
    public static Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    public static CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    public static Matriculas matriculas = new Matriculas(CAPACIDAD);


    public static void main(String[] args) {
        System.out.println("Programa para la gestion del sistema de matriculacion del IES Al-Ándalus");
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

        System.out.println("Hasta luego!!!");
    }

    public static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }


    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void buscarAlumno() {
        System.out.print("Introduce el DNI del alumno a buscar: ");
        String dni = Entrada.cadena(); // Leer el DNI del usuario

        try {
            // Creamos un objeto Alumno con el DNI introducido (aunque no existan los demás datos)
            Alumno alumno = new Alumno("NombreFicticio", dni, "a@gmail.com", "950950950", LocalDate.of(2000, 1, 1));

            // Utilizamos el método buscar de la clase Alumnos
            Alumno encontrado = alumnos.buscar(alumno);

            if (encontrado != null) {
                System.out.println("Alumno encontrado: " + encontrado);
            } else {
                System.out.println("No se encontró un alumno con el DNI proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }
    }


    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void mostrarAlumnos() {
        Alumno[] listaAlumnos = alumnos.get();
        if (listaAlumnos.length == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : listaAlumnos) {
                if (alumno != null) {
                    System.out.println(alumno);
                }
            }
        }
    }


    public static void insertarAsignatura() {
        try {
            // Leer los datos de la asignatura utilizando Consola con el parámetro ciclosFormativos
            Asignatura asignatura = Consola.leerAsignatura(ciclosFormativos);

            // Intentar insertar la asignatura en la colección
            asignaturas.insertar(asignatura);

            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar la asignatura: " + e.getMessage());
        }
    }



    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura encontrada = asignaturas.buscar(asignatura);
            if (encontrada != null) {
                System.out.println(encontrada);
            } else {
                System.out.println("No existe ninguna asignatura con ese código.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void mostrarAsignaturas() {
        Asignatura[] listaAsignaturas = asignaturas.get();
        if (listaAsignaturas.length == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (Asignatura asignatura : listaAsignaturas) {
                if (asignatura != null) {
                    System.out.println(asignatura);
                }
            }
        }
    }


    public static void insertarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(cicloFormativo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar ciclo formativo: " + e.getMessage());
        }
    }


    private static void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(cicloFormativo);
            if (encontrado != null) {
                System.out.println(encontrado);
            } else {
                System.out.println("No existe ningún ciclo formativo con ese código.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            ciclosFormativos.borrar(cicloFormativo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void mostrarCiclosFormativos() {
        CicloFormativo[] listaCiclosFormativos = ciclosFormativos.get();
        if (listaCiclosFormativos.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo cicloFormativo : listaCiclosFormativos) {
                if (cicloFormativo != null) {
                    System.out.println(cicloFormativo);
                }
            }
        }
    }


    private static void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            if (encontrada != null) {
                System.out.println(encontrada);
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static void anularMatricula() {
        System.out.print("Introduce el identificador de la matrícula a anular: ");
        try {
            int idMatricula = Integer.parseInt(Entrada.cadena());
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            if (matricula == null) {
                System.out.println("No se encontró ninguna matrícula con el identificador proporcionado.");
                return;
            }
            matriculas.borrar(matricula);
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al anular matrícula: " + e.getMessage());
        }
    }




    private static void mostrarMatriculas() {
        Matricula[] listaMatriculas = matriculas.get();
        if (listaMatriculas.length == 0) {
            System.out.println("No hay matrículas registradas.");
        } else {
            for (Matricula matricula : listaMatriculas) {
                if (matricula != null) {
                    System.out.println(matricula);
                }
            }
        }
    }


    private static void mostrarMatriculasPorAlumno() {
        try {
            // Obtener el alumno a partir del DNI introducido
            Alumno alumno = Consola.getAlumnoPorDni();

            // Verificar si el alumno existe
            Alumno encontrado = alumnos.buscar(alumno);
            if (encontrado == null) {
                System.out.println("No se encontró ningún alumno con el DNI proporcionado.");
                return;
            }

            // Obtener las matrículas del alumno encontrado
            Matricula[] matriculasAlumno = matriculas.get(encontrado);

            // Verificar si el alumno tiene matrículas
            if (matriculasAlumno == null || matriculasAlumno.length == 0) {
                System.out.println("El alumno no tiene matrículas asociadas.");
                return;
            }

            // Mostrar las matrículas
            System.out.println("Matrículas del alumno con DNI " + encontrado.getDni() + ":");
            for (Matricula matricula : matriculasAlumno) {
                System.out.println(matricula);
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar las matrículas del alumno: " + e.getMessage());
        }
    }



    private static void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            Matricula[] matriculasCiclo = matriculas.get(cicloFormativo);
            if (matriculasCiclo.length == 0) {
                System.out.println("No hay matrículas registradas para este ciclo formativo.");
            } else {
                for (Matricula matricula : matriculasCiclo) {
                    if (matricula != null) {
                        System.out.println(matricula);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void mostrarMatriculasPorCursoAcademico() {
        try {

            System.out.print("Introduce el curso académico (por ejemplo, 2023-2024): ");
            String cursoAcademico = Entrada.cadena().trim();


            Matricula[] matriculasCurso = matriculas.get(cursoAcademico);


            if (matriculasCurso == null || matriculasCurso.length == 0) {
                System.out.println("No se encontraron matrículas para el curso académico proporcionado.");
                return;
            }


            System.out.println("Matrículas del curso académico " + cursoAcademico + ":");
            for (Matricula matricula : matriculasCurso) {
                System.out.println(matricula);
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar las matrículas del curso académico: " + e.getMessage());
        }
    }


}