package entity;

import java.util.Objects;

public class Genre {
    private Integer genreId;
    private String genreName;
    private String genreDescription;

    public Integer getGenreId() {
        return genreId;
    }

    public Genre setGenreId(Integer genreId) {
        this.genreId = genreId;
        return this;
    }

    public String getGenreName() {
        return genreName;
    }

    public Genre setGenreName(String genreName) {
        this.genreName = genreName;
        return this;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public Genre setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreId, genre.genreId) && Objects.equals(genreName, genre.genreName) && Objects.equals(genreDescription, genre.genreDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName, genreDescription);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                ", genreDescription='" + genreDescription + '\'' +
                '}';
    }
}

