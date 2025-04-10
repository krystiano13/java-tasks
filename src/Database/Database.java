package Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import UI.MainFrame;

/**
 * Klasa z maksymalnie jedną instancją pozwalająca na połączenie się i
 * wykonywanie operacji na bazie danych
 */
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
            if(e.getSQLState() != "SQLITE_DONE") {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** 
     * Nakładka na select query pozwalająca pobrać rezultat zapytań 
     * @param statement
     * @return ResultSet
     */
    public ResultSet getQuery(String statement) {
        Statement stmt;
        ResultSet result;

        try {
            stmt = this.connection.createStatement();
            result = stmt.executeQuery(statement);
            return result;
        } catch (SQLException e) {
            if(e.getSQLState() != "SQLITE_DONE") {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 

        return null;
    }

    /** 
     * Nakładka na polcenia insert, update i delete pozwalająca na sprawdzenie czy 
     * operacja zakończyła się powodzeniem
     * @param statement
     * @return boolean
     */
    public boolean setQuery(String statement) {
        Statement stmt;

        try {
            stmt = this.connection.createStatement();
            stmt.executeQuery(statement);
            return true;
        } catch (SQLException e) {
            if(e.getSQLState() != "SQLITE_DONE") {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 

        return false;
    }
 
    /** 
     * Funkcja pozwala na globalny dostęp do instancji klasy Database
     * @return Database
     */
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
}