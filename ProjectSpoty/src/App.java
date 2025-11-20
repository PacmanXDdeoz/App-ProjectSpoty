import java.sql.*;
import Config.Con;

public class App {
    public static void main(String[] args) {
        //* Menus */

        //* Conexión a la base de datos */
        Con con = null;
        Connection connection = null;
        try {
            connection = con.getConn();
            System.out.println("Conexión correcta");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            con.closeConnetion(connection);
        }
    }
}

