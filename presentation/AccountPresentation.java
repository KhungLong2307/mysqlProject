package presentation;

import business.AccountImp;
import business.IAccount;
import entity.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountPresentation {
    static List<Account> accountList = new ArrayList<>();
    static IAccount iAccount = new AccountImp();
    static int offSet = 0;
    static int maxOffSet = 0;
    static boolean isOutTable = false;
    static boolean isUpdateOk = false;
    static Account loginInfor = null;
    public void menu(Scanner sc,Account login){
        boolean isExit = false;
        loginInfor = login;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|           ACCOUNT MANAGEMENT              |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Danh sách tài khoản                    |");
            System.out.println("| 2. Tạo tải khoản mới                      |");
            System.out.println("| 3. Cập nhật trạng thái tài khoản          |");
            System.out.println("| 4. Tìm kiếm tài khoản                     |");
            System.out.println("| 5. thoát                                  |");
            System.out.println("+-------------------------------------------+");
            int choice = 0;
            do{
                System.out.print("Chức năng: ");
                String input = sc.nextLine();
                if(!Pattern.matches("[123456]",input)){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép!!"));
                }else {
                    choice = Integer.parseInt(input);
                    break;
                }
            }while (true);
            switch (choice){
                case 1:
                    case1(sc);
                    break;
                case 2:
                    case2(sc);
                    break;
                case 3:
                    case3(sc);
                    break;
                case 4:
                    case4(sc);
                    break;
                case 5:
                    isExit = true;
                    break;
            }
        }while (!isExit);
    }
    public void case1(Scanner sc){
        offSet=0;
        isOutTable = false;
        do {
            accountList.clear();
            maxOffSet = iAccount.getlengthAccount();
            accountList.addAll(iAccount.getAccount(offSet));
            displayTable();
            bar(sc);
        }while (!isOutTable);
    }
    public void case2(Scanner sc){
        Account account = new Account();
        if (account.input(sc)){
            IAccount iAccount = new AccountImp();
            if (iAccount.createAccount(account)){
                System.out.println(colorGreen("Tạo tài khoản thành công"));
            }
        }
    }
    public void case3(Scanner sc){
        do {
            System.out.println("1. Nhập mã tài khoản đã biết");
            System.out.println("2. Chọn tài khoản từ bảng");
            System.out.println("0. Thoát");
            String input = sc.nextLine().trim();
            if (!Pattern.matches("[120]",input)){
                System.out.println(colorRed("Hãy chọn đúng chức năng cho phép!!!"));
            }else {
                if (Integer.parseInt(input)==0){
                    break;
                }else if (Integer.parseInt(input)==1){
                    if (updateStatus(sc)){
                        break;
                    }
                }else if (Integer.parseInt(input)==2){
                    updateBySelect(sc);
                    if (isUpdateOk){
                        break;
                    }
                }
            }
        }while (true);
    }
    public void case4(Scanner sc){
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        String input = "";
        do {
            System.out.println("Nhập tên tài khoản hay tên nhân viên để tìm kiếm");
            input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")) {
                break;
            } else {
                break;
            }
        }while (true);
        offSet=0;
        isOutTable = false;
        do {
            accountList.clear();
            maxOffSet = iAccount.getlengthSearch(input);
            accountList.addAll(iAccount.getAccountSearch(offSet,input));
            if (maxOffSet==0){
                System.out.println(colorRed("Không tìm thấy dữ liệu hiển thị"));
            }
            displayTable();
            if (maxOffSet==0){
                bar(sc);
            }else {
                barUpdate(sc);
            }
        }while (!isOutTable);

    }
    public void updateBySelect(Scanner sc){
        offSet=0;
        isOutTable = false;
        isUpdateOk = false;
        do {
            accountList.clear();
            maxOffSet = iAccount.getlengthAccount();
            accountList.addAll(iAccount.getAccount(offSet));
            displayTable();
            barUpdate(sc);
        }while (!isOutTable);
    }
    public void barUpdate(Scanner sc){
        String input = "";
        System.out.println("4. Cập nhật trạng thái");
        if (offSet>=10){
            System.out.println("6. Lùi đến trang cuối");
            System.out.println("7. Lùi");
        }
        if ((offSet+10)<maxOffSet){
            System.out.println("8. Tiến");
            System.out.println("9. Tiến đến trang cuối");
        }
        System.out.println("0. Thoát");
        do{
            input = sc.nextLine().trim();
            if (maxOffSet>10){
                if (offSet<10){
                    if (!Pattern.matches("[4890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==4){
                            break;
                        }else if (Integer.parseInt(input)==5){
                            break;
                        }else if (Integer.parseInt(input)==8){
                            offSet = offSet + 10;
                            break;
                        }
                        else if (Integer.parseInt(input)==9){
                            if (maxOffSet%10==0){
                                offSet = maxOffSet - 10;
                                break;
                            }else {
                                offSet = maxOffSet-(maxOffSet%10);
                                break;
                            }
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }else if((offSet+10)>maxOffSet){
                    if (!Pattern.matches("[4670]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==4){
                            break;
                        }else if (Integer.parseInt(input)==5){
                            break;
                        }else if (Integer.parseInt(input)==6){
                            offSet = 0;
                            break;
                        }else if (Integer.parseInt(input)==7){
                            offSet = offSet -10;
                            break;
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }else {
                    if (!Pattern.matches("[467890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==4){
                            break;
                        }else if (Integer.parseInt(input)==5){
                            break;
                        }else if (Integer.parseInt(input)==6){
                            offSet = 0;
                            break;
                        }else if (Integer.parseInt(input)==7){
                            offSet = offSet -10;
                            break;
                        }else if (Integer.parseInt(input)==8){
                            offSet = offSet + 10;
                            break;
                        }else if (Integer.parseInt(input)==9){
                            if (maxOffSet%10==0){
                                offSet = maxOffSet - 10;
                                break;
                            }else {
                                offSet = maxOffSet-(maxOffSet%10);
                                break;
                            }
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }
            }else {
                if (!Pattern.matches("[40]",input)){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                }else {
                    if (Integer.parseInt(input)==4){
                        break;
                    }else if (Integer.parseInt(input)==5){
                        break;
                    }else{
                        isOutTable = true;
                        break;
                    }
                }
            }
        }while (true);
        if (Integer.parseInt(input)==4){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập vị trí tài khoản cần cập nhật trạng thái (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>accountList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Account account = accountList.get(postision);
                        if(loginInfor.getId()== account.getId()){
                            System.out.println(colorRed("Không thể cập nhật chính mình!!!"));
                        }else if(!account.isPermission()){
                            System.out.println(colorRed("Không thể cập nhật tài khoản cùng cấp"));
                        }
                        else {
                            System.out.println("Tên sản phẩm đang cập nhật trạng thái: " + colorGreen(account.getUserName()));
                            System.out.println("Trạng thái hiện tại là: " + (account.isStatus() ? colorGreen("Hoạt động") : colorGreen("Khóa")));
                            System.out.println("Chuyển trạng thái");
                            System.out.println("1. Hoạt động");
                            System.out.println("2. Khóa");
                            do {
                                input = sc.nextLine().trim();
                                if (!Pattern.matches("[12]", input)) {
                                    System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                                } else {
                                    if (Integer.parseInt(input) == 1) {
                                        account.setStatus(true);
                                    } else {
                                        account.setStatus(false);
                                    }
                                    if (iAccount.updateStatus(account)) {
                                        System.out.println(colorGreen("Cập nhật thành công"));
                                    }
                                    isUpdateOk = true;
                                    break;
                                }
                            } while (true);
                            break;
                        }
                    }
                }
            }while (true);
        }
    }
    public boolean updateStatus(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã tài khoản");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(!Pattern.matches("\\d+",input)){
                    System.out.println(colorRed("Mã tài khoản chỉ bao gồm số!!"));
                }else {
                    Account account = iAccount.findAccout(Integer.parseInt(input));
                    if (account==null){
                        System.out.println(colorRed("Không tìm thấy tài khoản"));
                    }else if(loginInfor.getId()== account.getId()){
                        System.out.println(colorRed("Không thể cập nhật chính mình!!!"));
                    }else if(!account.isPermission()){
                        System.out.println(colorRed("Không thể cập nhật tài khoản cùng cấp"));
                    }
                    else {
                        System.out.println("Tên sản phẩm đang cập nhật trạng thái: "+colorGreen(account.getUserName()));
                        System.out.println("Trạng thái hiện tại là: "+(account.isStatus()?colorGreen("Hoạt động"):colorGreen("Khóa")));
                        System.out.println("Chuyển trạng thái");
                        System.out.println("1. Hoạt động");
                        System.out.println("2. Khóa");
                        do {
                            input = sc.nextLine().trim();
                            if (!Pattern.matches("[12]",input)){
                                System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                            }else {
                                if (Integer.parseInt(input)==1){
                                    account.setStatus(true);
                                }else {
                                    account.setStatus(false);
                                }
                                if (iAccount.updateStatus(account)){
                                    System.out.println(colorGreen("Cập nhật thành công"));
                                }
                                break;
                            }
                        }while (true);
                        break;
                    }
                }
            }
        }while (true);
        return isReturn;
    }
    public void displayTable(){
        int noLength = Math.max(String.valueOf(offSet).length(), 2);
        int accIdLength = 6;
        int nameLength = 9;
        int passwordLength = 8;
        for (Account account : accountList) {
            if (account.getUserName().length() > nameLength) nameLength = account.getUserName().length();
            if (account.getPassWord().length() > passwordLength) passwordLength = account.getPassWord().length();
            if (String.valueOf(account.getId()).length() > accIdLength) {
                accIdLength = String.valueOf(account.getId()).length();
            }
        }
        for(int i=0;i<accountList.size();i++){
            if (i==0){
                System.out.println(dotPalce(noLength)+dotPalce(accIdLength)+dotPalce(nameLength)+dotPalce(passwordLength)+dotPalce(6)+dotPalce(10)+dotPalce(6)+"+");
                System.out.println(centerPlace(noLength,"No")+centerPlace(accIdLength,"Acc Id")+centerPlace(nameLength,"User Name")+centerPlace(passwordLength,"Password")+centerPlace(6,"Emp Id")+centerPlace(10,"Permission")+centerPlace(6,"Status")+"|");
                System.out.println(dotPalce(noLength)+dotPalce(accIdLength)+dotPalce(nameLength)+dotPalce(passwordLength)+dotPalce(6)+dotPalce(10)+dotPalce(6)+"+");
            }
            String insertTextPer = "";
            if(accountList.get(i).isPermission()){
                insertTextPer = "User";
            }else {
                insertTextPer = "admin";
            }
            String insertTextStatus ="";
            if (accountList.get(i).isStatus()){
                insertTextStatus = " Active";
            }else {
                insertTextStatus = "Block";
            }
            System.out.println(centerPlace(noLength,String.valueOf(i+offSet+1))+centerPlace(accIdLength,String.valueOf(accountList.get(i).getId()))+centerPlace(nameLength,accountList.get(i).getUserName())+centerPlace(passwordLength,accountList.get(i).getPassWord())+centerPlace(6,accountList.get(i).getEmpId())+centerPlace(10,insertTextPer)+centerPlace(6,insertTextStatus)+"|");
            if (i==accountList.size()-1){
                System.out.println(dotPalce(noLength)+dotPalce(accIdLength)+dotPalce(nameLength)+dotPalce(passwordLength)+dotPalce(6)+dotPalce(10)+dotPalce(6)+"+");
            }
        }
    }
    public void bar(Scanner sc){
        if (offSet>=10){
            System.out.println("6. Lùi đến trang cuối");
            System.out.println("7. Lùi");
        }
        if ((offSet+10)<maxOffSet){
            System.out.println("8. Tiến");
            System.out.println("9. Tiến đến trang cuối");
        }
        System.out.println("0. Thoát");
        do{
            String input = sc.nextLine().trim();
            if (maxOffSet>10){
                if (offSet<10){
                    if (!Pattern.matches("[890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==8){
                            offSet = offSet + 10;
                            break;
                        }
                        else if (Integer.parseInt(input)==9){
                            if (maxOffSet%10==0){
                                offSet = maxOffSet - 10;
                                break;
                            }else {
                                offSet = maxOffSet-(maxOffSet%10);
                                break;
                            }
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }else if((offSet+10)>maxOffSet){
                    if (!Pattern.matches("[670]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==6){
                            offSet = 0;
                            break;
                        }else if (Integer.parseInt(input)==7){
                            offSet = offSet -10;
                            break;
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }else {
                    if (!Pattern.matches("[67890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==6){
                            offSet = 0;
                            break;
                        }else if (Integer.parseInt(input)==7){
                            offSet = offSet -10;
                            break;
                        }else if (Integer.parseInt(input)==8){
                            offSet = offSet + 10;
                            break;
                        }else if (Integer.parseInt(input)==9){
                            if (maxOffSet%10==0){
                                offSet = maxOffSet - 10;
                                break;
                            }else {
                                offSet = maxOffSet-(maxOffSet%10);
                                break;
                            }
                        }else {
                            isOutTable = true;
                            break;
                        }
                    }
                }
            }else {
                if (!Pattern.matches("[0]",input)){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                }else {
                    isOutTable = true;
                    break;
                }
            }
        }while (true);
    }
    public String colorRed(String text){
        return "\u001B[31m"+text+"\u001B[0m";
    }
    public String colorGreen(String text){
        return "\u001B[32m"+text+"\u001B[0m";
    }
    public String centerPlace(int space,String text){
        space = space/2;
        String outPut = "| ";
        if(text.length()%2==0){
            outPut = outPut + " ";
        }
        for (int i=0;i<(space-text.length()/2);i++){
            outPut = outPut.concat(" ");
        }
        outPut = outPut + text;
        for (int i=0;i<(space-text.length()/2);i++){
            outPut = outPut.concat(" ");
        }
        outPut = outPut.concat(" ");
        return outPut;
    }
    public String dotPalce(int space){
        String outPut = "+-";
        if (space%2==0){
            outPut = outPut.concat("-");
        }
        for(int i=0;i<space;i++){
            outPut = outPut.concat("-");
        }
        outPut = outPut+"-";
        return outPut;
    }
}
