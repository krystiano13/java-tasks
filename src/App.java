import Database.Database;
import Database.Seeds.MainSeed;
import UI.MainFrame;

public class App {
    public static void main(String[] args) {
        Database db = Database.getInstance();
        MainSeed.seed();
        MainFrame frame = MainFrame.getInstance();
    }
}