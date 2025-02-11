package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {

        GDCFGB("GDCFGB"),
        GDCFGM("GDCFGM"),
        GDCFGS("GDCFGS");

        private int numero;
        private String cadenaAMostrar;

        private Grado(String cadenaAMostrar){
            this.cadenaAMostrar= cadenaAMostrar;
        }

        public String imprimir(){
            return numero + ".-" + cadenaAMostrar;
        }

    @Override
    public String toString() {
        return "Grado{" +
                "numero=" + numero +
                ", cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
