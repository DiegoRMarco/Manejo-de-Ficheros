package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDeCoches {

//████████████████████████████████ LEER ARCHIVO AL COMENZAR EL PROGRAMA ██████████████████████████████████
    public ArrayList<Coche> leerFile (File archivo, ArrayList<Coche> listaCoches ) {
        ObjectInputStream leerObjetos = null;
        //De primeras se comprueba si el FILE al que apuntamos es un archivo y si existe
        //Si es asi se lee el objeto y se almacena en el ArrayList listaCoches
        if (archivo.isFile() && archivo.exists()){
            try {
                leerObjetos = new ObjectInputStream(new FileInputStream(archivo));
                listaCoches = (ArrayList<Coche>) leerObjetos.readObject();
        //Tambien se aprobecha para revisar el valor final donde se quedo el ultimo
        //Valor del ID
                int maxId = 0;
                for(Coche coche : listaCoches){
                    if(coche.getId() > 0){
                        maxId = coche.getId();
                    }
                }
                Coche.setContadorId(maxId + 1);

                System.out.println("Datos Leidos.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error ,no se han leido los datos o estan vacios");
            }
        }else{
            System.out.println("Archivo no encontrado");
        }
        return listaCoches;
    }
//████████████████████████████████ ESCRIBIR ARCHIVO AL SALIR DEL PROGRAMA ██████████████████████████████████
    public void escribirFile (File archivo, ArrayList<Coche> listaCoches) {
        ObjectOutputStream escribirObjetos = null;
    //Esta parte simplemente escribe en el File los Coches de la listaCoches
        if (archivo.isFile() && archivo.exists()){
            try {
                escribirObjetos = new ObjectOutputStream(new FileOutputStream(archivo));
                escribirObjetos.writeObject(listaCoches);

            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }finally{
                try {
                    escribirObjetos.close();
                } catch (IOException e) {
                    System.out.println("Error" + e.getMessage());
                }
            }
        }else{
            System.out.println("Archivo no encontrado");
        }
    }
//████████████████████████████████ AÑADIR UN NUEVO COCHE ██████████████████████████████████
    public ArrayList<Coche> añadirCoche (ArrayList<Coche> listaCoches){
        //Sencillamente pedimos que introduzcan los datos necesarios
        //Sin incluir ni la MATRIUCULA ni el ID pues esto lo hara el porgrma de manera automatica
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce la marca ...");
        String marca = scan.nextLine();
        System.out.println("Introduce el modelo ...");
        String modelo = scan.nextLine();
        System.out.println("Introduce el color ...");
        String color = scan.nextLine();
        Coche coche = new Coche(marca,modelo,color);
        //Para evitar que la MATRICULA sea una que ya existe lo comprobamos aqui,
        //En caso de que el progrma haya creado dos MATRICULAS iguales llamaremos
        //de nuevo al metodo que las crea y le pondremos una nueva.
        boolean matriculaDuplicada = false;
        do{
            matriculaDuplicada = false;
            for (Coche co : listaCoches){
                if (co.getMatricula().equals(coche.getMatricula())){
                    coche.setMatricula(coche.generadorMatricula());
                    matriculaDuplicada = true;
                    break;
                }
            }
        }while (matriculaDuplicada);

        listaCoches.add(coche);

    return listaCoches;
    }
//████████████████████████████████ ELIMINAR UN COCHE ██████████████████████████████████
    public ArrayList<Coche> eliminarCocheporId (ArrayList<Coche> listaCoches){
        //Introcucimos el ID del coche que queremos eliminar, si dicho id no esta en
        //la lista, nos avisara.
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el Id ...");
        int id = scan.nextInt();

        for (int i = 0 ; i < listaCoches.size() ; i++){
            Coche coche = listaCoches.get(i);
            if(coche.getId() == id){
                listaCoches.remove(coche);
                System.out.println("Coche "+ coche.getMarca() + coche.getModelo() +" con matricula: "+ coche.getMatricula() + " ha sido eliminado");
            }
            }
        return listaCoches;
    }
//████████████████████████████████ BUSCAR UN COCHE ██████████████████████████████████
    public void buscarPorId (ArrayList<Coche> listaCoches){
        //Introducimos el ID del coche que queremos buscar y mediante un for nos recorrera
        //el array de coches y nos devolvera el coche con el mismo id que pedimos.
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el Id ...");
        int id = scan.nextInt();
        for (Coche co : listaCoches){
            if(co.getId() == id){
                System.out.println("---------------------------------------------------------");
                System.out.println(co.toString());
                System.out.println("---------------------------------------------------------");
            }
        }
    }
//████████████████████████████████ VER LA LISTA DE COCHES ██████████████████████████████████
    public void listaDeCoches (ArrayList<Coche> listaCoches){
        //Sencillamente recorremos el array que contiene los coches y nos los muestra por pantalla
        for(Coche coche : listaCoches){
            System.out.println(coche.toString());
            System.out.println("_________________________________");
        }

    }
//████████████████████████████████ EXPORTAR COMO ARCHIVO CSV ██████████████████████████████████
    public void exportarCSV (ArrayList<Coche> listaCoches) {
        File archivoCSV = new File("src/main/java/recursos/concesionario.csv");
        PrintWriter escribirObjetos = null;
        //Escribimos mediante PrintWriter --> FileWritter los valores del objeto Coche
        //separados por ; para que lo pueda leer Excel(CSV) tambien y previo a esto escribimos
        //una primera columna con los nombres de las tablas.
        //Por ultimo cerramos el flujo y aseguramos que guarda el contenido.
        if (archivoCSV.isFile() && archivoCSV.exists()){
            try {
                escribirObjetos = new PrintWriter(new FileWriter(archivoCSV));
                escribirObjetos.println("Id;Matricula;Marca;Modelo;Color");
                for (Coche coche : listaCoches){
                    escribirObjetos.println(coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" +coche.getModelo() + ";" + coche.getColor());
                }
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }finally{
                if (escribirObjetos != null){
                escribirObjetos.close();}
            }
        }else{
            System.out.println("Archivo no encontrado");
        }
    }

}
