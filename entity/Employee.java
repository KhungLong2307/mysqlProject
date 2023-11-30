package entity;

import business.EmployeeImp;
import business.IEmployee;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee {
    private String id = "";
    private String name = "";
    private String birthDay = "";
    private String email = "";
    private String phone = "";
    private String adress = "";
    private int status = 0;

    public Employee() {
    }

    public Employee(String id, String name, String birthDay, String email, String phone, String adress, int status) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public boolean input(Scanner sc){
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        boolean isExit = false;
        boolean isReturn = true;
        while(!isExit){
            System.out.println("Hãy nhập mã nhân viên gồm 5 ký tự");
            id = sc.nextLine();
            if (id.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (id.length()!=5){
                    System.out.println(colorRed("Mã nhân viên phải gồm 5 ký tự"));
                }else {
                    IEmployee iEmployee = new EmployeeImp();
                    if(!iEmployee.isCheckId(id)){
                        System.out.println(colorRed("Mã nhân viên trùng lặp!!!"));
                    }else {
                        break;
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập tên nhân viên!!");
            name = sc.nextLine();
            if(name.toLowerCase().equals("exit")){
                isExit =true;
                break;
            }else {
                if (name.length()<=0||name.length()>100){
                    System.out.println(colorRed("Tên nhân viên quá dài hoặc đang bị bỏ trống!!!"));
                }else {
                    break;
                }
            }
        }
        while (!isExit){
            System.out.println("Hãy nhập ngày tháng năm sinh");
            System.out.println("VD: 1999-01-01");
            birthDay = sc.nextLine();
            if (birthDay.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                try{
                    LocalDate localDate = LocalDate.parse(birthDay);
                    break;
                }catch (DateTimeParseException e){
                    System.out.println(colorRed("Ngày tháng năm sinh không hợp lệ!!! Vui lòng nhập lại!!!"));
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập địa chỉ Email");
            System.out.println("VD: gmail@gmail.com");
            email = sc.nextLine();
            if(email.toLowerCase().equals("exit")){
                isExit =true;
                break;
            }else {
                if(email.length()<=0||email.length()>100){
                    System.out.println(colorRed("Email quá dài hoặc bị bỏ trống!!! Vui lòng nhập lại!!!"));
                }else {
                    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                    if(!Pattern.matches(regexPattern,email)){
                        System.out.println(colorRed("Email không hợp lệ!!! Vui lòng nhập lại!!!"));
                    }else {
                        IEmployee iEmployee = new EmployeeImp();
                        if(!iEmployee.isCheckEmail(email)){
                            System.out.println(colorRed("Email trùng lặp!!! Vui lòng nhập Email khác!!!"));
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        while (!isExit){
            System.out.println("Hãy nhập số điện thoại");
            System.out.println("VD: 0123456789");
            phone = sc.nextLine();
            if(phone.toLowerCase().equals("exit")){
                isExit =true;
                break;
            }else {
                if(!Pattern.matches("\\d+",phone)){
                    System.out.println(colorRed("Số điện thoại không hợp lê!!! Vui lòng nhập lại!!!"));
                }else if(phone.length()>100){
                    System.out.println(colorRed("Số điện thoại quá dài!!! Vui lòng nhập lại!!!"));
                }
                else {
                    break;
                }
            }
        }
        while (!isExit){
            System.out.println("Hãy nhập địa chỉ");
            adress = sc.nextLine();
            if(adress.toLowerCase().equals("exit")){
                isExit =true;
                break;
            }else {
                if(adress.length()<=0||adress.length()>200){
                    System.out.println(colorRed("Địa chỉ không hợp lệ!!! Vui lòng nhập lại!!!"));
                }else {
                    break;
                }
            }
        }
        while (!isExit){
            System.out.println("Hãy chọn trạng thái nhân viên!!!");
            System.out.println("1. Hoạt động");
            System.out.println("2. Nghỉ chế độ");
            System.out.println("3. Nghỉ việc");
            String input = sc.nextLine();
            if(input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if(!Pattern.matches("[123]",input)){
                    System.out.println(colorRed("Hãy chọn đúng trạng thái nhân viên!!!"));
                }else {
                    if(Integer.parseInt(input)==1){
                        status = 0;
                    }else if (Integer.parseInt(input)==2){
                        status = 1;
                    }else {
                        status = 2;
                    }
                    break;
                }
            }
        }
        if(isExit){
            isReturn = false;
        }
        return isReturn;
    }
    public boolean update(Scanner sc){
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        System.out.println(colorGreen("Bỏ trống nếu không muốn cập nhật dữ liệu đó"));
        boolean isExit =false;
        boolean isReturn = true;
        while(!isExit){
            System.out.println("Hãy nhập tên nhân viên");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if(input.length()<=0){
                    break;
                }else if (input.length()>100) {
                    System.out.println(colorRed("Tên nhân viên quá dài!!!"));
                }else {
                    name = input;
                    break;

                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập ngày tháng năm sinh");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0){
                    break;
                }else{
                    try{
                        LocalDate localDate = LocalDate.parse(input);
                        birthDay = input;
                        break;
                    }catch (DateTimeParseException e){
                        System.out.println(colorRed("Ngày tháng năm sinh không hợp lệ!!! Vui lòng nhập lại!!!"));
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập địa chỉ email");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0){
                    break;
                }else {
                    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                    if(!Pattern.matches(regexPattern,input)){
                        System.out.println(colorRed("Email không hợp lệ!!! Vui lòng nhập lại!!!"));
                    }else {
                        IEmployee iEmployee = new EmployeeImp();
                        if(!iEmployee.isCheckEmail(input)){
                            System.out.println(colorRed("Email trùng lặp hoặc giống với email trước đó"));
                        }else {
                            email = input;
                            break;
                        }
                    }
                }
            }
        }
        while(!isExit){
            System.out.println("Hãy nhập địa chỉ số điện thoại");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0){
                    break;
                }else {
                    if (input.length()>100){
                        System.out.println(colorRed("Số điện thoại quá dài!!!"));
                    }else if (!Pattern.matches("\\d+",input)){
                        System.out.println(colorRed("Số điện thoại không hợp lê!!!"));
                    }else {
                        phone = input;
                        break;
                    }
                }
            }
        }
        while (!isExit){
            System.out.println("Hãy nhập địa chỉ");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.length()<=0){
                    break;
                }else {
                    if (input.length()>200){
                        System.out.println(colorRed("Địa chỉ quá dài!!!"));
                    }else {
                        adress = input;
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
