package Database.Model;

import java.sql.ResultSet;
import Database.Database;

/**
 * Klasa abstrakcyjna która służy jako rodzic dla klas reprezentujących
 * poszczególne tabele w bazie danych. Zawiera funkcję pozwalające na 
 * wykonywanie podstawowych operacji na bazie danych - SELECT, INSERT, UPDATE i DELETE
 */
public abstract class Model {
    protected String table;
    protected Database db;

    public Model() {
        this.table = "model";
        this.db = Database.getInstance();
    }

    
    /** 
     * Funkcja pozwala wykonać zapytanie SELECT dla konkretnej tabeli
     * Podajemy jakie kolumny chcemy wyciągnąć oraz warunki jakie 
     * muszą spełniać wyciągnięte elementy
     * @param columns
     * @param conditions
     * @return ResultSet
     */
    public ResultSet select(String columns, String conditions) {
        String conditionString = "";

        if(!conditions.isEmpty()) {
            conditionString = "WHERE " + conditions;
        }

        return this.db.getQuery(
            "SELECT " + columns + " From " + this.table + 
            " " + conditionString
        );
    }

    
    /** 
     * Funkcja pozwala na wykonanie polecenia INSERT
     * Podajemy po kolei wartości dla pojedyńczego wiersza tabeli
     * oprócz id, które jest ustawione na AUTOINCREMENT
     * @param values
     * @return boolean
     */
    public boolean create(String values) {
        return this.db.setQuery(
            "INSERT INTO " + this.table + " " +
            "VALUES (NULL, " + values + ");"
        );
    }

    
    /** 
     * Pozwala na wykonanie polecenia UPDATE
     * Dla funkcji podajemy jako argumenty wartości jakie chcemy ustawić
     * oraz warunki
     * @param values
     * @param conditions
     * @return boolean
     */
    public boolean update(String values, String conditions) {
        return this.db.setQuery(
            "Update " + this.table + " SET " +
            values + " " + "WHERE " + conditions
        );
    }

    
    /** 
     * Funkcja pozwala na usunięcie konkretnego wiersza z 
     * konkretnej tabeli. Podajemy id elementu, który chcemy usunąć
     * @param id
     * @return boolean
     */
    public boolean delete(int id) {
        return this.db.setQuery(
            "DELETE FROM " + this.table +
            " WHERE id=" +  id + ";"
        );
    }
}
