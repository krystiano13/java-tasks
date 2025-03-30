package Database.Model;

public class Group extends Model {
    public String[] columns = { "id", "name" };

    public Group() {
        super();
        this.table = "groups";
    }
}
