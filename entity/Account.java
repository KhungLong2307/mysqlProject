package entity;

import business.AccountImp;
import business.EmployeeImp;
import business.IAccount;
import business.IEmployee;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    private int id = 0;
    private String userName = "";
    private String passWord = "";
    private boolean permission = false;
    private String empId = "";
    private boolean status = false;

    public Account() {
    }

    public Account(int id, String userName, String passWord, boolean permission, String empId, boolean status) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.permission = permission;
        this.empId = empId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean input(Scanner sc){
        boolean isExit = false;
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        while(!isExit){
            System.out.println("Hãy nhập tên tài khoản");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0||input.length()>30){
                    System.out.println(colorRed("Tên tài khoản quá dài hoặc đang bỏ trống!!! Vui lòng nhập lại!!!"));
                }else {
                    IAccount iAccount = new AccountImp();
                    if (!iAccount.isCheckUserName(input)){
                        System.out.println(colorRed("Tên tài khoản đã có người tạo!!!"));
                    }else {
                        userName = input;
                        break;
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập mật khẩu");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0||input.length()>30){
                    System.out.println(colorRed("Tên tài khoản quá dài hoặc đang bỏ trống!!! Vui lòng nhập lại!!!"));
                }else {
                    passWord = input;
                    break;
                }
            }
        }
        permission =true;
        while(!isExit){
            System.out.println("Hãy nhập mã nhân viên");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()!=5){
                    System.out.println(colorRed("Mã nhân viên gồm 5 ký tự!!! Vui lòng nhập lại!!!"));
                }else {
                    IAccount iAccount = new AccountImp();
                    if(!iAccount.isCheckEmpId(input)){
                        System.out.println(colorRed("Đã có tài khoản sử dụng mã nhân viên này!!!"));
                        System.out.println(colorRed("Một nhân viên không có 2 tài khoản!!!"));
                    }else {
                        IEmployee iEmployee = new EmployeeImp();
                        if (iEmployee.isCheckId(input)){
                            System.out.println(colorRed("Mã nhân viên không tồn tại!!!"));
                        }else {
                            empId = input;
                            break;
                        }
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy chọn trạng thái tài khoản");
            System.out.println("1. Active");
            System.out.println("2. Block");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (!Pattern.matches("[12]",input)){
                    System.out.println(colorRed("Hãy chọn đúng chức năng!!!"));
                }else {
                    if (Integer.parseInt(input)==1){
                        status = true;
                    }else {
                        status = false;
                    }
                    break;
                }
            }
        }
        if (isExit){
            isReturn = false;
        }
        return isReturn;
    }
    public String colorRed(String text){
        return "\u001B[31m"+text+"\u001B[0m";
    }
    public String colorGreen(String text){
        return "\u001B[32m"+text+"\u001B[0m";
    }
}
