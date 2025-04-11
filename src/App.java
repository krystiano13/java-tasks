import Database.Database;
import Database.Seeds.MainSeed;
import UI.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;

/**
 * Główna klasa aplikacji
 */
public class App {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        Database db = Database.getInstance();
        MainSeed.seed();
        MainFrame frame = MainFrame.getInstance();
    }
}