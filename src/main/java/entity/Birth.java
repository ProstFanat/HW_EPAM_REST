package entity;

import org.apache.log4j.Logger;
import java.util.Objects;

public class Birth {
    private String date;
    private String country;
    private String city;

    private static final Logger LOG = Logger.getLogger(Birth.class);

    public Birth(String date, String country, String city){
        setDate(date);
        setCountry(country);
        setCity(city);
    }

    public Birth(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        LOG.info(String.format("Setting birth date = %s", date));
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        LOG.info(String.format("Setting birth country = %s", country));
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        LOG.info(String.format("Setting birth city = %s", city));
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", country=" + country +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birth birth = (Birth) o;
        return Objects.equals(date, birth.date) && Objects.equals(country, birth.country) && Objects.equals(city, birth.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, country, city);
    }
}
