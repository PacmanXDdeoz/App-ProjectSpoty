package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Config.Con;
import Service.GeneralService;

public class ConsultaAlbumes {

    public static Map<String, Object> consultaAlbum(Connection connection, int albumId) {
        GeneralService.cleanScreen();

        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesAlbum = new HashMap<>();

        try {
            connection = Con.getConn();
            String query = "SELECT album_id, title, date_release, count_songs, artist_id " +
                            "FROM musica.albumes WHERE album_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, albumId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesAlbum.put("album_id", resultSet.getInt("album_id"));
                detallesAlbum.put("title", resultSet.getString("title"));
                detallesAlbum.put("date_release", resultSet.getString("date_release"));
                detallesAlbum.put("count_songs", resultSet.getInt("count_songs"));
                detallesAlbum.put("artist_id", resultSet.getInt("artist_id"));
            } else {
                System.out.println("No se encontró el álbum con ID: " + albumId);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar álbum: " + e.getMessage());
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

        return detallesAlbum;
    }
}
