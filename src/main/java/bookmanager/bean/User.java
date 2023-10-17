package bookmanager.bean;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String phone;
    private String birthday;

    public User() {
    }

    public User(String userName, String password, String phone, String birthday) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
    }

    public User(int userId, String userName, String password, String phone, String birthday) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
