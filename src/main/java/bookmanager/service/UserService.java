package bookmanager.service;

import bookmanager.bean.Book;
import bookmanager.bean.BorrowDTO;
import bookmanager.bean.BorrowInfo;
import bookmanager.bean.User;
import bookmanager.dao.*;

import java.util.List;
import java.util.Scanner;

public class UserService {
//    用户端功能

    Scanner scanner = new Scanner(System.in);
    AdminDao adminDao = new AdminDaoImpl();
    UserDao userDao = new UserDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    BorrowDao borrowDao = new BorrowDaoImpl();
    // 1，用户登录 DONE
    //通用 DONE
    public boolean login(String userName,String password) {
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();

        User user = userDao.selectUser(userName);
        if (user == null){
//            System.out.println("用户不存在！");
//            response.getWriter().write("登录失败");

            return false;
        } else {
            String userPassword = user.getPassword();
            if (userPassword.equals(password)){
//                System.out.println("登陆成功");
            return true;
            } else {
            System.out.println("密码错误");
            return false;
            }
        }
    }
    //登录方法 DONE
    public void Userlogin(){
        System.out.println("请输入用户名:");
        String userName = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        login(userName, password);
    }
    // 2，查看个人信息 DONE
    public void selectUserInfo(String userName,String password) {
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();
        if (login(userName, password)) {
            System.out.println("您的个人信息如下：");
            User users = userDao.selectUserInfo(userName);
            System.out.println(users);
        } else {
            System.out.println("信息错误！");
        }
    }
//    3，修改个人信息，不能修改用户名 DONE
    public boolean updateUserInfo(String userName,String password){
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();
        if (login(userName, password)) {
            int userId = userDao.getIdByName(userName);
            System.out.println("请输入新用户名");
            String userNameNew = scanner.next();
            System.out.println("请输入新手机号");
            String phone = scanner.next();
            System.out.println("请输入新出生日期（yyyy-MM-dd）");
            String birthday = scanner.next();

            User user = new User();
            user.setUserName(userNameNew);
            user.setPhone(phone);
            user.setBirthday(birthday);

            boolean result = userDao.updateUser(user, userId);
            if (result) {
                System.out.println("修改成功");
                return true;
            } else {
                System.out.println("修改失败");
                return false;
            }
        } else {
            System.out.println("输入有误！");
            return false;
        }
    }
//    4，修改密码 DONE
    public boolean updatePassword(String userName,String password) {
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();
        if (login(userName, password)) {
            System.out.println("请输入新密码：");
            String pwd1 = scanner.next();
            System.out.println("请在此输入新密码：");
            String pwd2 = scanner.next();
            if (!pwd1.equals(pwd2)) {
                return false;
            }
            boolean result = adminDao.updatePWD(userName, pwd1);
            if (result) {
                System.out.println("修改密码成功");
                return true;
            } else {
                System.out.println("修改密码失败");
                return false;
            }
        } else {
            return false;
        }

    }
//    5，查看所有图书信息 DONE
    public void bookInfo(){
        System.out.println("请输入查询内容，查询所有图书输入1，查询指定名称输入2");
        int i = scanner.nextInt();
        if (i == 1) {
            System.out.println("查询所有图书");
            List<Book> books = bookDao.selectAllBook();
            System.out.println(books);
        } else if (i == 2) {
            System.out.println("按照名字查询图书");
            System.out.println("请输入查询书籍的名称");
            String bookName = scanner.next();
            List<Book> books = bookDao.selectBookByName(bookName);
            System.out.println(books);
        } else {
            System.out.println("输入有误！");
        }
    }
//    6，查看自己的借阅信息 DONE
    public void selectUserBorrowInfo(String userName,String password){
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();
        if(login(userName,password)) {
            List<BorrowDTO> borrowDTOList = userDao.selectUserBorrowInfo(userName);
            if (borrowDTOList != null) {
                System.out.println(borrowDTOList);
            }else {
                System.out.println("没有查询到对应的数据！");
            }
        }else {
            System.out.println("输入有误！");
        }

    }
//    7，查看自己是否存在逾期借阅信息 DONE
    public boolean WhetherUserOverdue(String userName,String password){
//        System.out.println("请输入用户名:");
//        String userName = scanner.next();
//        System.out.println("请输入密码：");
//        String password = scanner.next();
        if(login(userName,password)) {
            User user = userDao.selectUser(userName);
//        //判断
//        //DONE:是否需要在每个模块前均添加登录？
//        if (user == null){
//            System.out.println("用户不存在！");
//            return false;
//        }
            int userId = user.getUserId();

            List<BorrowInfo> borrowInfoList = borrowDao.selectBorrowTimeInfo(userId);
            if (borrowInfoList.size() > 0) {
                //存在逾期
                System.out.println("你存在逾期图书，不能借阅！");
                System.out.println("书名如下：");
                for (BorrowInfo borrowInfo : borrowInfoList) {
                    String a = bookDao.getNameById(borrowInfo.getBookId());
                    System.out.println(a);
                }
                return false;
            } else {
                System.out.println("无逾期，请继续保持！");
                return true;
            }
        }else {
            return false;
        }
    }
}
