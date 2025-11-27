package UI;

import java.util.Scanner;
import Service.GeneralService;

public class MenuPlaylist {
    public static void playlist() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar Playlist -----");
            System.out.println("1. Ver Playlist");
            System.out.println("2. Buscar Playlist");
            System.out.println("3. Crear Playlist");
            System.out.println("4. Eliminar Playlist");
            System.out.println("0. Volver al Menú Principal");
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
                        // verPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // buscarPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // crearPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // eliminarPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        playlist();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}
