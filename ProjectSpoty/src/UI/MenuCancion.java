package UI;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import Service.GeneralService;
import Repository.ConsultaCanciones;
import Repository.BusquedaCanciones;
import Repository.ConsultaPlaylists;
import Model.Users;

public class MenuCancion {
    public static void cancion(Users currentUser) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("░░░░░░░░░░ Menu Canciones ░░░░░░░░░░");
            System.out.println("        ⓵. Ver Canciones");
            System.out.println("        ⓶. Buscar Canciones");
            System.out.println("        ⓷. Volver al Menú Principal");
            System.out.println("-----------------------------");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verCanciones(sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarCanciones(sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 0:
                        GeneralService.cleanScreen();
                        return;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    public static void verCanciones(Scanner sc, Users currentUser) {
        var todas = ConsultaCanciones.listarTodasCanciones(null);
        mostrarCanciones(todas, sc, currentUser);
    }

    public static void buscarCanciones(Scanner sc, Users currentUser) {
        int opcion = 0;
        do {
            System.out.println("░░░░░░░░░░ Buscar Canciones ░░░░░░░░░░");
            System.out.println("        ⓵. Buscar por nombre");
            System.out.println("        ⓶. Buscar por artista");
            System.out.println("        ⓷. Buscar por género");
            System.out.println("        ⓸. Buscar por álbum");
            System.out.println("        ⓹. Buscar por lenguaje");
            System.out.println("        ⓺. Volver al Menú Principal");
            System.out.println("-------------------------");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            sc.nextLine();
            try {
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre de la canción a buscar: ");
                        String nombre = sc.nextLine();
                        var resultados = BusquedaCanciones.buscarxNombre(null, nombre);
                        mostrarCanciones(resultados, sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre del artista a buscar: ");
                        String artista = sc.nextLine();
                        var resultados2 = BusquedaCanciones.buscarxArtista(null, artista);
                        mostrarCanciones(resultados2, sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el género a buscar: ");
                        String genero = sc.nextLine();
                        var resultados3 = BusquedaCanciones.buscarxGenero(null, genero);
                        mostrarCanciones(resultados3, sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre del álbum a buscar: ");
                        String album = sc.nextLine();
                        var resultados4 = BusquedaCanciones.buscarxAlbum(null, album);
                        mostrarCanciones(resultados4, sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el lenguaje a buscar: ");
                        String lenguaje = sc.nextLine();
                        var resultados5 = BusquedaCanciones.buscarxLenguaje(null, lenguaje);
                        mostrarCanciones(resultados5, sc, currentUser);
                        GeneralService.cleanScreen();
                        break;
                    case 0:
                        GeneralService.cleanScreen();
                        return;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    private static void mostrarCanciones(List<Map<String, Object>> resultados, Scanner sc, Users currentUser) {
        if (resultados != null && !resultados.isEmpty()) {
            for (var cancion : resultados) {
                Object songIdObj = cancion.get("song_id");
                int songId = -1;
                try {
                    songId = Integer.parseInt(String.valueOf(songIdObj));
                } catch (Exception e) {
                    // leave as -1 if parsing fails
                }

                System.out.println("ID: " + songId);
                System.out.println("Nombre: " + cancion.get("song_name"));
                System.out.println("Artista: " + cancion.get("name_artist"));
                System.out.println("Género: " + cancion.get("gender"));
                System.out.println("Álbum: " + cancion.get("album_name"));
                System.out.println("Duración: " + cancion.get("duration") + " segundos");
                System.out.println("Reproducciones: " + cancion.get("reproduction"));
                System.out.println("Lenguaje: " + cancion.get("lenguage"));
                System.out.println("---");

                System.out.print("¿Agregar esta canción a una playlist? (s/n): ");
                String resp = sc.nextLine().trim().toLowerCase();
                if (resp.equals("s")) {
                    var playlists = ConsultaPlaylists.listarPlaylistsPorUsuario(null, currentUser.getUser_id());
                    if (playlists == null || playlists.isEmpty()) {
                        System.out.print("No tienes playlists. ¿Deseas crear una? (s/n): ");
                        String crear = sc.nextLine().trim().toLowerCase();
                        if (crear.equals("s")) {
                            MenuPlaylist.crearPlaylist(currentUser, sc);
                            playlists = ConsultaPlaylists.listarPlaylistsPorUsuario(null, currentUser.getUser_id());
                        } else {
                            continue;
                        }
                    }

                    System.out.println("Elige la playlist por ID:");
                    for (var p : playlists) {
                        System.out.println("ID: " + p.get("playlist_id") + " - " + p.get("name_playlist"));
                    }
                    System.out.print("Ingresa el ID de la playlist: ");
                    String idInput = sc.nextLine().trim();
                    int playlistId = -1;
                    try {
                        playlistId = Integer.parseInt(idInput);
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido. Se omite la operación.");
                        continue;
                    }

                    boolean added = ConsultaPlaylists.agregarCancionAPlaylist(null, playlistId, songId);
                    if (added) {
                        System.out.println("✓ Canción agregada a la playlist.");
                    } else {
                        System.out.println("✗ No se pudo agregar la canción a la playlist.");
                    }
                }
            }
        } else {
            System.out.println("No se encontraron canciones.");
            sc.nextLine();
        }
        sc.nextLine();
    }
}
