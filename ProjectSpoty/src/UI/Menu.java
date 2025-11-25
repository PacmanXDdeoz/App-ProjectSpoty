package UI;

import java.util.Scanner;
import Service.GeneralService;
import UI.MenuSpotify;

public class Menu {

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("    SPOTIFY - BIENVENIDO");
        System.out.println("=============================");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
        System.out.println("-------------------------");

        int opcion = sc.nextInt();
        sc.nextLine();
        while (true) {
            try {

                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // iniciarSesion();
                        GeneralService.cleanScreen();
                        MenuSpotify.spotify();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // registrarse();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        System.out.println("¡Adiós!");
                        System.exit(0);
                    default:
                        System.out.println("Opción inválida");
                        mostrarMenuPrincipal();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}