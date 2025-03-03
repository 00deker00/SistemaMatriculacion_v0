package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {


    SALIR("SALIR") {
        @Override
        public void ejecutar() {
            vista.terminar();
        }
    },
    INSERTAR_ALUMNO("INSERTAR ALUMNO"){
        @Override
        public void ejecutar(){
            vista.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO("BUSCAR ALUMNO"){
        @Override
        public void ejecutar(){
            vista.buscarAlumno();
        }
    },
    BORRAR_ALUMNO("BORRAR ALUMNO"){
        @Override
        public void ejecutar(){
            vista.borrarAlumno();
        }
    },
    MOSTRAR_ALUMNOS("MOSTRAR ALUMNOS"){
        @Override
        public void ejecutar(){
            vista.mostrarAlumnos();
        }
    },
    INSERTAR_CICLO_FORMATIVO("INSERTAR CICLO FORMATIVO"){
        @Override
        public void ejecutar(){
            vista.insertarCicloFormativo();
        }
    },
    BUSCAR_CICLO_FORMATIVO("BUSCAR CICLO FORMATIVO"){
        @Override
        public void ejecutar(){
            vista.buscarCicloFormativo();
        }
    },
    BORRAR_CICLO_FORMATIVO("BORRAR CICLO FORMATIVO"){
        @Override
        public void ejecutar(){
            vista.borrarCicloFormativo();
        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("MOSTRAR CICLOS FORMATIVOS"){
        @Override
        public void ejecutar(){
            vista.mostrarCiclosFormativos();
        }
    },
    INSERTAR_ASIGNATURA("INSERTAR ASIGNATURA"){
        @Override
        public void ejecutar(){
            vista.insertarAsignatura();
        }
    },
    BUSCAR_ASIGNATURA("BUSCAR ASIGNATURA"){
        @Override
        public void ejecutar(){
            vista.buscarAsignatura();
        }
    },
    BORRAR_ASIGNATURA("BORRAR ASIGNATURA"){
        @Override
        public void ejecutar(){
            vista.borrarAsignatura();
        }
    },
    MOSTRAR_ASIGNATURAS("MOSTRAR ASIGNATURAS"){
        @Override
        public void ejecutar(){
            vista.mostrarAsignaturas();
        }
    },
    INSERTAR_MATRICULA("INSERTAR MATRICULA"){
        @Override
        public void ejecutar(){
            vista.insertarMatricula();
        }
    },
    BUSCAR_MATRICULA("BUSCAR MATRICULA"){
        @Override
        public void ejecutar(){
            vista.buscarMatricula();
        }
    },
    ANULAR_MATRICULA("ANULAR MATRICULA"){
        @Override
        public void ejecutar(){
            vista.anularMatricula();
        }
    },
    MOSTRAR_MATRICULAS("MOSTRAR MATRICULAS"){
        @Override
        public void ejecutar(){
            vista.mostrarMatriculas();
        }
    },
    MOSTRAR_MATRICULAS_ALUMNO("MOSTRAR MATRICULAS ALUMNO"){
        @Override
        public void ejecutar(){
            vista.mostrarMatriculasPorAlumno();
        }
    },
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("MOSTRAR MATRICULAS CICLO FORMATIVO"){
        @Override
        public void ejecutar(){
            vista.mostrarMatriculasPorCicloFormativo();
        }
    },
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("MOSTRAR MATRICULAS CURSO ACADEMICO"){
        @Override
        public void ejecutar(){
            vista.mostrarMatriculasPorCursoAcademico();
        }
    };

    private final String mensajeAMostrar;
    //AÑADE EL ATRIBUTO ESTÁTICO DE TIPO VISTA:
    private static Vista vista;

    // Constructor del enumerado
    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    //AÑADIR MÉTODO ABSTRACTO EJECUTAR
    public abstract void ejecutar();

    //MÉTODO SET VISTA QUE DEBE SER LLAMADO DESDE EL CONSTRUCTO DE LA CLASE VISTA
    public static void setVista(Vista vista){
        Opcion.vista= vista;
    };




    //BORRAR esto de abajo?
    /*public String getCadenaAMostrar() {
        return cadenaAMostrar;
    }
*/

    @Override
    public String toString() {
        return String.format("%d .- %s", ordinal(), mensajeAMostrar);
    }



}
