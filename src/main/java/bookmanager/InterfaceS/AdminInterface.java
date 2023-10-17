package bookmanager.InterfaceS;

import bookmanager.service.AdminService;

import java.util.Scanner;

public class AdminInterface {
    AdminService adminService = new AdminService();
    Scanner scanner = new Scanner(System.in);
    public void AdminInterfaceS(){
        System.out.println("--------请登录：--------");
        boolean adminYes = adminService.login();
        while (!adminYes){
            System.out.println("登录失败，请重新登录！");
            adminYes = adminService.login();
        }

        while (true) {
            System.out.println("------------请选择功能-----------");
            System.out.println("----1.添加普通用户----------------");
            System.out.println("----2.修改普通用户密码-------------");
            System.out.println("----3.删除普通用户-----------------");
            System.out.println("----4.查询指定用户的信息及借阅信息----");
            System.out.println("----5.添加图书--------------------");
            System.out.println("----6.删除图书--------------------");
            System.out.println("----7.修改图书--------------------");
            System.out.println("----8.查询图书--------------------");
            System.out.println("----9.添加借阅信息-----------------");
            System.out.println("----10.查询所有借阅信息-------------");
            System.out.println("----11.办理还书-------------------");
            System.out.println("请输入数字 1-11选择对应功能，输入0退出至主界面：");

            int input = scanner.nextInt();
            if (input == 0) {
                System.out.println("感谢您的使用！");
                break;
            }
            switch (input) {
                case 1:
                    adminService.addUser();
                    continue;
                case 2:
                    adminService.updatePassword();
                    continue;
                case 3:
                    adminService.deleteUser();
                    continue;
                case 4:
                    adminService.selectUserInfo();
                    continue;
                case 5:
                    adminService.addBook();
                    continue;
                case 6:
                    adminService.deleteBook();
                    continue;
                case 7:
                    adminService.updateBook();
                    continue;
                case 8:
                    adminService.selectBook();
                    continue;
                case 9:
                    adminService.addBorrowInfo();
                    continue;
                case 10:
                    adminService.selectAllBorrowInfo();
                    continue;
                case 11:
                    adminService.returnBook();
                    continue;
                default:
                    System.out.println("输入错误，请重新输入数字 1-11。");
            }
        }
    }
}
