import business.Login;
import entity.Account;
import presentation.UserManagement;
import presentation.WareHousePresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Account loginImfor = null;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("------------Đăng Nhập----------------");
            System.out.println("Tên đăng nhập: ");
            String userName = sc.nextLine();
            System.out.println("Mật khẩu: ");
            String password = sc.nextLine();
            if(userName.isEmpty()||password.isEmpty()){
                System.out.println(colorRed("Tên đăng nhập hoặc mật khẩu đang để trống!!!"));
            }
            else if (userName.length()>30||password.length()>30){
                System.out.println(colorRed("Tên đăng nhập hoặc mật khảu quá dài!!!"));
            }else {
                Login login = new Login();
                loginImfor = login.login(userName,password);
                if(loginImfor==null){
                    System.out.println(colorRed("Sai tài khoản hoặc mật khẩu!! Vui lòng nhập lại!!"));
                }else if (!loginImfor.isStatus()){
                    System.out.println(colorRed("Tài khoản đang bị khóa!!"));
                }
                else {
                   if(!loginImfor.isPermission()){
                       WareHousePresentation whp = new WareHousePresentation();
                       whp.menu(sc,loginImfor);
                   }else {
                       UserManagement userManagement = new UserManagement();
                       userManagement.menu(sc,loginImfor);
                   }
                    break;
                }
            }
        }while (true);
    }
    public static String colorRed(String text){
        return "\u001B[31m"+text+"\u001B[0m";
    }
}
