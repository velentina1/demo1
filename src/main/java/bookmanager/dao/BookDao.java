package bookmanager.dao;

import bookmanager.bean.Book;

import java.util.List;

public interface BookDao {
    //通过图书名称获取图书id
    int getIdByName(String bookName);

    public String getNameById(int bookId);

    //查询所有书籍
    List<Book> selectAllBook();
    //根据名称获取书籍
    List<Book> selectBookByName(String bookName);
    //书籍数量减一
    boolean updateBookRemain(int bookId);

    boolean updateBookRemainAdd(int bookId);
}
