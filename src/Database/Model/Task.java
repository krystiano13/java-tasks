package Database.Model;

public class Task extends Model {
    public String[] columns = { "id", "text", "group_id", "person_id" };

    public Task() {
        super();
        this.table = "tasks";
    }
}
