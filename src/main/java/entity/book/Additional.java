package entity.book;

import java.util.Objects;

public class Additional {
    private Integer pageCount;
    private Size size;

    public Integer getPageCount() {
        return pageCount;
    }

    public Additional setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Size getSize() {
        return size;
    }

    public Additional setSize(Size size) {
        this.size = size;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Additional that = (Additional) o;
        return Objects.equals(pageCount, that.pageCount) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, size);
    }

    @Override
    public String toString() {
        return "Additional{" +
                "pageCount=" + pageCount +
                ", size=" + size +
                '}';
    }
}
