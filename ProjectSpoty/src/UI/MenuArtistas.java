package UI;

import java.util.Scanner;
import Service.GeneralService;
import Repository.ConsultaArtistas;

public class MenuArtistas {
    public static void artistas() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
                System.out.println("----- Buscar Artista -----");
                System.out.println("1. Ver Artistas");
                System.out.println("2. Buscar Artista");
                System.out.println("0. Volver al Menú Principal");
                System.out.print("Elige una opción: ");
                System.out.println("-------------------------");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        var todos = ConsultaArtistas.listarTodos(null);
                        if (todos != null && !todos.isEmpty()) {
                            for (var a : todos) {
                                System.out.println("ID: " + a.get("artist_id"));
                                System.out.println("Nombre: " + a.get("name_artist"));
                                System.out.println("Nombre real: " + a.get("name_real"));
                                System.out.println("País: " + a.get("country"));
                                System.out.println("Tipo: " + a.get("type_artist"));
                                System.out.println("---");
                            }
                        } else {
                            System.out.println("No hay artistas para mostrar.");
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
                        String nombre = sc.nextLine();
                        var resultados = ConsultaArtistas.buscarxNombre(null, nombre);
                        if (resultados != null && !resultados.isEmpty()) {
                            for (var art : resultados) {
                                System.out.println("ID: " + art.get("artist_id"));
                                System.out.println("Nombre: " + art.get("name_artist"));
                                System.out.println("Nombre real: " + art.get("name_real"));
                                System.out.println("País: " + art.get("country"));
                                System.out.println("Tipo: " + art.get("type_artist"));
                                System.out.println("---");
                            }
                        } else {
                            System.out.println("No se encontraron artistas.");
                            sc.nextLine();
                        }
                        sc.nextLine();
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
}