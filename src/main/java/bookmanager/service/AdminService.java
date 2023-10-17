package bookmanager.service;

import bookmanager.bean.*;
import bookmanager.dao.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    Scanner scanner = new Scanner(System.in);
    AdminDao adminDao = new AdminDaoImpl();
    UserDao userDao = new UserDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    BorrowDao borrowDao = new BorrowDaoImpl();

    //登录方法
    public boolean login() {
        System.out.println("请输入用户名:");
        String userName = scanner.next();
        System.out.println("请输入和密码：");
        String password = scanner.next();

        if (userName == null || userName.equals("")) {
            System.out.println("用户名不能为空");
            return false;
        }
        if ("admin".equals(userName)) {
            Admin admin = adminDao.selectAdmin(userName);
            String adminPassword = admin.getAdminPassword();
            if (adminPassword.equals(password)) {
                System.out.println("登陆成功");
                return true;
            } else {
                System.out.println("密码错误");
                return false;
            }
        } else {
            User user = userDao.selectUser(userName);
            if (user == null) {
                System.out.println("用户名不存在，请联系管理员！");
                return false;
            }
            String userPassword = user.getPassword();
            if (password.equals(userPassword)) {
                System.out.println("登陆成功");
                return true;
            }
            System.out.println("密码错误");
            return false;
        }
    }

    //增加用户
    public boolean addUser() {
        System.out.println("请输入添加的用户名：");
        String username = scanner.next();
        System.out.println("请输入添加的密码：");
        String password = scanner.next();
        System.out.println("请输入添加的手机：");
        String phone = scanner.next();
        System.out.println("请输入添加的生日：");
        String birthday = scanner.next();
        User user = new User(username, password, phone, birthday);
        boolean result = adminDao.addUser(user);
        if (result) {
            System.out.println("添加成功");
            return true;
        } else {
            System.out.println("添加失败");
            return false;
        }

    }

    //修改密码
    public boolean updatePassword() {
        System.out.println("请输入你要修改密码的用户名：");
        String userName = scanner.next();
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
    }

    //删除用户
    public boolean deleteUser() {
        System.out.println("请输入要删除的用户名：");
        String userName = scanner.next();
        System.out.println("你确定删除" + userName + "这个用户信息吗？Y/N");
        String ok = scanner.next();
        if ("N".equals(ok)) {
            return false;
        } else if (!"Y".equals(ok)) {
            System.out.println("请规范输入");
            return false;
        } else {
            boolean result = adminDao.deleteUser(userName);
            if (result) {
                System.out.println("删除成功");
                return true;
            } else {
                System.out.println("删除失败");
                return false;
            }
        }
    }

    //查询指定用户的信息及借阅信息
    public void selectUserInfo() {
        System.out.println("请输入要查询信息的用户名");
        String userName = scanner.next();
        List<BorrowDTO> borrowDTOList = adminDao.selectUserBorrowInfo(userName);
        if (borrowDTOList != null) {
            System.out.println(borrowDTOList);
        } else {
            System.out.println("没有查询到对应的数据！");
        }
    }

    //增加图书
    public boolean addBook() {
        System.out.println("请输入要增加书籍的名称：");
        String bookName = scanner.next();
        System.out.println("请输入要增加书籍的出版社：");
        String publisher = scanner.next();
        System.out.println("请输入要增加书籍的作者：");
        String author = scanner.next();
        System.out.println("请输入要增加书籍的类别：");
        String bookType = scanner.next();
        System.out.println("请输入要增加书籍的剩余数量：");
        int remain = scanner.nextInt();

        Book book = new Book();
        book.setBookName(bookName);
        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setBookType(bookType);
        book.setRemain(remain);

        boolean result = adminDao.addBook(book);

        if (result) {
            System.out.println("添加书籍成功");
            return true;
        } else {
            System.out.println("添加书籍失败");
            return false;
        }
    }

    //删除图书
    public boolean deleteBook() {
        System.out.println("请输入你要删除的图书的名称");
        String bookName = scanner.next();
        if (String.valueOf(bookDao.getIdByName(bookName)).equals("-1")){
            System.out.println("不存在该图书！");
        }else {
            System.out.println("你确定删除" + bookName + "这本书籍信息吗？Y/N");
            String ok = scanner.next();
            if ("N".equals(ok)) {
                return false;
            } else if (!"Y".equals(ok)) {
                System.out.println("请规范输入");
                return false;
            } else {
                //判断图书借阅情况
                int bookId = bookDao.getIdByName(bookName);
//            System.out.println(bookId);
                boolean rs = borrowDao.selectBorrowInfoById(bookId);
                if (rs) {
                    System.out.println("该图书被借阅不能删除！");
                    return false;
                } else {
                    //删除
                    boolean result = adminDao.deleteBookByName(bookName);
                    if (result) {
                        System.out.println("删除成功");
                        return true;
                    } else {
                        System.out.println("删除失败");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //修改图书
    public boolean updateBook() {
        System.out.println("请输入要修改的图书名：");
        String bookName = scanner.next();
        //id修改图书信息
        //验证（防止空指针异常）
        if (String.valueOf(bookDao.getIdByName(bookName)).equals("-1")){
            System.out.println("不存在该图书！");
        }
        else {
            int bookId = bookDao.getIdByName(bookName);
            System.out.println("修改后的图书名：");
            String newBookName = scanner.next();
            System.out.println("修改后的出版社：");
            String publisher = scanner.next();
            System.out.println("修改后的作者：");
            String author = scanner.next();
            System.out.println("修改后的类别：");
            String bookType = scanner.next();
            System.out.println("修改后的数量：");
            int remain = scanner.nextInt();

            Book book = new Book();
            book.setBookName(newBookName);
            book.setPublisher(publisher);
            book.setAuthor(author);
            book.setBookType(bookType);
            book.setRemain(remain);

            //调用
            boolean result = adminDao.updateBook(book, bookId);
            if (result) {
                System.out.println("修改成功");
                return true;
            } else {
                System.out.println("修改失败");
                return false;
            }
        }
        return false;
    }

    //查询图书
    public void selectBook() {
        System.out.println("请输入查询内容，查询所有图书输入1，查询指定名称输入2");
        try {
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
        } catch (InputMismatchException e) {
            System.out.println("输入的数只能为整数,请重新输入！ ");
        } finally {
            scanner.close();
        }
    }

    //添加借阅 DONE:修改数据库√  重复书籍借阅应选择归还哪一本√
    //TODO:是否需要限制同书只能借阅一本？
    //TODO:事务回滚
    public boolean addBorrowInfo() {
        System.out.println("请输入借阅用户名");
        String userName = scanner.next();

        System.out.println("请输入要借阅的图书名");
        String bookName = scanner.next();

        //判断用户方
        User user = userDao.selectUser(userName);
        int userId = user.getUserId();

        int count = borrowDao.selectBorrowCount(userId);
        if (count == 3) {
            System.out.println("已经借阅了三本书！");
            return false;
        }
        List<BorrowInfo> borrowInfoList = borrowDao.selectBorrowTimeInfo(userId);
        if (borrowInfoList.size() > 0) {
            //存在逾期
            System.out.println("你存在逾期图书，不能借阅，书名如下：");
           for (BorrowInfo borrowInfo : borrowInfoList) {
               String a = bookDao.getNameById(borrowInfo.getBookId());
               System.out.println(a);
           }
            return false;
        }
        //判断书籍方
        List<Book> books = bookDao.selectBookByName(bookName);
        Book book = null;
        for (Book book1 : books
        ) {
            book = book1;
        }
        int remain = 0;
        if (book != null) {
            remain = book.getRemain();
        }
        if (remain == 0) {
            System.out.println("书籍已经被借完了！请选择其他书籍");
            return false;
        }
        int bookId = book.getBookId();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        BorrowInfo borrowInfo = new BorrowInfo(userId, bookId, borrowTime, null, 0);
        //TODO:事务回滚
        boolean result = borrowDao.addBorrowInfo(borrowInfo);
        boolean result1 = bookDao.updateBookRemain(bookId);

        if (result && result1) {
            System.out.println("借书成功，请及时归还！");
            return true;
        } else {
            System.out.println("借书失败！");
            return false;
        }
    }

    //查询所有借阅信息
    public void selectAllBorrowInfo(){
        System.out.println("显示所有借阅信息");
        List<BorrowDTO> borrowDTOList = borrowDao.selectAllInfo();
        System.out.println(borrowDTOList);
    }
    
    //管理员还书
    public void returnBook(){
        System.out.println("请输入还书人的用户名：");
        String userName = scanner.next();
        System.out.println("请输入要还书籍的名称：");
        String bookName = scanner.next();

        User user = userDao.selectUser(userName);
        int userId = user.getUserId();
        int bookId = bookDao.getIdByName(bookName);

        int count = borrowDao.selectRepeatBorrowCount(userId , bookId);

        if (count == 1) {
            //原代码
            if (user == null) {
                System.out.println("用户不存在");
            }
            List<Book> books = bookDao.selectBookByName(bookName);
            Book book = null;
            for (Book book1 : books
            ) {
                book = book1;
            }
            if (book != null) {
                bookId = book.getBookId();
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String returnTime = sdf.format(date);
            BorrowInfo borrowInfo = new BorrowInfo();
            borrowInfo.setUserId(userId);
            borrowInfo.setBookId(bookId);
            borrowInfo.setReturntime(returnTime);
            borrowInfo.setIsreturn(1);
            boolean result = borrowDao.updateBorrowInfo(borrowInfo);
            //TODO:事务回滚
            boolean result2 = bookDao.updateBookRemainAdd(bookId);

            if(result && result2){
                System.out.println("归还成功");
            }else {
                System.out.println("归还失败");
            }
        } else if (count >= 2) {
            //判断有多本同名书籍归还（按照借阅Id）
            //System.out.println("还在写别急");
            System.out.println("查询到有多本未归还同本书籍借阅信息，请根据borrowId选择归还哪一本：");
            List<BorrowReInfo> borrowReInfoList = userDao.selectUserBorrowRepeat(userName,bookName);
            if (borrowReInfoList != null){
                System.out.println(borrowReInfoList);
                System.out.println("请输入borrowId归还：");
                int borrowId = scanner.nextInt();
                List<Book> books = bookDao.selectBookByName(bookName);
                Book book = null;
                for (Book book1 : books
                ) {
                    book = book1;
                }
                if (book != null) {
                    bookId = book.getBookId();
                }
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String returnTime = sdf.format(date);
                BorrowInfo borrowInfo = new BorrowInfo();
                borrowInfo.setBorrowId(borrowId);
                borrowInfo.setReturntime(returnTime);
                borrowInfo.setIsreturn(1);
                boolean result = borrowDao.updateBorrowInfoByBorrwId(borrowInfo);
                //TODO:事务回滚
                boolean result2 = bookDao.updateBookRemainAdd(bookId);

                if(result && result2){
                    System.out.println("归还成功");
                }else {
                    System.out.println("归还失败");
                }
            }
            else {
                System.out.println("错误！");
            }
        }

    }
}
//    回头测试

//    boolean result = borrowDao.addBorrowInfo(borrowInfo);
//if (!result) {
//        // 如果addBorrowInfo操作失败，你需要决定如何处理。例如，你可以抛出一个异常或返回错误信息。
//        throw new RuntimeException("添加借阅信息失败");
//        }
//
//        boolean result1 = bookDao.updateBookRemain(bookId);
//        if (!result1) {
//        // 如果updateBookRemain操作失败，你需要回滚addBorrowInfo操作。
//        borrowDao.rollbackAddBorrowInfo(borrowInfo);
//        throw new RuntimeException("更新书籍剩余数量失败");
//        }
//
//        System.out.println("借书成功，请及时归还！");
//        return true;
