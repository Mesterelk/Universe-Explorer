import java.sql.*;
import java.util.ArrayList;

public class Fetcher {
    public ArrayList<UnivComp> fetchData(int idup, String upper, String inner) {
        ArrayList<UnivComp> univcomp = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + inner.substring(0, 1).toUpperCase() + inner.substring(1) + " WHERE " + upper + "_id = " + idup);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(inner+"_name");
                int size = resultSet.getInt("size");
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                String color = resultSet.getString("color");
                int id = resultSet.getInt(inner+"_id");
                String other=null;

                //todo: implement other for planet and whatever
                if (inner.equals("planet") || inner.equals("moon")){
                    other = resultSet.getString("state");
                }

                univcomp.add(new UnivComp(name, size*2, x*8, y*8, color, id, other));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return univcomp;
    }
}
