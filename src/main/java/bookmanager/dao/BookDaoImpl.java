package bookmanager.dao;

import bookmanager.bean.Book;

import java.util.List;


public class BookDaoImpl extends BaseDao implements BookDao{
    @Override
    public int getIdByName(String bookName) {
        String sql = "select * from book where bookName = ?";
        Book book = QueryOne(Book.class,sql,bookName);
//        System.out.println(book.getBookId());
        if (book == null){
            return -1;
        } else {
            return book.getBookId();
        }
    }

    @Override
    public String getNameById(int bookId){
        String sql = "select * from book where bookId = ?";
        Book book = QueryOne(Book.class,sql,bookId);
        return book.getBookName();
    }

    @Override
    public List<Book> selectAllBook() {
        String sql = "select * from book";
        List<Book> books = QueryAll(Book.class, sql);
        return books;
    }

    @Override
    public List<Book> selectBookByName(String bookName) {
        String sql = "select * from book where bookName like ?";
        bookName = "%" + bookName + "%";
        List<Book> books = QueryAll(Book.class, sql, bookName);
        return books;
    }

    @Override
    public boolean updateBookRemain(int bookId) {
        String sql = "update book set remain = remain - 1 where bookId = ?";
        return update(sql,bookId);
    }

    @Override
    public boolean updateBookRemainAdd(int bookId) {
        String sql = "update book set remain = remain + 1 where bookId = ?";
        return update(sql,bookId);
    }
}
