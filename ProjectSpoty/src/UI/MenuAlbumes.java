package UI;

import java.util.Scanner;

import Config.Con;
import Service.GeneralService;
import Repository.ConsultaAlbumes;

public class MenuAlbumes {
    public static void albumes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
            System.out.println("░░░░░░░░░░ Menu Albumes ░░░░░░░░░░");
            System.out.println("        ⓵. Ver Albumes");
            System.out.println("        ⓶. Buscar Albumes");
            System.out.println("        ⓷. Volver al Menú Principal");
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
                        var todos = ConsultaAlbumes.listarTodos(null);
                        if (todos != null && !todos.isEmpty()) {
                            for (var album : todos) {
                                System.out.println("Título: " + album.get("title"));
                                System.out.println("Fecha de lanzamiento: " + album.get("date_release"));
                                System.out.println("Cantidad de canciones: " + album.get("count_songs"));
                                // mostrar nombre del artista si está disponible
                                if (album.get("artist_name") != null) {
                                    System.out.println("Artista: " + album.get("artist_name"));
                                } else {
                                    System.out.println("ID del artista: " + album.get("artist_id"));
                                }
                                System.out.println("---");
                            }
                            sc.nextLine();
                        } else {
                            System.out.println("No hay álbumes para mostrar.");
                            sc.nextLine();
                        }
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

    public static void buscarAlbumes() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
            System.out.println("░░░░░░░░░░ Buscar Albumes ░░░░░░░░░░");
            System.out.println("        ⓵. Buscar por título");
            System.out.println("        ⓶. Buscar por artista");
            System.out.println("        ⓷. Volver al Menú Principal");
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
                            sc.nextLine();
                        }
                        sc.nextLine();
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
                        sc.nextLine();
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
}