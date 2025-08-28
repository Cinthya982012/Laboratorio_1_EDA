import java.util.stream.Collectors;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 LABORATORIO 1: INTRODUCCIÓN A JAVA Y ALGORITMOS SOBRE ARREGLOS
 UNIVERSIDAD DIEGO PORTALES
 PROFESOR: CRISTIÁN LLULL
 INTEGRANTES:
 -CINTHYA FUENTEALBA BRAVO
 -IGNACIA REYES OJEDA
 */
//
//================================================================================
//================================================================================
//================================================================================

//CLASE ANALIZADOR DE NOTAS
class AnalizadorDeNotas {

    //================================================================================
    //ATRIBUTOS CLASE ANALIZADOR DE NOTAS
    private double[][] notas; //Matriz bidimensional de las calificaciones[i][j](i Estudiantes /j Evaluaciones)
    private String[] evaluaciones; //Arreglo con los nombres de las evaluaciones
    private int[] rut; //Arreglo con los RUT de los estudiantes
    private int cantEstudiantes; //Número total de estudiantes (filas)
    private int cantEvaluaciones;//Número total de evaluaciones (columnas)

    //================================================================================
    //CONSTRUCTOR 1
    public AnalizadorDeNotas(int cantEstudiantes, int cantEvaluaciones) {
        this.cantEstudiantes = cantEstudiantes;
        this.cantEvaluaciones = cantEvaluaciones;

        this.notas = new double[cantEstudiantes][cantEvaluaciones]; //Creación de la matriz (tamaño estudiantes X evaluaciones)
        this.rut = new int[cantEstudiantes]; //Creación de arreglo con rut
        this.evaluaciones = new String[cantEvaluaciones]; //Creación de arreglo con los nombres de las evaluaciones

        /**Genera RUT unicos y simples*/
        for (int i = 0; i < cantEstudiantes; i++) {
            rut[i] = 100 + i;
        }

        /**Genera nombres de las evaluaciones*/
        //String nombreEvaluaciones;
        for (int j = 0; j < cantEvaluaciones; j++) {
            this.evaluaciones[j] = "E_" + (j + 1);
            //nombreEvaluaciones = evaluaciones[j];
            //System.out.println(nombreEvaluaciones);
        }

        /**Rellena de manera aleatoria la matriz con notas entre 1 y 7*/
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < cantEstudiantes; i++) {
            for (int j = 0; j < cantEvaluaciones; j++) {
                this.notas[i][j] = 1.0 + rand.nextDouble() * 6.0;
            }
        }
    }

    //public boolean validarRut(int rut) {
    //    /**Valida un rut */
    //    for (int i = 0; i < cantEstudiantes; i++) {
    //        if(this.rut[i] == rut) {
    //            return true;
    //        }
    //        return false;
    //    }
    //}

    //public void guardarRut(int index, int rutNuevo) {
    //    if(validarRut(rutNuevo)==true && index < cantEstudiantes && index ) {
    //        for (int i = 0; i < cantEstudiantes; i++) {
    //            rut[i] = 1000 + i
    //            this.rut[i] = rut;
    //            rut.add(int rut (i));
    //        }
    //    }
    //}

    //================================================================================
    //CONSTRUCTOR 2
    public AnalizadorDeNotas(int cantEstudiantes, int cantEvaluaciones, String[] evaluaciones) {
        this.cantEstudiantes = cantEstudiantes;
        this.cantEvaluaciones = cantEvaluaciones;

        /**Se copian los nombres de las evaluaciones*/
        this.evaluaciones = new String[cantEvaluaciones];
        for (int j = 0; j < cantEstudiantes; j++) {
            this.evaluaciones[j] = evaluaciones[j];
        }

        /**Se copian los RUT de los estudiantes*/
        this.rut = new int[cantEstudiantes];
        for (int i = 0; i < cantEstudiantes; i++) {
            this.rut[i] = rut[i];
        }

        /**Se copia la matiz de las calificaciones*/
        this.notas = new double[cantEstudiantes][cantEvaluaciones];
        for (int i = 0; i < cantEstudiantes; i++) {
            for (int j = 0; j < cantEvaluaciones; j++) {
                this.notas[i][j] = notas[i][j];
            }
        }
    }

    //================================================================================
    //SETTERS Y GETTERS
    public double[][] getNotas() {
        return notas;
    }

    public String[] getEvaluaciones() {
        return evaluaciones;
    }

    public int[] getRut() {
        return rut;
    }

    public int getCantEstudiantes() {
        return cantEstudiantes;
    }

    public int getCantEvaluaciones() {
        return cantEvaluaciones;
    }


    public void setNotas(double[][] notas) {
        this.notas = notas;
    }

    public void setEvaluaciones(String[] evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void setRut(int[] rut) {
        this.rut = rut;
    }

    public void setCantEstudiantes(int cantEstudiantes) {
        this.cantEstudiantes = cantEstudiantes;
    }

    public void setCantEvaluaciones(int cantEvaluaciones) {
        this.cantEvaluaciones = cantEvaluaciones;
    }

    //================================================================================
    //MÉTODOS CLASE ANALIZADOR DE NOTAS

    //Metodo que calcula el promedio del estudiante según su rut
    public double calcularPromedioEstudiante(int rut) {
        /**Buscar el indice del rut que corresponda que se esta buscando*/
        int index = -1;
        for (int i = 0; i < cantEstudiantes; i++) {
            if (this.rut[i] == rut) {
                index = i;
                break;
            }
        }
        /**Si no se encuentra el RUT buscado se devuelve -1*/
        if (index == -1) {
            System.out.println("El estudiante con RUT:" + rut + " no existe.");
            return -1;
        }

        /**Sumar todas las notas del estudiante buscado por su RUT*/
        double suma = 0;
        for (int j = 0; j < cantEstudiantes; j++) {
            suma += this.notas[index][j];
        }

        /**Se calcula el promedio del estudiante y se retorna el promedio*/
        return suma / cantEvaluaciones;
    }

    //Metodo que calcula el promedio de la evaluacion buscada segun su nombre
    public double calcularPromedioEvaluacion(int index) {
        /**Validar la evaluación*/
        if (index < 0 || index >= cantEvaluaciones) {
            System.out.println("La evaluación:" + index + " es un indice invalido.");
            return -1;
        }

        /**Se recorren tdos los estudiantes que rindieron la evaluación*/
        double suma = 0;
        for (int i = 0; i < cantEstudiantes; i++) {
            suma += this.notas[i][index];
        }

        /**Se calcula el promedio de la evualuación buscada y se retorna dicho valor*/
        return suma / cantEstudiantes;
    }

    //Metodo que calcula la varianza de una evaluación
    public double calcularVarianzaEvaluacion(int index) {
        /**Validar la evaluación*/
        if (index < 0 || index >= cantEvaluaciones) {
            System.out.println("La evaluación:" + index + " es un indice invalido.");
            return -1;
        }

        /**Corresponde al promedio de la evaluación*/
        double promedio = calcularPromedioEvaluacion(index);

        /**Suma de las notas del estuante menos el promedio, elevado a 2 (parte de a ecuación de varianza)*/
        double suma = 0;
        for (int i = 0; i < cantEstudiantes; i++) {
            double resta = notas[i][index] - promedio;
            suma += resta * resta;
        }

        /**Se divide por la cantidad total de estudiantes y se retorna dicho valor*/
        return suma / cantEstudiantes;
    }

    //Metodo que calcula el promedio de notas de cada estaudiante
    public double[] calcularPromediosEstudiantes() {
        /**Arreglo de promedios, uno por estudiante*/
        double[] promedios = new double[cantEstudiantes];

        /**Para cada uno de los estudiantes*/
        for (int i = 0; i < cantEstudiantes; i++) {
            double suma = 0;

            /**Se suman todas las notas del estudiante i*/
            for (int j = 0; j < cantEstudiantes; j++) {
                suma += this.notas[i][j];
            }

            /**Se calcula el promedio y se guarda*/
            promedios[i] = suma / cantEvaluaciones;
        }
        /**Se retorna el valor de los promedios*/
        return promedios;
    }

    //Metodo que calcula la varianza de notas de cada estudiante
    public double[] calcularVarianzaEstudiantes() {
        /**Arreglo de varianza de notas, uno por estudiante*/
        double[] varianzas = new double[cantEstudiantes];

        /**Para cada uno de los estudiantes*/
        for (int i = 0; i < cantEstudiantes; i++) {
            double suma = 0;

            /**Se suman todas las notas del estudiante i*/
            for (int j = 0; j < cantEvaluaciones; j++) {
                suma += this.notas[i][j];
            }

            /**Se calcula el promedio del estudiante i*/
            double promedio = suma / cantEvaluaciones;

            /**Suma de las diferencias del las notas del estudiante menos el promedio de la evaluación, elevado a 2 (parte de a ecuación de varianza)*/
            double sumadeRestas = 0;
            for (int j = 0; j < cantEvaluaciones; j++) {
                double resta = notas[i][j] - promedio;
                sumadeRestas += resta * resta;
            }

            /**Calculo de la varianza de las notas del estudiante i (suma de las diferencias dividido en la cantidad de estudiantes)*/
            varianzas[i] = sumadeRestas / cantEstudiantes;
        }

        /**Se retorna el valor de las varianzas*/
        return varianzas;
    }

    //Metodo que calcula el promedio de las evaluaciones especificas para cada estudiante
    public double[] calcularPromediosEvaluaciones(String[] evaluaciones) {
        /**Arreglo de promedios de notas, uno por estudiante*/
        double[] promedios = new double[cantEstudiantes];

        /**Encontrar el nombre de las evaluaciones que se necesitan*/
        int[] nombreEvaluaciones = new int[evaluaciones.length];
        for (int i = 0; i < cantEstudiantes; i++) {
            for (int j = 0; j < cantEvaluaciones; j++) {
                for (int k = 0; k < evaluaciones.length; k++) {
                    nombreEvaluaciones[k] = -1; // Inicializar en -1

                    if (this.evaluaciones[j].equals(nombreEvaluaciones[k])) {
                        nombreEvaluaciones[k] = j;
                        break;
                    }
                    if (nombreEvaluaciones[k] == -1) {
                        System.out.println("Evaluación " + evaluaciones[k] + " no existe.");
                    }
                    /**C*/

                    double suma = 0;
                    int contador = 0;
                    for (int indice : nombreEvaluaciones) {
                        if (indice != -1) { //Si, solo si la evaluacion es escontrada
                            suma += this.notas[i][indice];
                            contador++;
                        }
                    }
                    promedios[i] = (contador > 0) ? suma / contador : 0.0;
                }

            }
        }
        return promedios;
    }

    //Metodo para encontrar la calificacion maxima para la evaluacion seleccionada
    public String encontrarMaximos(int index) {
        /**Validar el index*/
        if (index < 0 || index >= cantEstudiantes) {
            System.out.println("Evaluación" +index + "no encontrada");
            return null;
        }

        /**Se inicia desde el primer estudiante*/
        double notaMax = notas[0][index];
        int rutMax = rut[0];

        /**Se recorre el resto de los estudiantes*/
        for (int i = 1; i < cantEstudiantes; i++) {
            if (notaMax < this.notas[i][index]) {
                notaMax = this.notas[i][index];
                rutMax = rut[i];
            }
        }
        /**Se retorna el rut como String*/
        return String.valueOf(rutMax);
    }
}
public class Main {
    public static void main(String[] args) {
        AnalizadorDeNotas analizador1 = new AnalizadorDeNotas(100,3);
        String[] notas = new String[5];

        double promedio =analizador1.calcularPromedioEstudiante(1002);
        System.out.println("El promedio del estudiante 1002 es: " + promedio);

        double promedio1 = analizador1.calcularPromedioEvaluacion(1002);
        System.out.println("Promedio de la evaluación 1002 es: " + promedio1);

        double varianzaEvaluacion1 = analizador1.calcularVarianzaEvaluacion(1002);
        System.out.println("Varianza de la evaluacion 1002 es: " + varianzaEvaluacion1);

        String[] seleccion = {"E_1"};
        double [] promedios = analizador1.calcularPromediosEvaluaciones(seleccion);
        for (int i = 0; i < promedios.length; i++) {
            System.out.println("Promedio del estudiante: " + promedios[i]);
        }

        String mejor = analizador1.encontrarMaximos(1);
        System.out.println("El mejor de la evaluacion 1 es: " + mejor);

    }
}
