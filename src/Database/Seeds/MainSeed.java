package Database.Seeds;

import Database.Database;

/**
 * Klasa MainSeed jest odpowiedzialna za inicjalizację bazy danych
 * poprzez tworzenie niezbędnych tabel, jeśli jeszcze nie istnieją.
 */
public final class MainSeed {
    
    /**
     * Metoda seed() jest używana do tworzenia tabel w bazie danych.
     * Tworzy tabele: groups, persons i tasks, jeśli nie istnieją.
     * 
     * Tabela groups zawiera:
     * - id: unikalny identyfikator grupy (INTEGER, PRIMARY KEY, AUTOINCREMENT)
     * - name: nazwa grupy (VARCHAR(100), NOT NULL)
     * 
     * Tabela persons zawiera:
     * - id: unikalny identyfikator osoby (INTEGER, PRIMARY KEY, AUTOINCREMENT)
     * - name: imię osoby (VARCHAR(50), NOT NULL)
     * - last_name: nazwisko osoby (VARCHAR(50), NOT NULL)
     * 
     * Tabela tasks zawiera:
     * - id: unikalny identyfikator zadania (INTEGER, PRIMARY KEY, AUTOINCREMENT)
     * - text: treść zadania (VARCHAR(255), NOT NULL)
     * - group_id: identyfikator grupy, do której należy zadanie (INTEGER)
     * - person_id: identyfikator osoby przypisanej do zadania (INTEGER)
     * 
     * Klucze obce:
     * - group_id odnosi się do id w tabeli groups
     * - person_id odnosi się do id w tabeli persons
     */
    public static void seed() {
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
                "FOREIGN KEY (group_id) REFERENCES groups(id)," +
                "FOREIGN KEY (person_id) REFERENCES persons(id));"
            );
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
