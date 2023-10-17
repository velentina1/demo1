package bookmanager;

import bookmanager.InterfaceS.AdminInterface;
import bookmanager.InterfaceS.UserInterface;

import java.util.Objects;
import java.util.Scanner;

//可循环输出的版本
public class InterfaceTest1 {
    public static void main(String[] args) {
        AdminInterface adminInterface = new AdminInterface();
        UserInterface userInterface = new UserInterface();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            //界面
            System.out.println("---------图书借阅系统----------");
            System.out.println("------请选择您的登录身份：------");
            System.out.println("------1.管理员----2.用户------");
            System.out.println("--------选择0退出本系统--------");
            String UserType = scanner.nextLine();
            while (!Objects.equals(UserType, "1") && !Objects.equals(UserType, "2") && !Objects.equals(UserType, "0")){
                System.out.print("输入错误！请重新输入:");
                UserType = scanner.nextLine();
            }
            switch (UserType) {
                case "1"://管理员
                    adminInterface.AdminInterfaceS();
                    break;
                case "2"://用户
                    userInterface.UserInterfaceS();
                    break;
                case "0":
                    System.out.println("感谢您的使用！");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
            choice = Integer.parseInt(UserType);
        }
    }
}
