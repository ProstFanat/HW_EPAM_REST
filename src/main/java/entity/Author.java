package entity;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Author {
    private Integer authorId;
    private AuthorName authorName;
    private String nationality;
    private Birth birth;
    private String authorDescription;

    private static final Logger LOG = Logger.getLogger(Author.class);

    public int getAuthorId() {
        return authorId;
    }

    public Author setAuthorId(int authorId) {
        this.authorId = authorId;
        LOG.info(String.format("Setting authorId = %s", authorId));
        return this;
    }

    public AuthorName getAuthorName() {
        return authorName;
    }

    public Author setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Author setNationality(String nationality) {
        this.nationality = nationality;
        LOG.info(String.format("Setting nationality = %s", nationality));
        return this;
    }

    public Birth getBirth() {
        return birth;
    }

    public Author setBirth(Birth birth) {
        this.birth = birth;
        return this;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public Author setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
        LOG.info(String.format("Setting authorDescription = %s", authorDescription));
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "authorId=" + authorId +
                ", authorName=" + authorName +
                ", nationality=" + nationality +
                ", birth=" + birth +
                ", authorDescription=" + authorDescription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) && Objects.equals(authorName, author.authorName) && Objects.equals(nationality, author.nationality) && Objects.equals(birth, author.birth) && Objects.equals(authorDescription, author.authorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, nationality, birth, authorDescription);
    }
}
