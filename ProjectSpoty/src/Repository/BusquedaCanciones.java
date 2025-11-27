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

public class BusquedaCanciones {

    /**
     * Busca canciones por nombre (búsqueda parcial, case-insensitive).
     * Retorna una lista de Maps con info completa de cada canción.
     */
    public static List<Map<String, Object>> buscarxNombre(Connection connection, String nombre) {
        GeneralService.cleanScreen();
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
                    "WHERE LOWER(c.song_name) LIKE LOWER(?) " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
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
            System.err.println("Error al buscar canciones por nombre: " + e.getMessage());
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
     * Busca canciones por nombre de artista (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxArtista(Connection connection, String artista) {
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
                    "WHERE LOWER(a.name_artist) LIKE LOWER(?) " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + artista + "%");
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
            System.err.println("Error al buscar canciones por artista: " + e.getMessage());
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
     * Busca canciones por género (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxGenero(Connection connection, String genero) {
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
                    "WHERE LOWER(g.gender) LIKE LOWER(?) " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + genero + "%");
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
            System.err.println("Error al buscar canciones por género: " + e.getMessage());
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
     * Busca canciones por nombre de álbum (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxAlbum(Connection connection, String album) {
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
                    "WHERE LOWER(alb.title) LIKE LOWER(?) " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + album + "%");
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
            System.err.println("Error al buscar canciones por álbum: " + e.getMessage());
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
     * Busca canciones por lenguaje de letra (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxLenguaje(Connection connection, String lenguaje) {
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
                    "WHERE LOWER(l.lenguage) LIKE LOWER(?) " +
                    "ORDER BY c.song_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + lenguaje + "%");
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
            System.err.println("Error al buscar canciones por lenguaje: " + e.getMessage());
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
