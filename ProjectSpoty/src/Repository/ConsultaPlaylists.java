package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Config.Con;
import Service.GeneralService;

public class ConsultaPlaylists {

    /**
     * Consulta una playlist por su id.
     * Retorna un Map con keys: "playlist_id", "name_playlist", "description", "date_create", "user_id".
     */
    public static Map<String, Object> consultaPlaylist(Connection connection, int playlistId) {
        GeneralService.cleanScreen();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Map<String, Object> playlist = new HashMap<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT playlist_id, name_playlist, description, date_create, user_id FROM musica.playlist WHERE playlist_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, playlistId);
            rs = ps.executeQuery();

            if (rs.next()) {
                playlist.put("playlist_id", rs.getInt("playlist_id"));
                playlist.put("name_playlist", rs.getString("name_playlist"));
                playlist.put("description", rs.getString("description"));
                playlist.put("date_create", rs.getDate("date_create"));
                playlist.put("user_id", rs.getInt("user_id"));
            } else {
                System.out.println("No se encontró playlist con id: " + playlistId);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar playlist: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return playlist;
    }

    /**
     * Lista las playlists de un usuario por su user_id.
     * Retorna una lista de Maps (cada Map tiene keys: playlist_id, name_playlist, description, date_create, user_id).
     */
    public static List<Map<String, Object>> listarPlaylistsPorUsuario(Connection connection, int userId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT playlist_id, name_playlist, description, date_create, user_id FROM musica.playlist WHERE user_id = ? ORDER BY playlist_id";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> p = new HashMap<>();
                p.put("playlist_id", rs.getInt("playlist_id"));
                p.put("name_playlist", rs.getString("name_playlist"));
                p.put("description", rs.getString("description"));
                p.put("date_create", rs.getDate("date_create"));
                p.put("user_id", rs.getInt("user_id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar playlists por usuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return lista;
    }

    /**
     * Lista todas las playlists.
     */
    public static List<Map<String, Object>> listarPlaylists(Connection connection) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT playlist_id, name_playlist, description, date_create, user_id FROM musica.playlist ORDER BY playlist_id";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> p = new HashMap<>();
                p.put("playlist_id", rs.getInt("playlist_id"));
                p.put("name_playlist", rs.getString("name_playlist"));
                p.put("description", rs.getString("description"));
                p.put("date_create", rs.getDate("date_create"));
                p.put("user_id", rs.getInt("user_id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar playlists: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return lista;
    }

}
