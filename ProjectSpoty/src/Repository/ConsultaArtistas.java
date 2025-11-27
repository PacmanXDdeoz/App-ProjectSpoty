package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Config.Con;
import Service.GeneralService;

public class ConsultaArtistas {
    public static Map<String, Object> consultaArtista(Connection connection, int artistId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesArtista = new HashMap<>();

        try {
            connection = Con.getConn();
            String query = "SELECT artist_id, name_artist, name_real, country, type_artist " +
                            "FROM musica.artistas " +
                            "WHERE artist_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, artistId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesArtista.put("artist_id", resultSet.getInt("artist_id"));
                detallesArtista.put("name_artist", resultSet.getString("name_artist"));
                detallesArtista.put("name_real", resultSet.getString("name_real"));
                detallesArtista.put("country", resultSet.getString("country"));
                detallesArtista.put("type_artist", resultSet.getString("type_artist"));
            } else {
                System.out.println("No se encontraron datos para artist_id: " + artistId);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar artista: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return detallesArtista;
    }
}