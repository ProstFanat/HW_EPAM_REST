package entity;

import org.apache.log4j.Logger;

import java.util.Objects;

public class AuthorName {
    private String first;
    private String second;

    private static final Logger LOG = Logger.getLogger(AuthorName.class);

    public AuthorName(String first, String second){
        setFirst(first);
        setSecond(second);
    }

    public AuthorName(){

    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
        LOG.info(String.format("Setting first name = %s", first));
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
        LOG.info(String.format("Setting second name = %s", second));
    }

    @Override
    public String toString() {
        return "{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorName that = (AuthorName) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
