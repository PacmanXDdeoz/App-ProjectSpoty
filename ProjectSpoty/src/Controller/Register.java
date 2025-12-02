package Controller;

import Config.Con;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
    public Register() {
    }

    /**
     * Registra un nuevo usuario en la base de datos
     * @param connection Conexión a la base de datos
     * @param name Nombre del usuario
     * @param email Email del usuario
     * @param password Contraseña del usuario
     * @return Usuario registrado con ID generado, o null si falla
     */
    public static Users register(Connection connection, String name, String email, String password) {
        // Use the actual schema/table and column names from `appSpoty.sql` (musica.usuarios)
        String sql = "INSERT INTO musica.usuarios(user_name, email, password) VALUES (?, ?, ?) RETURNING user_id";
        Users user = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    user = new Users(userId, name, email, password);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
}