package bookmanager.InterfaceS;

import bookmanager.service.UserService;

import java.util.Scanner;

public class UserInterface {
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);
    public void UserInterfaceS(){
        System.out.println("--------请登录：--------");
        System.out.println("请输入用户名:");
        String userName = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        boolean userYes = userService.login(userName,password);
        while (!userYes){
            System.out.println("登录失败，请重新登录！");
            System.out.println("请输入用户名:");
            String userNameS = scanner.next();
            System.out.println("请输入密码：");
            String passwordS = scanner.next();
            userYes = userService.login(userNameS,passwordS);
        }
        System.out.println("登陆成功！");
        while (userYes) {
            System.out.println("------------请选择功能------------");
            System.out.println("----1.查看个人信息----------------");
            System.out.println("----2.修改个人信息----------------");
            System.out.println("----3.修改密码--------------------");
            System.out.println("----4.查看所有图书信息--------------");
            System.out.println("----5.查看所有已借阅信息-------------");
            System.out.println("----6.查询自己是否存在逾期-----------");
            System.out.println("请输入数字 1-6选择对应功能，输入0退出至主界面：");

            int input = scanner.nextInt();
            if (input == 0) {
                System.out.println("感谢您的使用！");
                break;
            }
            if (input == 2) {
                userService.updateUserInfo(userName, password);
                System.out.println("更新成功！请重新登录");
                break;
            }
            if (input == 3) {
                userService.updatePassword(userName, password);
                System.out.println("请重新登录");
                break;
            }
            switch (input) {
                case 1:
                    userService.selectUserInfo(userName, password);
                    continue;
                case 4:
                    userService.bookInfo();
                    continue;
                case 5:
                    userService.selectUserBorrowInfo(userName, password);
                    continue;
                case 6:
                    userService.WhetherUserOverdue(userName, password);
                    continue;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}
