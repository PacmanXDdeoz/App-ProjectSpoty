import java.sql.*;
import Config.Con;
import UI.Menu;


public class App {
    public static void main(String[] args) {
        //* Menus */
        Menu.mostrarMenuPrincipal();
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

