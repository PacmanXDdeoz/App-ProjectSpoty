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

public class ConsultaCanciones {

    /**
     * Consulta una canción por su id.
     * Retorna un Map con información completa: song_id, song_name, duration, date_creation, reproduction,
     * name_artist, gender, album_name, lyrics_content, lenguage, version.
     */
    public static Map<String, Object> consultaCancion(Connection connection, int songId) {
        GeneralService.cleanScreen();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Map<String, Object> cancion = new HashMap<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT " +
                    "c.song_id, c.song_name, c.duration, c.date_creation, c.reproduction, " +
                    "a.name_artist, g.gender, alb.title AS album_name, " +
                    "l.content AS lyrics_content, l.lenguage, l.version " +
                    "FROM musica.canciones c " +
                    "INNER JOIN musica.artistas a ON c.artist_id = a.artist_id " +
                    "INNER JOIN musica.generos g ON c.gender_id = g.gender_id " +
                    "INNER JOIN musica.albumes alb ON c.album_id = alb.album_id " +
                    "INNER JOIN musica.lyrics l ON c.lyrics_id = l.lyrics_id " +
                    "WHERE c.song_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, songId);
            rs = ps.executeQuery();

            if (rs.next()) {
                cancion.put("song_id", rs.getInt("song_id"));
                cancion.put("song_name", rs.getString("song_name"));
                cancion.put("duration", rs.getInt("duration"));
                cancion.put("date_creation", rs.getDate("date_creation"));
                cancion.put("reproduction", rs.getLong("reproduction"));
                cancion.put("name_artist", rs.getString("name_artist"));
                cancion.put("gender", rs.getString("gender"));
                cancion.put("album_name", rs.getString("album_name"));
                cancion.put("lyrics_content", rs.getString("lyrics_content"));
                cancion.put("lenguage", rs.getString("lenguage"));
                cancion.put("version", rs.getString("version"));
            } else {
                System.out.println("No se encontró canción con id: " + songId);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar canción: " + e.getMessage());
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

        return cancion;
    }

    /**
     * Lista todas las canciones con información completa (artista, género, álbum, letra).
     * Retorna una lista de Maps.
     */
    public static List<Map<String, Object>> listarTodasCanciones(Connection connection) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT " +
                    "c.song_id, c.song_name, c.duration, c.date_creation, c.reproduction, " +
                    "a.name_artist, g.gender, alb.title AS album_name, " +
                    "l.content AS lyrics_content, l.lenguage, l.version " +
                    "FROM musica.canciones c " +
                    "INNER JOIN musica.artistas a ON c.artist_id = a.artist_id " +
                    "INNER JOIN musica.generos g ON c.gender_id = g.gender_id " +
                    "INNER JOIN musica.albumes alb ON c.album_id = alb.album_id " +
                    "INNER JOIN musica.lyrics l ON c.lyrics_id = l.lyrics_id " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> cancion = new HashMap<>();
                cancion.put("song_id", rs.getInt("song_id"));
                cancion.put("song_name", rs.getString("song_name"));
                cancion.put("duration", rs.getInt("duration"));
                cancion.put("date_creation", rs.getDate("date_creation"));
                cancion.put("reproduction", rs.getLong("reproduction"));
                cancion.put("name_artist", rs.getString("name_artist"));
                cancion.put("gender", rs.getString("gender"));
                cancion.put("album_name", rs.getString("album_name"));
                cancion.put("lyrics_content", rs.getString("lyrics_content"));
                cancion.put("lenguage", rs.getString("lenguage"));
                cancion.put("version", rs.getString("version"));
                lista.add(cancion);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar canciones: " + e.getMessage());
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
