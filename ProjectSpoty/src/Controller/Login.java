package Controller;
import Model.Users;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static Users login (Connection connection, String email, String password){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Users users = null;

        try {
            connection = Con.getConn();

            String query = "select * from musica.usuarios where email = ? " +
                            "and password = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {                
                users = new Users();
                users.setUser_id(resultSet.getInt("user_id"));
                users.setName(resultSet.getString("user_name"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));

                System.out.println("Login exitoso!" + " " + users.getName());
            } else {
                System.out.println("Credenciales incorrectas");
            }
            } catch (SQLException e) {
                System.err.println("Error al verificar credenciales: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // ? Cerrar recursos
                try {
                    if (resultSet != null) resultSet.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
                Con.closeConnetion(connection);
            }
            return users;
        }
}
