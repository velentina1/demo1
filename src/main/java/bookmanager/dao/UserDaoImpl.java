package bookmanager.dao;

import bookmanager.bean.BorrowDTO;
import bookmanager.bean.BorrowReInfo;
import bookmanager.bean.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao{

    @Override
    public User selectUser(String userName) {
        String sql = "select * from book_user where userName = ?";
        return QueryOne(User.class,sql,userName);
    }

    @Override
    public User selectUserInfo(String userName) {
        String sql = "select userName,phone,birthday from book_user where userName = ?";
        return QueryOne(User.class,sql,userName);
    }

    @Override
    public int getIdByName(String userName) {
        String sql = "select * from book_user where userName = ?";
        User user = QueryOne(User.class,sql,userName);
        return user.getUserId();
    }

    @Override
    public boolean updateUser(User user, int userId) {
        String sql = "update book_user set userName = ?, phone = ?, birthday = ? where userId = ?";
        return update(sql,user.getUserName(),user.getPhone(),user.getBirthday(),userId);
    }

    @Override
    public List<BorrowDTO> selectUserBorrowInfo(String userName) {
        String sql = "select u.userName as userName, u.phone as phone, b2.bookName as bookName, b1.borrowtime as borrowtime, b1.returntime as returntime from book_user as u left join borrowinfo as b1 on u.userId = b1.userId left join book as b2 on b1.bookId = b2.bookId where u.userName = ?";
        return QueryAll(BorrowDTO.class,sql,userName);
    }

    @Override
    public List<BorrowReInfo> selectUserBorrowRepeat(String userName, String bookName) {
        String sql = "select b1.borrowId as borrowId, u.userName as userName, b2.bookName as bookName, b1.borrowtime as borrowtime from book_user as u left join borrowinfo as b1 on u.userId = b1.userId left join book as b2 on b1.bookId = b2.bookId where u.userName = ? and b2.bookName = ? and b1.isreturn != 1";
        return QueryAll(BorrowReInfo.class,sql,userName,bookName);
    }
}
