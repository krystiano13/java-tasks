package Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public final class Database {
    public Connection connection;
    private static Database instance;
    private String url;

    Database() {
        this.url = "jdbc:sqlite:database.db";

        try {
            this.connection = DriverManager.getConnection(this.url);
            System.out.println("Connected to database !");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getQuery(String statement) {
        Statement stmt;
        ResultSet result;

        try {
            stmt = this.connection.createStatement();
            result = stmt.executeQuery(statement);
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 

        return null;
    }

    public boolean setQuery(String statement) {
        Statement stmt;

        try {
            stmt = this.connection.createStatement();
            stmt.executeQuery(statement);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 

        return false;
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
}