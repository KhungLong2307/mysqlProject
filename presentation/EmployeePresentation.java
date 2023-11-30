package presentation;

import business.EmployeeImp;
import business.IEmployee;
import entity.Employee;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeePresentation {
    static List<Employee> employeeList = new ArrayList<>();
    static int offSet = 0;
    static int maxOffSet = 0;
    static boolean isOutTable = false;
    static boolean isUpdateOk = false;
    public void menu(Scanner sc){
        boolean isExit = false;
        do{
            System.out.println("+--------------------------------------------+");
            System.out.println("|           EMPLOYEE MANAGEMENT              |");
            System.out.println("+--------------------------------------------+");
            System.out.println("| 1. Danh sách nhân viên                     |");
            System.out.println("| 2. Thêm mới nhân viên                      |");
            System.out.println("| 3. Cập nhật thông tin nhân viên            |");
            System.out.println("| 4. Cập nhật trạng thái nhân viên           |");
            System.out.println("| 5. tìm kiếm nhân viên                      |");
            System.out.println("| 6. Thoát                                   |");
            System.out.println("+--------------------------------------------+");
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
            switch(choice){
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
                    case5(sc);
                    break;
                case 6:
                    isExit = true;
                    break;
            }
        }while (!isExit);
    }
    public void case1(Scanner sc){
        IEmployee iEmployee = new EmployeeImp();
        offSet = 0;
        isOutTable = false;
        do {
            employeeList.clear();
            maxOffSet = iEmployee.getLengthEmployee();
            employeeList.addAll(iEmployee.getEmployee(offSet));
            displayTable();
            bar(sc);
        }while (!isOutTable);
    }
    public void case2(Scanner sc){
        Employee employee = new Employee();
        if (employee.input(sc)){
            IEmployee iEmployee = new EmployeeImp();
            if(iEmployee.createEmployee(employee)) {
                System.out.println(colorGreen("Thêm mới nhân viên thành công"));
            }
        }
    }
    public void case3(Scanner sc){
        do {
            System.out.println("1. Nhập mã mã nhân viên đã biết");
            System.out.println("2. Chọn mã nhân viên từ bảng");
            System.out.println("0. Thoát");
            String input = sc.nextLine().trim();
            if (!Pattern.matches("[120]",input)){
                System.out.println(colorRed("Hãy chọn đúng chức năng cho phép!!!"));
            }else {
                if (Integer.parseInt(input)==0){
                    break;
                }else if (Integer.parseInt(input)==1){
                    if (updateById(sc)){
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
        do {
            System.out.println("1. Nhập mã mã nhân viên đã biết");
            System.out.println("2. Chọn mã nhân viên từ bảng");
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
    public void case5(Scanner sc){
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        String input = "";
        do {
            System.out.println("Hãy nhập đặc điểm nhân viên cần tìm kiếm");
            input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                break;
            }else {
                if(input.length()>100){
                    System.out.println(colorRed("Nhập liệu quá dài!!!"));
                }else {
                    break;
                }
            }
        }while (true);
        IEmployee iEmployee = new EmployeeImp();
        offSet = 0;
        isOutTable = false;
        do {
            employeeList.clear();
            maxOffSet = iEmployee.getLengthSearch(input);
            employeeList.addAll(iEmployee.getEmployeeAfterSearch(offSet,input));
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
    public boolean updateStatus(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã nhân viên");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(input.length()!=5){
                    System.out.println(colorRed("Mã nhân viên phải là 5 ký tự !!"));
                }else {
                    IEmployee iEmployee = new EmployeeImp();
                    Employee employee = iEmployee.updateById(input);
                    if (employee==null){
                        System.out.println(colorRed("Không tìm thấy nhân viên"));
                    }else {
                        System.out.println("Tên nhân viên đang cập nhật trạng thái: "+colorGreen(employee.getName()));
                        String insert = "";
                        if (employee.getStatus()==0){
                            insert = "Hoạt động";
                        }else if (employee.getStatus()==1){
                            insert = "Nghỉ chế độ";
                        }else {
                            insert = "Nghỉ việc";
                        }
                        System.out.println("Trạng thái hiện tại là: "+colorGreen(insert));
                        System.out.println("Chuyển trạng thái");
                        System.out.println("1. Hoạt động");
                        System.out.println("2. Nghỉ chế độ");
                        System.out.println("3. Nghỉ việc");
                        do {
                            input = sc.nextLine().trim();
                            if (!Pattern.matches("[123]",input)){
                                System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                            }else {
                                employee.setStatus(Integer.parseInt(input)-1);
                                if (iEmployee.updateEmployee(employee)){
                                    System.out.println(colorGreen("Cập nhật thành công"));
                                    isUpdateOk = true;
                                    isOutTable = true;
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
    public boolean updateById(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã nhân viên");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(input.length()!=5){
                    System.out.println(colorRed("Mã nhân viên phải là 5 ký tự !!"));
                }else {
                    IEmployee iEmployee = new EmployeeImp();
                    Employee employee = iEmployee.updateById(input);
                    if (employee==null){
                        System.out.println(colorRed("Không tìm thấy nhân viên"));
                    }else {
                        if (employee.update(sc)){
                            if (iEmployee.updateEmployee(employee)){
                                System.out.println(colorGreen("Cập nhật thành công"));
                            }
                        }else {
                            System.out.println(colorRed("Bạn đã thoát khỏi cập nhật!!"));
                            isReturn = false;
                        }
                        break;
                    }
                }
            }
        }while (true);
        return isReturn;
    }
    public void updateBySelect(Scanner sc){
        IEmployee iEmployee = new EmployeeImp();
        offSet = 0;
        isOutTable = false;
        isUpdateOk = false;
        do {
            employeeList.clear();
            maxOffSet = iEmployee.getLengthEmployee();
            employeeList.addAll(iEmployee.getEmployee(offSet));
            displayTable();
            barUpdate(sc);
        }while (!isOutTable);
    }
    public void displayTable(){
        int noLength = 2;
        if(String.valueOf(offSet).length()>2){
            noLength = String.valueOf(offSet).length();
        }
        int nameLength = 4;
        int emailLength = 5;
        int phoneLength = 5;
        int addressLength = 7;
        for(int y=0;y<employeeList.size();y++){
            if(employeeList.get(y).getName().length()>nameLength){
                nameLength = employeeList.get(y).getName().length();
            }
            if(employeeList.get(y).getEmail().length()>emailLength){
                emailLength = employeeList.get(y).getEmail().length();
            }
            if(employeeList.get(y).getPhone().length()>phoneLength){
                phoneLength = employeeList.get(y).getPhone().length();
            }
            if(employeeList.get(y).getAdress().length()>addressLength){
                addressLength = employeeList.get(y).getAdress().length();
            }
        }
        for(int i=0;i<employeeList.size();i++){
            if (i==0){
                System.out.println(dotPalce(noLength)+dotPalce(5)+dotPalce(nameLength)+dotPalce(10)+dotPalce(emailLength)+dotPalce(phoneLength)+dotPalce(addressLength)+dotPalce(11)+"+");
                System.out.println(centerPlace(noLength,"No")+centerPlace(5,"Id")+centerPlace(nameLength,"Name")+centerPlace(10,"Birth Day")+centerPlace(emailLength,"Email")+centerPlace(phoneLength,"Phone")+centerPlace(addressLength,"Address")+centerPlace(11,"Status")+"|");
                System.out.println(dotPalce(noLength)+dotPalce(5)+dotPalce(nameLength)+dotPalce(10)+dotPalce(emailLength)+dotPalce(phoneLength)+dotPalce(addressLength)+dotPalce(11)+"+");
            }
            String insertText = "";
            if(employeeList.get(i).getStatus()==0){
                insertText = "hoạt động ";
            }else if (employeeList.get(i).getStatus()==1){
                insertText = "Nghỉ chế độ";
            }else {
                insertText = "Nghỉ việc ";
            }
            System.out.println(centerPlace(noLength,String.valueOf(i+offSet+1))+centerPlace(5,employeeList.get(i).getId())+centerPlace(nameLength,employeeList.get(i).getName())+centerPlace(10,employeeList.get(i).getBirthDay())+centerPlace(emailLength,employeeList.get(i).getEmail())+centerPlace(phoneLength,employeeList.get(i).getPhone())+centerPlace(addressLength,employeeList.get(i).getAdress())+centerPlace(11,insertText)+"|");
            if (i==employeeList.size()-1){
                System.out.println(dotPalce(noLength)+dotPalce(5)+dotPalce(nameLength)+dotPalce(10)+dotPalce(emailLength)+dotPalce(phoneLength)+dotPalce(addressLength)+dotPalce(11)+"+");
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
    public void barUpdate(Scanner sc){
        String input = "";
        System.out.println("4. Cập nhật trạng thái");
        System.out.println("5. Chọn vị trí cập nhật");
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
                    if (!Pattern.matches("[45890]",input)){
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
                    if (!Pattern.matches("[45670]",input)){
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
                    if (!Pattern.matches("[4567890]",input)){
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
                if (!Pattern.matches("[450]",input)){
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
                System.out.println("Hãy nhập vị trí nhân viên cần cập nhật trạng thái (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>employeeList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Employee employee = employeeList.get(postision);
                        IEmployee iEmployee = new EmployeeImp();
                        System.out.println("Tên nhân viên đang cập nhật trạng thái: "+colorGreen(employee.getName()));
                        String insert = "";
                        if (employee.getStatus()==0){
                            insert = "Hoạt động";
                        }else if (employee.getStatus()==1){
                            insert = "Nghỉ chế độ";
                        }else {
                            insert = "Nghỉ việc";
                        }
                        System.out.println("Trạng thái hiện tại là: "+colorGreen(insert));
                        System.out.println("Chuyển trạng thái");
                        System.out.println("1. Hoạt động");
                        System.out.println("2. Nghỉ chế độ");
                        System.out.println("3. Nghỉ việc");
                        do {
                            input = sc.nextLine().trim();
                            if (!Pattern.matches("[123]",input)){
                                System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                            }else {
                                employee.setStatus(Integer.parseInt(input)-1);
                                if (iEmployee.updateEmployee(employee)){
                                    System.out.println(colorGreen("Cập nhật thành công"));
                                    isUpdateOk = true;
                                    isOutTable = true;
                                }
                                break;
                            }
                        }while (true);
                        break;
                    }
                }
            }while (true);
        }
        if (Integer.parseInt(input)==5){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập vị trí sản phẩm cần cập nhật (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>employeeList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Employee employee = employeeList.get(postision);
                        IEmployee iEmployee = new EmployeeImp();
                        if (employee.update(sc)){
                            if (iEmployee.updateEmployee(employee)){
                                System.out.println(colorGreen("Cập nhật thành công"));
                                isOutTable = true;
                                isUpdateOk = true;
                            }
                        }else {
                            System.out.println(colorRed("Bạn đã thoát khỏi cập nhật!!"));
                            isOutTable = false;
                        }
                        break;
                    }
                }
            }while (true);
        }
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
