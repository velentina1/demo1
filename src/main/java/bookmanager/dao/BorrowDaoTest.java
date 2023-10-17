package bookmanager.dao;

import bookmanager.bean.User;

import java.util.Scanner;

public class BorrowDaoTest {
    public static void main(String[] args) {
        //测试计数功能
        BorrowDao borrowDao = new BorrowDaoImpl();
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();

        System.out.println("请输入还书人的用户名：");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();
        System.out.println("请输入要还书籍的名称：");
        String bookName = scanner.next();
        User user = userDao.selectUser(userName);
        int userId = user.getUserId();
        int bookId = bookDao.getIdByName(bookName);

        System.out.println(userId);
        System.out.println(bookId);

        int count = borrowDao.selectRepeatBorrowCount(userId , bookId);

        System.out.println(count);
    }
}
