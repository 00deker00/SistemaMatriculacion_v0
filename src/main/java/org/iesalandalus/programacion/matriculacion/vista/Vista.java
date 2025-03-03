package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
//import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
//import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
//import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
//import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class Vista {

    //ELIMINAR SEGÚN EL DIAGRAMA V6
    private Controlador controlador;



    public void setControlador(Controlador controlador){
        if (controlador == null){
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
        }

        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do { Consola.mostrarMenu(); //Muestra el menu
            opcion = Consola.elegirOpcion(); //Se elije la opción
            ejecutarOpcion(opcion); //Ejecución con el método de abajo
        }
        while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar(){
        System.out.println("¡Hasta pronto!");
    }



    //ELIMINAR SEGÚN EL DIAGRAMA V6
    private void ejecutarOpcion(Opcion opcion) {
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

    //CAMBIADO A PUBLIC
    public void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            //LO QUE HABIA ANTES
            //alumnos.insertar(alumno);
            controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //CAMBIADO A PUBLIC
    public void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno encontrado = controlador.buscar(alumno);
            if (encontrado != null) {
                System.out.println(encontrado);
            } else {
                System.out.println("No se encontró un alumno con el DNI proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }
    }



    //CAMBIADO A PUBLIC
    public void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            //alumnos.borrar(alumno);
            controlador.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //CAMBIADO A PUBLIC
    public void mostrarAlumnos() {
            ArrayList<Alumno> listaAlumnos = controlador.getAlumnos();
            if (listaAlumnos.size() == 0) { // Comprobación de que la lista no esté vacía
                System.out.println("No hay alumnos registrados.");
            } else {
                //ORDENAR LISTA DE ALUMNOS (SORT) POR NOMBRE
                listaAlumnos.sort(Comparator.comparing(Alumno::getNombre));
                for (Alumno alumno : listaAlumnos) {
                    if (alumno != null) {
                        System.out.println(alumno);
                    }
                }
            }

    }


    public void insertarAsignatura() {
        try {
            // Leer los datos de la asignatura utilizando Consola con el parámetro ciclosFormativos
            //Asignatura asignatura = Consola.leerAsignatura(controlador.buscar(Consola.getCicloFormativoPorCodigo()));

            CicloFormativo falso = Consola.getCicloFormativoPorCodigo();
            CicloFormativo verdadero = controlador.buscar(falso);
            Asignatura asignaturaBuscada = (Consola.leerAsignatura(verdadero));
            controlador.insertar(asignaturaBuscada);

            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar la asignatura: " + e.getMessage());
        }
    }


    //CAMBIADO A PUBLIC
    public void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            //Asignatura encontrada = asignaturas.buscar(asignatura);
            Asignatura encontrada = controlador.buscar(asignatura);
            if (encontrada != null) {
                System.out.println(encontrada);
            } else {
                System.out.println("No existe ninguna asignatura con ese código.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //CAMBIADO A PUBLIC
    public void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            //asignaturas.borrar(asignatura);
            controlador.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //CAMBIADO A PUBLIC
    public void mostrarAsignaturas() {
        //Asignatura[] listaAsignaturas = asignaturas.get();
        ArrayList<Asignatura> listaAsignaturas = controlador.getAsignaturas();
        if (listaAsignaturas.size() == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            listaAsignaturas.sort(Comparator.comparing(Asignatura::getNombre));
            for (Asignatura asignatura : listaAsignaturas) {
                if (asignatura != null) {
                    System.out.println(asignatura);
                }
            }
        }
    }


    public void insertarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            //ciclosFormativos.insertar(cicloFormativo);
            controlador.insertar(cicloFormativo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar ciclo formativo: " + e.getMessage());
        }
    }

    //CAMBIADO A PUBLIC
    public void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            //CicloFormativo encontrado = ciclosFormativos.buscar(cicloFormativo);
            CicloFormativo encontrado = controlador.buscar(cicloFormativo);
            if (encontrado != null) {
                System.out.println(encontrado);
            } else {
                System.out.println("No existe ningún ciclo formativo con ese código.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            //ciclosFormativos.borrar(cicloFormativo);
            controlador.borrar(cicloFormativo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mostrarCiclosFormativos() {
        //CicloFormativo[] listaCiclosFormativos = ciclosFormativos.get();
        ArrayList<CicloFormativo> listaCiclosFormativos = controlador.getCiclosFormativos();
        if (listaCiclosFormativos.size() == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            listaCiclosFormativos.sort(Comparator.comparing(CicloFormativo::getNombre));

            for (CicloFormativo cicloFormativo : listaCiclosFormativos) {
                System.out.println(cicloFormativo);
            }
        }
    }



    //CAMBIADO A PUBLIC
    public void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            //TODO Asignatura[] asignaturasMatricula = Consola.elegirAsignaturasMatricula(controlador.getAsignaturas());
            Matricula matricula = Consola.leerMatricula(alumno, Consola.elegirAsignaturasMatricula(controlador.getAsignaturas()));
            controlador.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar la matrícula: " + e.getMessage());
        }

    }

    //CAMBIADO A PUBLIC
    public void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            //Matricula encontrada = matriculas.buscar(matricula);
            Matricula encontrada = controlador.buscar(matricula);
            if (encontrada != null) {
                System.out.println(encontrada);
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void anularMatricula() {
        System.out.print("Introduce el identificador de la matrícula a anular: ");
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            LocalDate fechaAnulada = Consola.leerFecha("Selecciona la fecha de anulación: Debe ser anterior a la fecha de hoy y debe estar en este formato dd/mm/yyyy");
            matricula.setFechaAnulacion(fechaAnulada);
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al anular matrícula: " + e.getMessage());
        }
    }



    //CAMBIADO A PUBLIC
    public void mostrarMatriculas() {
        ArrayList<Matricula> listaMatriculas = controlador.getMatriculas();
        if (listaMatriculas.size() == 0) {
            System.out.println("No hay matrículas registradas.");
        } else {
            listaMatriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
                    .thenComparing(matricula -> matricula.getAlumno().getNombre()));

            for (Matricula matricula : listaMatriculas) {
                if (matricula != null) {
                    System.out.println(matricula);
                }
            }
        }
    }

    //CAMBIADO A PUBLIC
    public void mostrarMatriculasPorAlumno() {
        try {
            // Obtener el alumno a partir del DNI introducido
            Alumno alumno = Consola.getAlumnoPorDni();

            // Verificar si el alumno existe
            Alumno encontrado = controlador.buscar(alumno);
            if (encontrado == null) {
                System.out.println("No se encontró ningún alumno con el DNI proporcionado.");
                return;
            }

            // Obtener las matrículas del alumno encontrado
            ArrayList<Matricula> matriculasAlumno = controlador.getMatriculas(encontrado);

            // Verificar si el alumno tiene matrículas
            if (matriculasAlumno.size() == 0) {
                System.out.println("El alumno no tiene matrículas asociadas.");

            } else {
                matriculasAlumno.sort(new Comparator<Matricula>() {
                    @Override
                    public int compare(Matricula o1, Matricula o2) {
                        int fechaComparison = o2.getFechaMatriculacion().compareTo(o1.getFechaMatriculacion());
                        if (fechaComparison != 0) {
                            return fechaComparison;
                        }

                        return o1.getAlumno().getNombre().compareToIgnoreCase(o2.getAlumno().getNombre());
                    }
                });

                // Mostrar las matrículas
                System.out.println("Matrículas del alumno con DNI " + encontrado.getDni() + ":");
                for (Matricula matricula : matriculasAlumno) {
                    System.out.println(matricula);
                }

            }
        }catch(Exception e){
            System.out.println("Error al mostrar las matrículas del alumno: " + e.getMessage());
            }
    }

    //CAMBIADO A PUBLIC
    public void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            ArrayList<Matricula> matriculasCiclo = controlador.getMatriculas(cicloFormativo);
            if (matriculasCiclo.size() == 0) {
                System.out.println("No hay matrículas registradas para este ciclo formativo.");
            } else {
                matriculasCiclo.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
                        .thenComparing(matricula -> matricula.getAlumno().getNombre()));

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



    public void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.print("Introduce el curso académico (por ejemplo, 2023-2024): ");
            String cursoAcademico = Entrada.cadena().trim();

            ArrayList<Matricula> matriculasCurso = controlador.getMatriculas(cursoAcademico);

            if (matriculasCurso == null || matriculasCurso.size() == 0) {
                System.out.println("No se encontraron matrículas para el curso académico proporcionado.");
                return;
            }

            // Ordenar las matrículas
            Collections.sort(matriculasCurso, (m1, m2) -> {
                int fechaComparison = m2.getFechaMatriculacion().compareTo(m1.getFechaMatriculacion());
                if (fechaComparison != 0) {
                    return fechaComparison;
                }
                return m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
            });

            System.out.println("Matrículas del curso académico " + cursoAcademico + ":");
            for (Matricula matricula : matriculasCurso) {
                System.out.println(matricula);
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar las matrículas del curso académico: " + e.getMessage());
        }
    }





}






