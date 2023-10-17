package bookmanager.dao;

import bookmanager.bean.BorrowDTO;
import bookmanager.bean.BorrowReInfo;
import bookmanager.bean.User;

import java.util.List;

public interface UserDao {
    User selectUser(String userName);

    User selectUserInfo(String userName);

    int getIdByName(String userName);

    boolean updateUser(User user, int userId);

    List<BorrowDTO> selectUserBorrowInfo(String userName);
    List<BorrowReInfo> selectUserBorrowRepeat(String userName, String bookName);
}
