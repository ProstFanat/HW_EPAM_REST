package entity;

import org.apache.log4j.Logger;

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
}
