import UI.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;

import Database.Migrations.MainMigration;

/**
 * Główna klasa aplikacji
 */
public class App {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        MainMigration.migrate();
        MainFrame.getInstance();
    }
}