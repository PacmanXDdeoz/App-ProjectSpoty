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

    /**
     * Busca artistas por nombre (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxNombre(Connection connection, String nombre) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT artist_id, name_artist, name_real, country, type_artist FROM musica.artistas WHERE LOWER(name_artist) LIKE LOWER(?) ORDER BY artist_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> artista = new HashMap<>();
                artista.put("artist_id", rs.getInt("artist_id"));
                artista.put("name_artist", rs.getString("name_artist"));
                artista.put("name_real", rs.getString("name_real"));
                artista.put("country", rs.getString("country"));
                artista.put("type_artist", rs.getString("type_artist"));
                lista.add(artista);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar artistas por nombre: " + e.getMessage());
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
     * Busca artistas por país (búsqueda parcial, case-insensitive).
     */
    public static List<Map<String, Object>> buscarxPais(Connection connection, String pais) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT artist_id, name_artist, name_real, country, type_artist FROM musica.artistas WHERE LOWER(country) LIKE LOWER(?) ORDER BY artist_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + pais + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> artista = new HashMap<>();
                artista.put("artist_id", rs.getInt("artist_id"));
                artista.put("name_artist", rs.getString("name_artist"));
                artista.put("name_real", rs.getString("name_real"));
                artista.put("country", rs.getString("country"));
                artista.put("type_artist", rs.getString("type_artist"));
                lista.add(artista);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar artistas por país: " + e.getMessage());
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
     * Busca artistas por tipo (Solista, Banda, Grupo, etc.).
     */
    public static List<Map<String, Object>> buscarxTipo(Connection connection, String tipo) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT artist_id, name_artist, name_real, country, type_artist FROM musica.artistas WHERE LOWER(type_artist) LIKE LOWER(?) ORDER BY artist_id";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + tipo + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> artista = new HashMap<>();
                artista.put("artist_id", rs.getInt("artist_id"));
                artista.put("name_artist", rs.getString("name_artist"));
                artista.put("name_real", rs.getString("name_real"));
                artista.put("country", rs.getString("country"));
                artista.put("type_artist", rs.getString("type_artist"));
                lista.add(artista);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar artistas por tipo: " + e.getMessage());
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
     * Lista todos los artistas.
     */
    public static List<Map<String, Object>> listarTodos(Connection connection) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            connection = Con.getConn();
            String sql = "SELECT artist_id, name_artist, name_real, country, type_artist FROM musica.artistas ORDER BY artist_id";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> artista = new HashMap<>();
                artista.put("artist_id", rs.getInt("artist_id"));
                artista.put("name_artist", rs.getString("name_artist"));
                artista.put("name_real", rs.getString("name_real"));
                artista.put("country", rs.getString("country"));
                artista.put("type_artist", rs.getString("type_artist"));
                lista.add(artista);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar todos los artistas: " + e.getMessage());
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