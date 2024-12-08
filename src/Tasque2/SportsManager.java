package Tasque2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SportsManager {
    private Connection conn;
    private SportDAO sportDAO;
    private AthleteDAO athleteDAO;

    public static void main(String[] args) {
        SportsManager manager = new SportsManager();
        manager.init();
    }

    private void init() {
        try {
            Connect config = new Connect("database.xml");
            conn = config.dbConnect();

            if (conn == null) {
                System.out.println("No se pudo establecer conexión a la base de datos.");
                return;
            }

            sportDAO = new SportDAO(conn);
            athleteDAO = new AthleteDAO(conn);

            while (true) {
                int option = View.showMenu();

                switch (option) {
                    case 1: // Agregar deporte
                        addSport();
                        break;

                    case 2: // Agregar atleta
                        addAthlete();
                        break;

                    case 3: // Buscar atleta por nombre
                        searchAthlete();
                        break;

                    case 4: // Listar atletas por deporte
                        listAthletes();
                        break;

                    case 5: // Salir
                        View.showMessage("Saliendo...");
                        return;

                    default:
                        View.showMessage("Opción no válida. Intente nuevamente.");
                }
            }
        } finally {
            closeConnection();
        }
    }

    private void addSport() {
        String sportName = View.sportForm();
        sportDAO.add(new SportClass(sportName));
        View.showMessage("Deporte agregado con éxito.");
    }

    private void addAthlete() {
        AthleteClass athlete = View.athleteForm();
        athleteDAO.add(athlete);
        View.showMessage("Atleta agregado con éxito.");
    }

    private void searchAthlete() {
        String partialName = View.askAthlete();
        List<AthleteClass> athletes = athleteDAO.findName(partialName);
        View.printAthletes(athletes);
    }

    private void listAthletes() {
        List<SportClass> sports = sportDAO.getAll();
        int selectedSportId = View.askSport(sports);
        List<AthleteClass> athletesBySport = athleteDAO.getAll();
        athletesBySport.stream()
                .filter(a -> a.getSportId() == selectedSportId)
                .forEach(View::printAthlete);
    }

    private void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada con éxito.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
