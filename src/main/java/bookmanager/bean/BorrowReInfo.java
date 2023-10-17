package bookmanager.bean;

public class BorrowReInfo {

    private int borrowId;
    private String userName;
    private String bookName;
    private String borrowtime;

    public BorrowReInfo(int borrowId, String userName, String bookName, String borrowtime) {
        this.borrowId = borrowId;
        this.userName = userName;
        this.bookName = bookName;
        this.borrowtime = borrowtime;
    }

    public BorrowReInfo() {
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    @Override
    public String toString() {
        return "BorrowReInfo{" +
                "borrowId=" + borrowId +
                ", userName='" + userName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowtime='" + borrowtime + '\'' +
                '}';
    }
}
