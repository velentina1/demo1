package bookmanager.bean;

public class BorrowInfo {
    private int borrowId;
    private int userId;
    private int bookId;
    private String borrowtime;
    private String returntime;
    private int isreturn;

    public BorrowInfo() {
    }

    public BorrowInfo(int borrowId,int userId, int bookId, String borrowtime, String returntime, int isreturn) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.isreturn = isreturn;
    }

    public BorrowInfo(int userId, int bookId, String borrowtime, String returntime, int isreturn) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.isreturn = isreturn;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public int getIsreturn() {
        return isreturn;
    }

    public void setIsreturn(int isreturn) {
        this.isreturn = isreturn;
    }

    @Override
    public String toString() {
        return "BorrowInfo{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", borrowtime='" + borrowtime + '\'' +
                ", returntime='" + returntime + '\'' +
                ", isreturn=" + isreturn +
                '}';
    }
}
