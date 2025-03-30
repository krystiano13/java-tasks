package Database.Model;

public class Person extends Model {
    public String[] columns = {"id", "name", "last_name"};

    public Person() {
        super();
        this.table = "persons";
    }
}
