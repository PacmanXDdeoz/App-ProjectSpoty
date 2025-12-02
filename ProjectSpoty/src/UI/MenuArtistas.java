package UI;

import java.util.Scanner;
import Service.GeneralService;
import Repository.ConsultaArtistas;

public class MenuArtistas {
    public static void artistas() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar Artista -----");
            System.out.println("1. Ver Artistas");
            System.out.println("2. Buscar Artista");
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
                        ConsultaArtistas.listarTodos(null);
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        buscarArtista();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        Menu.mostrarMenuPrincipal();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        artistas();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }

    public static void buscarArtista() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- Buscar -----");
            System.out.println("1. Buscar por nombre");
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
                        System.out.print("Ingresa el nombre del artista a buscar: ");
                        String nombre = sc.nextLine();
                        var resultados = ConsultaArtistas.buscarxNombre(null, nombre);
                        if (resultados != null && !resultados.isEmpty()) {
                            for (var artista : resultados) {
                                System.out.println("ID: " + artista.get("id"));
                                System.out.println("Nombre: " + artista.get("name"));
                                System.out.println("Cantidad de canciones: " + artista.get("count_songs"));
                                System.out.println("---");
                            }
                        } else {
                            System.out.println("No se encontraron artistas.");
                        }
                        GeneralService.cleanScreen();
                        break;
                    case 2:
                        artistas();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        buscarArtista();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (true);
    }
}