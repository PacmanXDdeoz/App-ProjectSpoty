package UI;

import java.util.Scanner;
import Service.GeneralService;
import UI.MenuAlbumes;
import UI.MenuArtistas;
import UI.MenuCancion;
import UI.MenuPlaylist;

public class MenuSpotify {
    public static void spotify() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Menu Spotify -----");
            System.out.println("1. Buscar Artista");
            System.out.println("2. Buscar Álbum");
            System.out.println("3. Buscar Canción");
            System.out.println("4. Buscar Playlist");
            System.out.println("5. Cerrar Sesión");
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
                        MenuArtistas.artistas();
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        MenuAlbumes.albumes();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        MenuCancion.cancion();
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        MenuPlaylist.playlist();
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        System.out.println("¡Hasta pronto!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción inválida");
                        spotify();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}