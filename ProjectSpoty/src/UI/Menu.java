package UI;

import Controller.Login;
import Controller.Register;
import Config.Con;
import Model.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Service.GeneralService;
import UI.MenuSpotify;

public class Menu {

    public static void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("=============================");
                System.out.println("    SPOTIFY - BIENVENIDO");
                System.out.println("=============================");
                System.out.println("      ⓵. Iniciar Sesión");
                System.out.println("      ⓶. Registrarse");
                System.out.println("      ⓷. Salir");
                System.out.println("-----------------------------");
                System.out.print("Elige una opción: ");
                int opcion = sc.nextInt();
                System.out.println("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");

                sc.nextLine();

                switch (opcion) {
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        Users logged = iniciarSesion(sc);
                        if (logged != null) {
                            GeneralService.cleanScreen();
                            MenuSpotify.spotify(logged);
                        }
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        registrarse(sc);
                        GeneralService.cleanScreen();
                        break;
                    case 3:
                        System.out.println("¡Adiós!");
                        System.exit(0);
                    default:
                        GeneralService.cleanScreen();
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Users iniciarSesion(Scanner sc) {
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

    public static void registrarse(Scanner sc) {
        GeneralService.cleanScreen();
        System.out.println("▶ REGISTRARSE ◀");
        System.out.print("Ingresa tu nombre: ");
        String name = sc.nextLine();
        System.out.print("Ingresa tu correo: ");
        String email = sc.nextLine();
        System.out.print("Ingresa tu contraseña: ");
        String password = sc.nextLine();

        Connection connection = null;
        try {
            connection = Con.getConn();
            Users nuevoUsuario = Register.register(connection, name, email, password);
            if (nuevoUsuario != null) {
                System.out.println("✓ ¡Registro exitoso! Bienvenido " + name);
            } else {
                System.out.println("✗ Error al registrar el usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
        }
    }
}