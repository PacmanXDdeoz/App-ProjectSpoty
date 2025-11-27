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

    /**
     * Busca álbumes por título (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxTitulo(Connection connection, String titulo) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT album_id, title, date_release, count_songs, artist_id FROM musica.albumes WHERE LOWER(title) LIKE LOWER(?) ORDER BY album_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + titulo + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> album = new HashMap<>();
                album.put("album_id", rs.getInt("album_id"));
                album.put("title", rs.getString("title"));
                album.put("date_release", rs.getString("date_release"));
                album.put("count_songs", rs.getInt("count_songs"));
                album.put("artist_id", rs.getInt("artist_id"));
                lista.add(album);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar álbumes por título: " + e.getMessage());
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
     * Busca álbumes por artista (búsqueda por artist_id).
     */
    public static List<Map<String, Object>> buscarxArtista(Connection connection, int artistId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT album_id, title, date_release, count_songs, artist_id FROM musica.albumes WHERE artist_id = ? ORDER BY album_id";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, artistId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> album = new HashMap<>();
                album.put("album_id", rs.getInt("album_id"));
                album.put("title", rs.getString("title"));
                album.put("date_release", rs.getString("date_release"));
                album.put("count_songs", rs.getInt("count_songs"));
                album.put("artist_id", rs.getInt("artist_id"));
                lista.add(album);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar álbumes por artista: " + e.getMessage());
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
     * Lista todos los álbumes.
     */
    public static List<Map<String, Object>> listarTodos(Connection connection) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT album_id, title, date_release, count_songs, artist_id FROM musica.albumes ORDER BY album_id";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> album = new HashMap<>();
                album.put("album_id", rs.getInt("album_id"));
                album.put("title", rs.getString("title"));
                album.put("date_release", rs.getString("date_release"));
                album.put("count_songs", rs.getInt("count_songs"));
                album.put("artist_id", rs.getInt("artist_id"));
                lista.add(album);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar todos los álbumes: " + e.getMessage());
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
