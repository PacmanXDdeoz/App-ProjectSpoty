package UI;

import java.util.Scanner;
import Service.GeneralService;
import Repository.ConsultaCanciones;

public class MenuCancion {
    public static void cancion() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar Canciones -----");
            System.out.println("1. Ver Canciones");
            System.out.println("2. Buscar Canciones");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            System.out.println("-------------------------");
            opcion = sc.nextInt();
            sc.nextLine();
            try {
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        ConsultaCanciones.listarTodasCanciones(null);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarCanciones();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        Menu.mostrarMenuPrincipal();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        cancion();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    public static void buscarCanciones() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar -----");
            System.out.println("1. Buscar por su id de canción");
            System.out.println("2. Regresar");
            System.out.print("Elige una opción: ");
            System.out.println("-------------------------");
            opcion = sc.nextInt();
            sc.nextLine();
            try{
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        ConsultaCanciones.consultaCancion(null, opcion);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        cancion();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        buscarCanciones();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}
