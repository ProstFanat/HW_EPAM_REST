package entity;

import groovy.util.logging.Log;
import org.apache.log4j.Logger;

public class AuthorName {
    private String first;
    private String second;

    private static final Logger LOG = Logger.getLogger(AuthorName.class);

    public AuthorName(String first, String second){
        setFirst(first);
        setSecond(second);
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
}
