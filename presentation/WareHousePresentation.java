package presentation;

import entity.Account;

import java.util.Scanner;
import java.util.regex.Pattern;

public class WareHousePresentation {
    public void menu(Scanner sc, Account loginInfor){

        do {
            System.out.println("+----------------------------------------------+");
            System.out.println("|              WAREHOUSE MANAGEMENT            |");
            System.out.println("+----------------------------------------------+");
            System.out.println("| 1.Quản lý sản phẩm                           |");
            System.out.println("| 2.Quản lý nhân viên                          |");
            System.out.println("| 3.Quản lý tài khoản                          |");
            System.out.println("| 4.Quản lý phiêu nhập                         |");
            System.out.println("| 5.Quản lý phiếu xuất                         |");
            System.out.println("| 6.Quản lý báo cáo                            |");
            System.out.println("| 7.Thoát                                      |");
            System.out.println("+----------------------------------------------+");
            int choice = 0;
            do{
                System.out.print("chức năng: ");
                String input = sc.nextLine();
                if(!Pattern.matches("[1234567]",input)){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép!!"));
                }else {
                    choice = Integer.parseInt(input);
                    break;
                }
            }while (true);
            switch (choice){
                case 1:
                    ProductPresentation productPresentation = new ProductPresentation();
                    productPresentation.menu(sc);
                    break;
                case 2:
                    EmployeePresentation employeePresentation = new EmployeePresentation();
                    employeePresentation.menu(sc);
                    break;
                case 3:
                    AccountPresentation accountPresentation = new AccountPresentation();
                    accountPresentation.menu(sc,loginInfor);
                    break;
                case 4:
                    ReceiptPresentation receiptPresentation = new ReceiptPresentation();
                    receiptPresentation.menu(sc,loginInfor);
                    break;
                case 5:
                    BillPresentation billPresentation = new BillPresentation();
                    billPresentation.menu(sc,loginInfor);
                    break;
                case 6:
                    ReportPresentation reportPresentation = new ReportPresentation();
                    reportPresentation.menu(sc);
                    break;
                case 7:
                    System.exit(0);
            }
        }while (true);
    }
    public String colorRed(String text){
        return "\u001B[31m"+text+"\u001B[0m";
    }
}
