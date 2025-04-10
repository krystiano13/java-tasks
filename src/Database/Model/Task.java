package Database.Model;

/**
 * Klasa reprezentująca zadanie w bazie danych.
 * Zawiera metody do operacji na tabeli grup.
 */
public class Task extends Model {
    public String[] columns = { "id", "text", "group_id", "person_id" };

    public Task() {
        super();
        this.table = "tasks";
    }
}
