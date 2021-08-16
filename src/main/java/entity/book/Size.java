package entity.book;

import java.util.Objects;

public class Size {
    private Integer height;
    private Integer width;
    private Integer length;

    public Integer getHeight() {
        return height;
    }

    public Size setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Size setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public Size setLength(Integer length) {
        this.length = length;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Objects.equals(height, size.height) && Objects.equals(width, size.width) && Objects.equals(length, size.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, length);
    }

    @Override
    public String toString() {
        return "Size{" +
                "height=" + height +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
