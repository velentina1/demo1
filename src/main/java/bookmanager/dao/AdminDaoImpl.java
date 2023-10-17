package bookmanager.dao;


import bookmanager.bean.Admin;
import bookmanager.bean.Book;
import bookmanager.bean.BorrowDTO;
import bookmanager.bean.User;

import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao{
    @Override
    public Admin selectAdmin(String adminName) {
        String sql = "select * from admin where adminName = ?";
        return QueryOne(Admin.class,sql,adminName);
    }

    @Override
    public boolean addUser(User user) {
        String sql = "insert into book_user (userName,password,phone,birthday)" +
                " VALUES(?,?,?,?)";
        return update(sql,user.getUserName(),user.getPassword(),user.getPhone(),user.getBirthday());
    }

    @Override
    public boolean updatePWD(String userName, String pwd1) {
        String sql = "update book_user set password = ? where userName = ?";
        return update(sql,pwd1,userName);
    }

    @Override
    public boolean deleteUser(String userName) {
        String sql = "delete from book_user where userName = ?";
        return update(sql,userName);
    }

    @Override
    public List<BorrowDTO> selectUserBorrowInfo(String userName) {
        String sql = "select u.userName as userName, u.phone as phone, b2.bookName as bookName, b1.borrowtime as borrowtime, b1.returntime as returntime from book_user as u left join borrowinfo as b1 on u.userId = b1.userId left join book as b2 on b1.bookId = b2.bookId where u.userName like ?";
        userName = "%" + userName + "%";
        return QueryAll(BorrowDTO.class,sql,userName);
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into book (bookName,publisher,author,bookType,remain) values (?,?,?,?,?)";
        return update(sql,book.getBookName(),book.getPublisher(),book.getAuthor(),book.getBookType(),book.getRemain());
    }

    @Override
    public boolean deleteBookByName(String bookName) {
        String sql = "delete from book where bookName = ?";
        return update(sql,bookName);
    }

    @Override
    public boolean updateBook(Book book, int bookId) {
        String sql = "update book set bookName = ?,publisher = ?,author = ?, bookType = ?, remain = ? where bookId = ?";
        return update(sql,book.getBookName(),book.getPublisher(),book.getAuthor(),book.getBookType(),book.getRemain(),bookId);
    }
}

