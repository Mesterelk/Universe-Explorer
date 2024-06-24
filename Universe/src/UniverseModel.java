import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class UniverseModel {
    private List<String> universeNames;
    void fetchUniverseNamesFromDatabase() {
        universeNames = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT universe_name FROM Universe");
            while (resultSet.next()) {
                universeNames.add(resultSet.getString("universe_name"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getUniverseNames() { return universeNames; }
}