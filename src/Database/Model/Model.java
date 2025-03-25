package Database.Model;

import java.sql.ResultSet;
import Database.Database;

public abstract class Model {
    protected String table;
    protected Database db;

    public Model() {
        this.table = "model";
        this.db = Database.getInstance();
    }

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

    public boolean create(String values) {
        return this.db.setQuery(
            "INSERT INTO " + this.table + " " +
            "VALUES (NULL, " + values + ");"
        );
    }

    public boolean update(String values, String conditions) {
        return this.db.setQuery(
            "Update " + this.table + " SET " +
            values + " " + "WHERE " + conditions
        );
    }

    public boolean delete(int id) {
        return this.db.setQuery(
            "DELETE FROM " + this.table +
            " WHERE id=" +  id + ";"
        );
    }
}
