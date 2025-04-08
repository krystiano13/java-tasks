import Database.Database;
import Database.Seeds.MainSeed;
import UI.MainFrame;
import Database.Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Database db = Database.getInstance();
        MainSeed.seed();

        MainFrame frame = MainFrame.getInstance();

        Task taskModel = new Task();
        taskModel.update("text = 'Learn Ruby on Rails'", "id=1");
        ResultSet result = taskModel.select("*", "");
        
        try {
            while (result.next()) {
                int columnCount = result.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(result.getString(i) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}