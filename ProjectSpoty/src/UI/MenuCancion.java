package UI;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import Service.GeneralService;
import Repository.ConsultaCanciones;
import Repository.BusquedaCanciones;

public class MenuCancion {
    public static void cancion() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
            System.out.println("░░░░░░░░░░ Menu Canciones ░░░░░░░░░░");
            System.out.println("        ⓵. Ver Canciones");
            System.out.println("        ⓶. Buscar Canciones");
            System.out.println("        ⓷. Volver al Menú Principal");
            System.out.println("-----------------------------");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            sc.nextLine();
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verCanciones(sc);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarCanciones(sc);
                        GeneralService.cleanScreen();
                        break;
                    case 3:
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

    public static void verCanciones(Scanner sc) {
        var todas = ConsultaCanciones.listarTodasCanciones(null);
        if (todas != null && !todas.isEmpty()) {
            for (var cancion : todas) {
                System.out.println("Nombre: " + cancion.get("song_name"));
                System.out.println("Artista: " + cancion.get("name_artist"));
                System.out.println("Género: " + cancion.get("gender"));
                System.out.println("Álbum: " + cancion.get("album_name"));
                System.out.println("Duración: " + cancion.get("duration") + " segundos");
                System.out.println("Reproducciones: " + cancion.get("reproduction"));
                System.out.println("Lenguaje: " + cancion.get("lenguage"));
                System.out.println("---");
            }
        } else {
            System.out.println("No hay canciones para mostrar.");
            sc.nextLine();
        }
        sc.nextLine();
    }

    public static void buscarCanciones(Scanner sc) {
        int opcion = 0;
        do {
            try {
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
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre de la canción a buscar: ");
                        String nombre = sc.nextLine();
                        var resultados = BusquedaCanciones.buscarxNombre(null, nombre);
                        mostrarCanciones(resultados);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre del artista a buscar: ");
                        String artista = sc.nextLine();
                        var resultados2 = BusquedaCanciones.buscarxArtista(null, artista);
                        mostrarCanciones(resultados2);
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el género a buscar: ");
                        String genero = sc.nextLine();
                        var resultados3 = BusquedaCanciones.buscarxGenero(null, genero);
                        mostrarCanciones(resultados3);
                        GeneralService.cleanScreen();
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre del álbum a buscar: ");
                        String album = sc.nextLine();
                        var resultados4 = BusquedaCanciones.buscarxAlbum(null, album);
                        mostrarCanciones(resultados4);
                        GeneralService.cleanScreen();
                        break;
                    case 5:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el lenguaje a buscar: ");
                        String lenguaje = sc.nextLine();
                        var resultados5 = BusquedaCanciones.buscarxLenguaje(null, lenguaje);
                        mostrarCanciones(resultados5);
                        GeneralService.cleanScreen();
                        break;
                    case 6:
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

    private static void mostrarCanciones(List<Map<String, Object>> resultados) {
        Scanner sc = new Scanner(System.in);
        if (resultados != null && !resultados.isEmpty()) {
            for (var cancion : resultados) {
                System.out.println("░░░░░░░░░░ Canción ░░░░░░░░░░");
                System.out.println("Nombre: " + cancion.get("song_name"));
                System.out.println("Artista: " + cancion.get("name_artist"));
                System.out.println("Género: " + cancion.get("gender"));
                System.out.println("Álbum: " + cancion.get("album_name"));
                System.out.println("Duración: " + cancion.get("duration") + " segundos");
                System.out.println("Reproducciones: " + cancion.get("reproduction"));
                System.out.println("Lenguaje: " + cancion.get("lenguage"));
                System.out.println("---");
            }
        } else {
            System.out.println("No se encontraron canciones.");
            sc.nextLine();
        }
        sc.nextLine();
    }
}
