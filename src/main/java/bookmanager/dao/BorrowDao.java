package bookmanager.dao;

import bookmanager.bean.BorrowDTO;
import bookmanager.bean.BorrowInfo;

import java.util.List;

public interface BorrowDao {
    //根据图书id查看图书是否被借阅
    boolean selectBorrowInfoById(int bookId);

    int selectBorrowCount(int userId);

    int selectRepeatBorrowCount(int userId, int bookId);

    List<BorrowInfo> selectBorrowTimeInfo(int userId);

    boolean addBorrowInfo(BorrowInfo borrowInfo);

    List<BorrowDTO> selectAllInfo();

    boolean updateBorrowInfo(BorrowInfo borrowInfo);
    boolean updateBorrowInfoByBorrwId(BorrowInfo borrowInfo);
    boolean rollbackAddBorrowInfo(BorrowInfo borrowInfo);
}
