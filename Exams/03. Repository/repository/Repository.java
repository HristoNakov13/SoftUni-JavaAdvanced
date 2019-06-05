package repository;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Map<Integer, Person> data;
    private Integer id;

    public Repository() {
        this.data = new HashMap<>();
        this.id = 0;
    }
    public void add(Person person) {
        this.data.put(id, person);
        this.id++;

    }
    public Person get(int id) {
        return data.get(id);
    }
    public boolean update(int id, Person newPerson) {
        boolean updated = false;

        if (this.data.containsKey(id)) {
            this.data.put(id, newPerson);
            updated = true;
        }
        return updated;
    }
    public boolean delete(int id) {
        boolean deleted = false;

        if (this.data.containsKey(id)) {
            this.data.remove(id);
            deleted = true;
        }
        return deleted;
    }
    public int getCount() {
        return this.data.size();
    }
}
