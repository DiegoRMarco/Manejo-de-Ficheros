import model.Coche;
import model.GestorDeCoches;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//████████████████████████████████ MENU ██████████████████████████████████

        //En este apartado apunto a coches. dat para trabajar con el
        File archivo = new File("src/main/java/recursos/coches.dat");
        Scanner scan = new Scanner(System.in);
        ArrayList<Coche> listaCoches = new ArrayList<>();
        int opcion = 0;

        //Inicializo GestorDeCoches que es donde estan las funciones con funcionalidad
        GestorDeCoches gc = new GestorDeCoches();

        //Para empezar llamo a la funcion leerFile que se encargara de leer el archivo
        listaCoches = gc.leerFile(archivo, listaCoches);

        //Un Do While que mostrara el menu que nos dara las instrucciones
        do{
            System.out.println("""
                    ╔═════════════════════════════╗
                    ║      MENU CONCESIONARIO     ║
                    ╠═════════════════════════════╣
                    ║ 0). Exportar en CSV         ║
                    ║─────────────────────────────║
                    ║ 1). Agregar un Coche        ║
                    ║─────────────────────────────║
                    ║ 2). Borrar un Coche por ID  ║
                    ║─────────────────────────────║
                    ║ 3). Buscar un Coche por ID  ║
                    ║─────────────────────────────║
                    ║ 4). Lista de Coches         ║
                    ║─────────────────────────────║
                    ║ 5). Salir y Guardar         ║
                    ╚═════════════════════════════╝
                    """);

            opcion = scan.nextInt();
            switch (opcion) {
                case 0:
                    System.out.println("Exportar a CSV");
                    gc.exportarCSV(listaCoches);
                    break;
                case 1:
                    System.out.println("Agregar Coche...");
                    listaCoches = gc.añadirCoche(listaCoches);
                    break;
                case 2:
                    System.out.println("Borrar Coche por ID...");
                    listaCoches = gc.eliminarCocheporId(listaCoches);
                    break;
                case 3:
                    System.out.println("Consultar Coche por ID...");
                    gc.buscarPorId(listaCoches);
                    break;
                case 4:
                    System.out.println("Lista de Coches...");
                    gc.listaDeCoches(listaCoches);
                    break;
                case 5:
                    System.out.println("Consulta el fichero coches.dat");
                    System.out.println("Fin del Programa");
                    gc.escribirFile(archivo, listaCoches);
                    break;
                default:
                    System.out.println("Opción no válida, por favor inténtelo de nuevo.");
            }
        }while(opcion != 5);
        scan.close();
    }
}
