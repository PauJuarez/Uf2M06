package Tasque2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDAO implements DAOInterface<SportClass> {
    private final Connection connection;

    public SportDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(SportClass sport) {
    	String sql = "INSERT INTO deportes (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SportClass> getAll() {
        List<SportClass> sports = new ArrayList<>();
        String sql = "SELECT cod, nombre FROM deportes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sports.add(new SportClass(rs.getInt("cod"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sports;
    }
}
