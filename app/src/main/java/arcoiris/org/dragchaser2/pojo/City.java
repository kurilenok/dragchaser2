package arcoiris.org.dragchaser2.pojo;

/**
 * Created by kukolka on 3/1/17.
 */

public class City {

    String id;
    String name;
    String category;

    public City() {
    }

    public City(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
