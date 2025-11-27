package UI;

import Controller.Login;
import Config.Con;
import Model.Users;

import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Service.GeneralService;
import UI.MenuSpotify;


public class Menu {

    public static void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("    SPOTIFY - BIENVENIDO");
        System.out.println("=============================");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
        System.out.println("-------------------------");

        int opcion = sc.nextInt();
        sc.nextLine();
        while (true) {
            try {

                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        Users logged = iniciarSesion(sc);
                        if (logged != null) {
                            GeneralService.cleanScreen();
                            MenuSpotify.spotify();
                        }
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // Controller.Register();
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        System.out.println("¡Adiós!");
                        System.exit(0);
                    default:
                        System.out.println("Opción inválida");
                        mostrarMenuPrincipal();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Users iniciarSesion(Scanner sc){
        GeneralService.cleanScreen();
        System.out.println("▶ INICIAR SESIÓN ◀");
        System.out.print("Ingresa tu correo: ");
        String email = sc.nextLine();
        System.out.print("Ingresa tu contraseña: ");
        String pass = sc.nextLine();

        Connection connection = null;
        Users usuarioActual = null;

        try {
            connection = Con.getConn();
            usuarioActual = Login.login(connection, email, pass);
            if (usuarioActual == null) {
                System.out.println("Email o contraseña inválida");
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
        }
        return usuarioActual;
    }
}