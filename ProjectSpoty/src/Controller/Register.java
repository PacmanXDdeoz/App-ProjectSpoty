package Controller;

import Config.Con;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
        
    public static Users register(Scanner sc) {
        System.out.print("Ingresa tu nombre/nombres: ");
        String name = sc.nextLine();

        System.out.print("Ingresa tu correo: ");
        String email = sc.nextLine();

        System.out.print("Ingresa tu contraseña: ");
        String password = sc.nextLine();

        Connection connection = null;
        try {
            connection = Con.getConn();
            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?) RETURNING id";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        Users user = new Users(id, name, email, password);
                        System.out.println("Usuario registrado con id: " + id);
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
        } finally {
            Con.closeConnetion(connection);
        }
        return null;
    }
}
