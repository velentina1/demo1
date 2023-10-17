package bookmanager;


//不能循环的版本
public class InterfaceTest0 {
//    public static void main(String[] args) {
//        AdminInterface adminInterface = new AdminInterface();
//        UserInterface userInterface = new UserInterface();
//        //一般路过控制台输出
//        System.out.println("---------图书借阅系统----------");
//        System.out.println("------请选择您的登录身份：------");
//        System.out.println("------1.管理员----2.用户------");
//        System.out.println("--------选择0退出本系统--------");
//        Scanner scanner = new Scanner(System.in);
//        String UserType = scanner.nextLine();
//        //int还是太麻烦了
//        while (!Objects.equals(UserType, "1") && !Objects.equals(UserType, "2") && !Objects.equals(UserType, "0")){
//            System.out.print("输入错误！请重新输入:");
//            UserType = scanner.nextLine();
//        }
//        //管理员部分
//        if (UserType.equals("1")){
//            adminInterface.AdminInterfaceS();
//        }
//        //用户部分
//        else if (UserType.equals("2")) {
//            userInterface.UserInterfaceS();
//        }
//        //退出
//        else {
//            System.out.println("感谢您的使用！");
//        }
//    }
}
