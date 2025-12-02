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
            System.out.println("----- Buscar Playlist -----");
            System.out.println("1. Ver Mis Playlists");
            System.out.println("2. Ver Todas las Playlists");
            System.out.println("3. Crear Playlist");
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
}
