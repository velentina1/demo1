package bookmanager.dao;

import bookmanager.bean.Admin;
import bookmanager.bean.Book;
import bookmanager.bean.BorrowDTO;
import bookmanager.bean.User;

import java.util.List;

public interface AdminDao {
    //查询管理员信息
    Admin selectAdmin(String adminName);

    //增加用户
    boolean addUser(User user);

    boolean updatePWD(String userName, String pwd1);

    boolean deleteUser(String userName);
    List <BorrowDTO> selectUserBorrowInfo(String userName);

    boolean addBook(Book book);

    boolean deleteBookByName(String bookName);

    boolean updateBook(Book book, int bookId);
}
