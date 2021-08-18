package entity;

import java.util.Objects;

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookLanguage;
    private String bookDescription;
    private Additional additional;
    private Integer publicationYear;

    public Integer getBookId() {
        return bookId;
    }

    public Book setBookId(Integer bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public Book setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
        return this;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public Book setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        return this;
    }

    public Additional getAdditional() {
        return additional;
    }

    public Book setAdditional(Additional additional) {
        this.additional = additional;
        return this;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Book setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(bookName, book.bookName) && Objects.equals(bookLanguage, book.bookLanguage) && Objects.equals(bookDescription, book.bookDescription) && Objects.equals(additional, book.additional) && Objects.equals(publicationYear, book.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, bookLanguage, bookDescription, additional, publicationYear);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookLanguage='" + bookLanguage + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", additional=" + additional +
                ", publicationYear=" + publicationYear +
                '}';
    }

    public static class Additional {
        private Integer pageCount;
        private Size size;

        public Integer getPageCount() {
            return pageCount;
        }

        public Book.Additional setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Size getSize() {
            return size;
        }

        public Book.Additional setSize(Size size) {
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

    public static class Size {
        private Integer height;
        private Integer width;
        private Integer length;

        public Integer getHeight() {
            return height;
        }

        public Book.Size setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Integer getWidth() {
            return width;
        }

        public Book.Size setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public Integer getLength() {
            return length;
        }

        public Book.Size setLength(Integer length) {
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
}
