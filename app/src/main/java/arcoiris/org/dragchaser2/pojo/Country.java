package arcoiris.org.dragchaser2.pojo;

/**
 * Created by kukolka on 3/1/17.
 */

public class Country {

    String imageUrl;
    String population;

    public Country(String imageUrl, String population) {
        this.imageUrl = imageUrl;
        this.population = population;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
