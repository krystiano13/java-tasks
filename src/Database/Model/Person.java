package Database.Model;

/**
 * Klasa reprezentująca osobę w bazie danych.
 * Zawiera metody do operacji na tabeli grup.
 */
public class Person extends Model {
    public String[] columns = {"id", "name", "last_name"};

    public Person() {
        super();
        this.table = "persons";
    }
}
