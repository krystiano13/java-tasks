import Database.Database;
import Database.Seeds.MainSeed;
import UI.MainFrame;

/**
 * Główna klasa aplikacji
 */
public class App {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Database db = Database.getInstance();
        MainSeed.seed();
        MainFrame frame = MainFrame.getInstance();
    }
}