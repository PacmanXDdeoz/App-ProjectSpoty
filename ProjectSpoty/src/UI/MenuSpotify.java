package UI;

import java.util.Scanner;
import Service.GeneralService;
import UI.MenuAlbumes;
import UI.MenuArtistas;
import UI.MenuCancion;
import UI.MenuPlaylist;
import Model.Users;

public class MenuSpotify {
    public static void spotify(Users currentUser) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
            System.out.println("░░░░░░░░░░ Menu Spotify " + currentUser.getName() + " ░░░░░░░░░░");
            System.out.println("        ⓵. Buscar Artista");
            System.out.println("        ⓶. Buscar Álbum");
            System.out.println("        ⓷. Buscar Canción");
            System.out.println("        ⓸. Buscar Playlist");
            System.out.println("        ⓹. Cerrar Sesión");
            System.out.println("-----------------------------");
            System.out.println("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            sc.nextLine();
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
                        MenuPlaylist.playlist(currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        GeneralService.cleanScreen();
                        System.out.println("¡Hasta pronto!");
                        return;
                    default:
                        GeneralService.cleanScreen();
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}