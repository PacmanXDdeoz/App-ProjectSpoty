package UI;

import java.util.Scanner;
import Service.GeneralService;
import Repository.ConsultaAlbumes;

public class MenuAlbumes {
    public static void albumes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar Albumes -----");
            System.out.println("1. Ver Albumes");
            System.out.println("2. Buscar Albumes");
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
                        ConsultaAlbumes.listarTodos(null);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarAlbumes();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        Menu.mostrarMenuPrincipal();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        albumes();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    public static void buscarAlbumes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar -----");
            System.out.println("1. Buscar por título");
            System.out.println("2. Buscar por artista");
            System.out.println("3. Regresar");
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
                        System.out.print("Ingresa el título del álbum a buscar: ");
                        String titulo = sc.nextLine();
                        var resultados = ConsultaAlbumes.buscarxTitulo(null, titulo);
                        if (resultados != null && !resultados.isEmpty()) {
                            for (var album : resultados) {
                                System.out.println("Título: " + album.get("title"));
                                System.out.println("Fecha de lanzamiento: " + album.get("date_release"));
                                System.out.println("Cantidad de canciones: " + album.get("count_songs"));
                                System.out.println("ID del artista: " + album.get("artist_id"));
                                System.out.println("---");
                            }
                        } else {
                            System.out.println("No se encontraron álbumes.");
                        }
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        System.out.print("Ingresa el nombre del artista a buscar: ");
                        String artista = sc.nextLine();
                        var resultados2 = ConsultaAlbumes.buscarxArtista(null, artista);
                        if (resultados2 != null && !resultados2.isEmpty()) {
                            for (var album : resultados2) {
                                System.out.println("Título: " + album.get("title"));
                                System.out.println("Fecha de lanzamiento: " + album.get("date_release"));
                                System.out.println("Cantidad de canciones: " + album.get("count_songs"));
                                System.out.println("ID del artista: " + album.get("artist_id"));
                                System.out.println("---");
                            }
                        }
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        albumes();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        albumes();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}