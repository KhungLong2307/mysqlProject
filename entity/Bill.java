package entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Bill {
    private int id = 0;
    private String code = "";
    private boolean type = true;
    private String idCreated = "";
    private String ceated = "";
    private String idAuth = null;
    private String authDate = "";
    private int status = 0;

    public Bill() {
    }

    public Bill(int id, String code, boolean type, String idCreated, String ceated, String idAuth, String authDate, int status) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.idCreated = idCreated;
        this.ceated = ceated;
        this.idAuth = idAuth;
        this.authDate = authDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getIdCreated() {
        return idCreated;
    }

    public void setIdCreated(String idCreated) {
        this.idCreated = idCreated;
    }

    public String getCeated() {
        return ceated;
    }

    public void setCeated(String ceated) {
        this.ceated = ceated;
    }

    public String getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(String idAuth) {
        if (idAuth==null){
            this.idAuth = "null";
        }else {
            this.idAuth = idAuth;
        }
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public boolean input(Scanner sc){
        boolean isExit =false;
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        while(!isExit){
            System.out.println("Hãy nhập mã code gồm 5 ký tự");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit =true;
                break;
            }else {
                if (input.length()!=5){
                    System.out.println(colorRed("Mã code phải là 5 ký tự!!!"));
                }else {
                    code = input;
                    break;
                }
            }
        }
        if (isExit){
            isReturn = false;
        }
        return isReturn;
    }
    public boolean update(Scanner sc){
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        System.out.println(colorGreen("Nếu không muốn cập nhật thông tin thì có thể để trống"));
        boolean isExit = false;
        boolean isReturn =true;
        while(!isExit){
            System.out.println("Hãy nhập mã code mới gồm 5 ký tự");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.isEmpty()){
                    break;
                }else {
                    if (input.length()!=5){
                        System.out.println(colorRed("Mã code không hợp lệ!!!"));
                    }else {
                        code = input;
                        break;
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy chọn trạng thái hiện tại phiếu nhập");
            System.out.println("1. Tạo");
            System.out.println("2. hủy");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.isEmpty()){
                    break;
                }else {
                    if (!Pattern.matches("[12]",input)){
                        System.out.println(colorRed("Hãy chọn 1 hoặc 2!!!"));
                    }else {
                        status = (Integer.parseInt(input)-1);
                        break;
                    }
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
