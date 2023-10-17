package bookmanager.bean;

public class Book {
    private int bookId;
    private String bookName;
    private String publisher;
    private String author;
    private String bookType;
    private int remain;

    public Book() {
    }

    public Book(int bookId, String bookName, String publisher, String author, String bookType, int remain) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
        this.bookType = bookType;
        this.remain = remain;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", bookType='" + bookType + '\'' +
                ", remain=" + remain +
                '}';
    }
}
