package bookmanager.bean;

public class BorrowDTO {
    private String userName;
    private String phone;
    private String bookName;
    private String borrowtime;
    private String returntime;

    public BorrowDTO(){}

    public BorrowDTO(String userName, String phone, String bookName, String borrowtime, String returntime) {
        this.userName = userName;
        this.phone = phone;
        this.bookName = bookName;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    @Override
    public String toString() {
        return "用户以及借阅信息{" +
                "用户名='" + userName + '\'' +
                ", 手机号='" + phone + '\'' +
                ", 书籍名='" + bookName + '\'' +
                ", 借阅时间='" + borrowtime + '\'' +
                ", 归还时间='" + returntime + '\'' +
                '}';
    }
}

