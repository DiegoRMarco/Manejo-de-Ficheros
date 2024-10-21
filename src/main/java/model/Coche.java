package model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Coche implements Serializable {
    //No he usado lomBok para recordar algunos conceptos
    //Creamos un UID con un numero a nuestra eleccion, yo use el 676
    //De esta manera podemos serializar el objeto
    private static final long codigoUID =  676L;
    private static int contadorId = 0;
    private int id = 0;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    //Al crear un constructor solo le pondremos marca, modelo y color pues la matricula
    //y el ID seran automaticos, de igualmanera en el constructor vacio, aun que no, le
    //pasemos los valores siguen estando tanto el id como la matricula.
    public Coche(String marca, String modelo, String color) {
        this.id = contadorId++;
        this.matricula = generadorMatricula();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
    public Coche() {
        this.id = contadorId++;
        this.matricula = generadorMatricula();
    }

    //Para generar la matricula use Random al cual le puedes pedir que te genere un numero
    //aleatorio, en el caso de numero matricula siempre sera de 1000 a 9999 y en el caso de letraAleatoria
    //de 0 a 20, he creado un array con letras para exluir Ñ y las letras vocales.
    //Finalmente mediante retornamos la mezcla como String de los numeros y letras.
    public String generadorMatricula() {
        Random aleatorio = new Random();
        int numeroMatricula = aleatorio.nextInt(9999)+1000;
        StringBuilder trioDeLetras = new StringBuilder();

        char[] letrasSinVocales = new char[]{
                'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'
        };
        for(int i = 0 ; i < 3 ; i++){
            int letraAleatoria = aleatorio.nextInt(20);
            trioDeLetras.append(letrasSinVocales[letraAleatoria]);
        }
        return numeroMatricula +"-"+ trioDeLetras;
    }
    //Contador es igual a MaxID que es el que verifica cual es el ultimo numero usado
    //en el id.
    public static void setContadorId(int maxId){
        contadorId=maxId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id:" + id +
                " Modelo: " + modelo +" "+ marca +" "+ color +
                " Con matricula " + matricula + '}';
    }
}
