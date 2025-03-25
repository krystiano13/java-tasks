package Database.Seeds;

import Database.Database;

public final class MainSeed {
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
