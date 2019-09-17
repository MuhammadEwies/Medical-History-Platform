package Services;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Locations {

    private String lat;
    @Id
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lon) {
        this.lng = lon;
    }

    public Locations() {

    }

    public Locations(String lat, String lng) {

        this.lat = lat;
        this.lng = lng;
    }

    private String lng;
}