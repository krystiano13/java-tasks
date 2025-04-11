package Database.Migrations;

import Database.Database;

/**
 * Klasa MainSeed jest odpowiedzialna za inicjalizację bazy danych
 * poprzez tworzenie niezbędnych tabel, jeśli jeszcze nie istnieją.
 */
public final class MainMigration {
    
    /**
     * Funkcja migrate wywołuje zapytania SQL które tworzą poszczególne tabelki
     * jeśli te z jakiegoś powodu jeszcze nie istnieją w bazie danych
     */
    public static void migrate() {
        try {
            Database db = Database.getInstance();

            db.setQuery(
                "CREATE TABLE IF NOT EXISTS groups (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(100) NOT NULL);"
            );

            db.setQuery(
                "CREATE TABLE IF NOT EXISTS persons (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL);"
            );

            db.setQuery(
                "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "text VARCHAR(255) NOT NULL," +
                "group_id INTEGER," +
                "person_id INTEGER," +
                "done BOOLEAN," +
                "FOREIGN KEY (group_id) REFERENCES groups(id)," +
                "FOREIGN KEY (person_id) REFERENCES persons(id));"
            );
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
