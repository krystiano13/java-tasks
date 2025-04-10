package Database.Model;

/**
 * Klasa reprezentująca grupę w bazie danych.
 * Zawiera metody do operacji na tabeli grup.
 */
public class Group extends Model {
    public String[] columns = { "id", "name" };

    public Group() {
        super();
        this.table = "groups";
    }
}
