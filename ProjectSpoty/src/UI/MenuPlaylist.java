package UI;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import Service.GeneralService;
import Repository.ConsultaPlaylists;
import Config.Con;
import Model.Users;

public class MenuPlaylist {
    public static void playlist(Users currentUser) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
            System.out.println("░░░░░░░░░░ Menu Playlist " + currentUser.getName() + " ░░░░░░░░░░");
            System.out.println("        ⓵. Ver Mis Playlists");
            System.out.println("        ⓶. Ver Todas las Playlists");
            System.out.println("        ⓷. Crear Playlist");
            System.out.println("        ⓸. Ver Contenido de una Playlist");
            System.out.println("        ⓹. Volver al Menú Principal");
            System.out.println("-----------------------------");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            sc.nextLine();
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verMisPlaylists(currentUser, sc);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verTodasPlaylists(sc);
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        crearPlaylist(currentUser, sc);
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verContenidoPlaylist(currentUser, sc);
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        GeneralService.cleanScreen();
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

    public static void verMisPlaylists(Users currentUser, Scanner sc) {
        var mis_playlists = ConsultaPlaylists.listarPlaylistsPorUsuario(null, currentUser.getUser_id());
        if (mis_playlists != null && !mis_playlists.isEmpty()) {
            for (var playlist : mis_playlists) {
                System.out.println("ID: " + playlist.get("playlist_id"));
                System.out.println("Nombre: " + playlist.get("name_playlist"));
                System.out.println("Descripción: " + playlist.get("description"));
                System.out.println("Fecha de creación: " + playlist.get("date_create"));
                System.out.println("---");
            }
        } else {
            System.out.println("No tienes playlists. Crea una nueva.");
            sc.nextLine();
        }
        sc.nextLine();
    }

    public static void verTodasPlaylists(Scanner sc) {
        var todas = ConsultaPlaylists.listarPlaylists(null);
        if (todas != null && !todas.isEmpty()) {
            for (var playlist : todas) {
                System.out.println("ID: " + playlist.get("playlist_id"));
                System.out.println("Nombre: " + playlist.get("name_playlist"));
                System.out.println("Descripción: " + playlist.get("description"));
                System.out.println("Fecha de creación: " + playlist.get("date_create"));
                System.out.println("---");
            }
        } else {
            System.out.println("No hay playlists para mostrar.");
            sc.nextLine();
        }
        sc.nextLine();
    }

    public static void crearPlaylist(Users currentUser, Scanner sc) {
        System.out.print("Nombre de la playlist: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        Connection connection = null;
        try {
            connection = Con.getConn();
            String sql = "INSERT INTO musica.playlist(name_playlist, description, date_create, user_id) VALUES (?, ?, CURRENT_DATE, ?) RETURNING playlist_id";
            var ps = connection.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, currentUser.getUser_id());

            var rs = ps.executeQuery();
            if (rs.next()) {
                int playlistId = rs.getInt("playlist_id");
                System.out.println("✓ ¡Playlist creada exitosamente! ID: " + playlistId);
            } else {
                System.out.println("✗ Error al crear la playlist");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al crear playlist: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
        }
    }

    public static void verContenidoPlaylist(Users currentUser, Scanner sc) {
        // Mostrar las playlists del usuario para escoger
        var mis_playlists = ConsultaPlaylists.listarPlaylistsPorUsuario(null, currentUser.getUser_id());
        if (mis_playlists == null || mis_playlists.isEmpty()) {
            System.out.println("No tienes playlists. Crea una nueva primero.");
            sc.nextLine();
            return;
        }

        System.out.println("Tus Playlists:");
        for (var p : mis_playlists) {
            System.out.println("ID: " + p.get("playlist_id") + " - " + p.get("name_playlist"));
        }
        System.out.print("Ingresa el ID de la playlist para ver su contenido: ");
        String input = sc.nextLine().trim();
        int playlistId = -1;
        try {
            playlistId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        var canciones = ConsultaPlaylists.listarCancionesDePlaylist(null, playlistId);
        if (canciones == null || canciones.isEmpty()) {
            System.out.println("La playlist está vacía o no existe.");
            sc.nextLine();
            return;
        }

        System.out.println("Contenido de la playlist:");
        for (var c : canciones) {
            System.out.println("Song ID: " + c.get("song_id"));
            System.out.println("Nombre: " + c.get("song_name"));
            System.out.println("Artista: " + c.get("name_artist"));
            System.out.println("Género: " + c.get("gender"));
            System.out.println("Álbum: " + c.get("album_name"));
            System.out.println("Duración: " + c.get("duration") + " segundos");
            System.out.println("---");
        }
        sc.nextLine();
    }
}
