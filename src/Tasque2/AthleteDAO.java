package Tasque2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO implements DAOInterface<AthleteClass> {
    private final Connection connection;

    public AthleteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(AthleteClass athlete) {
        String sql = "INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, athlete.getName());
            stmt.setInt(2, athlete.getSportId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar atleta: " + e.getMessage());
        }
    }

    @Override
    public List<AthleteClass> getAll() {
        List<AthleteClass> athletes = new ArrayList<>();
        String sql = "SELECT cod, nombre, cod_deporte FROM deportistas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                athletes.add(new AthleteClass(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de atletas: " + e.getMessage());
        }
        return athletes;
    }

    public List<AthleteClass> findName(String partialName) {
        List<AthleteClass> athletes = new ArrayList<>();
        String sql = "SELECT cod, nombre, cod_deporte FROM deportistas WHERE LOWER(nombre) LIKE LOWER(?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + partialName + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                athletes.add(new AthleteClass(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar atletas por nombre: " + e.getMessage());
        }
        return athletes;
    }
}
