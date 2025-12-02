package UI;

import java.util.Scanner;
import Config.Con;
import Service.GeneralService;
import Repository.ConsultaPlaylists;
import java.util.Map;

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
            System.out.println("5. Volver al Menú Principal");
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
                        ConsultaPlaylists.listarPlaylists(null);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        crearPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        eliminarPlaylist();
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        Menu.mostrarMenuPrincipal();
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

    public static void buscarPlaylist() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar -----");
            System.out.println("1. Buscar por su id");
            System.out.println("2. Regresar");
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
                        System.out.print("Ingresa el id de la playlist a buscar: ");
                        int id = sc.nextInt();
                        Map<String, Object> playlist = ConsultaPlaylists.consultaPlaylist(Con.getConn(), id);
                        if (playlist == null) return;
                        System.out.println("Playlist encontrada:");
                        System.out.println("ID: " + playlist.get("playlist_id"));
                        System.out.println("Nombre: " + playlist.get("name_playlist"));
                        System.out.println("Descripción: " + playlist.get("description"));
                        System.out.println("Fecha: " + playlist.get("date_create"));
                        System.out.println("Usuario: " + playlist.get("user_id"));
                        break;
                    case 2:
                        playlist();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        buscarPlaylist();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    public static void crearPlaylist() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre de la playlist: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        System.out.print("ID del usuario dueño de la playlist: ");
        int user = sc.nextInt();
        String sql = "INSERT INTO musica.playlist(name_playlist, description, date_create, user_id) VALUES (?, ?, CURRENT_DATE, ?)";
        try {
            var conn = Con.getConn();
            var ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, desc);
            ps.setInt(3, user);
            ps.executeUpdate();
            ps.close();
            System.out.println("Playlist creada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al crear playlist: " + e.getMessage());
        }
    }

    public static void eliminarPlaylist() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el ID de la playlist que deseas eliminar: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM musica.playlist WHERE playlist_id = ?";
        try {
            var conn = Con.getConn();
            var ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();
            if (rows > 0) {
                System.out.println("✔ Playlist eliminada correctamente.");
            } else {
                System.out.println("No existe playlist con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar playlist: " + e.getMessage());
        }
    }
}
