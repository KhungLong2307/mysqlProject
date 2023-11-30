package presentation;

import business.IReport;
import business.Reportimp;
import entity.Report;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReportPresentation {
    public void menu(Scanner sc){
        boolean isExit = false;
        do{
            System.out.println("+------------------------------------------------------------------+");
            System.out.println("|                      REPORT MANAGEMENT                           |");
            System.out.println("+------------------------------------------------------------------+");
            System.out.println("| 1. Thống kê chi phí theo ngày, tháng, năm                        |");
            System.out.println("| 2. Thống kê chi phí theo khoảng thời gian                        |");
            System.out.println("| 3. Thống kê doanh thu theo ngày, tháng, năm                      |");
            System.out.println("| 4. Thống kê doanh thu theo khoảng thời gian                      |");
            System.out.println("| 5. Thống kê số nhân viên theo từng trạng thái                    |");
            System.out.println("| 6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian      |");
            System.out.println("| 7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian         |");
            System.out.println("| 8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian      |");
            System.out.println("| 9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian         |");
            System.out.println("| 10. Thoát                                                        |");
            System.out.println("+------------------------------------------------------------------+");
            int choice = 0;
            do{
                System.out.print("Chức năng: ");
                String input = sc.nextLine();
                if(!Pattern.matches("\\d+",input)||Integer.parseInt(input)<=0||Integer.parseInt(input)>10){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép!!"));
                }else {
                    choice = Integer.parseInt(input);
                    break;
                }
            }while (true);
            switch (choice){
                case 1:
                    displayTable(sc,true,0);
                    break;
                case 2:
                    displayTable(sc,true,1);
                    break;
                case 3:
                    displayTable(sc,false,0);
                    break;
                case 4:
                    displayTable(sc,false,1);
                    break;
                case 5:
                    displayEmployeeTable();
                    break;
                case 6:
                    displayTable(sc,true,3);
                    break;
                case 7:
                    displayTable(sc,true,2);
                    break;
                case 8:
                    displayTable(sc,false,3);
                    break;
                case 9:
                    displayTable(sc,false,2);
                    break;
                case 10:
                    isExit = true;
                    break;
            }
        }while (!isExit);
    }
    public void displayTable(Scanner sc,Boolean type,int tool){
        IReport iReport = new Reportimp();
        int offSet = 0;
        boolean isExit = false;
        int maxOffSet = 0;
        List<Report> reportList = null;
        String date1 = "";
        String date2 = "";
        if (tool==0){
            reportList = iReport.getReport(offSet,type);
            maxOffSet = iReport.getLengthReport(type);
        }else if (tool>0){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập khoảng thời gian cần thông kê");
                System.out.println("VD: Từ ngày 1999-01-01 đến 1999-01-31");
                System.out.println(colorRed("Lưu ý; Có tháng 30,31 ngày"));
                LocalDate localDate1 = null;
                LocalDate localDate2 = null;
                do {
                    System.out.print("Từ ngày: ");
                    date1 = sc.nextLine().trim();
                    if (date1.toLowerCase().equals("exit")){
                        isExit = true;
                        break;
                    }else {
                        try{
                            localDate1 = LocalDate.parse(date1);
                            break;
                        }catch (DateTimeParseException e){
                            System.out.println(colorRed("Thời gian nhập không hợp lệ!!!"));
                        }
                    }
                }while (true);
                while (!date1.toLowerCase().equals("exit")){
                    System.out.print("Đến ngày: ");
                    date2 = sc.nextLine().trim();
                    if (date1.toLowerCase().equals("exit")){
                        isExit = true;
                        break;
                    }else {
                        try{
                            localDate2 = LocalDate.parse(date2);
                            break;
                        }catch (DateTimeParseException e){
                            System.out.println(colorRed("Thời gian nhập không hợp lệ!!!"));
                        }
                    }
                }
                if (localDate1.isBefore(localDate2)){
                    break;
                }else {
                    System.out.println(colorRed("Nhập liệu ngày không hợp lệ!!!"));
                }
            }while (true);
            if (!isExit&&tool==1){
                reportList = iReport.getReportBetween(offSet,type,date1,date2);
                maxOffSet = iReport.getLengthReportBetween(type,date1,date2);
            }else if (!isExit&&tool==2){
                reportList = iReport.getReportBetweenMaxest(offSet,type,date1,date2);
                maxOffSet = 1;
            }else {
                reportList = iReport.getReportBetweenMinest(offSet,type,date1,date2);
                maxOffSet = 1;
            }
        }
        if (maxOffSet==0){
            System.out.println(colorRed("Không có dữ liệu để hiển thị"));
        }else {
            do{
                int noLength = 2;
                if(String.valueOf(offSet).length()>2){
                    noLength = String.valueOf(offSet).length();
                }
                int billIdLength = 7;
                int productNameLength = 12;
                int quantityLength = 8;
                int priceLength = 5;
                int tolatLength = 5;
                int dateLength = 10;
                for(int y=0;y<reportList.size();y++){
                    if (String.valueOf(reportList.get(y).getBillId()).length()>billIdLength){
                        billIdLength = String.valueOf(reportList.get(y).getBillId()).length();
                    }
                    if (reportList.get(y).getProductName().length()>productNameLength){
                        productNameLength = reportList.get(y).getProductName().length();
                    }
                    if (String.valueOf(reportList.get(y).getQuantity()).length()>quantityLength){
                        quantityLength = String.valueOf(reportList.get(y).getQuantity()).length();
                    }
                    if (String.valueOf(reportList.get(y).getPrice()).length()>priceLength){
                        priceLength = String.valueOf(reportList.get(y).getPrice()).length();
                    }
                    if (reportList.get(y).tolat().length()>tolatLength){
                        tolatLength = reportList.get(y).tolat().length();
                    }
                }
                for (int i=0;i<reportList.size();i++){
                    if (i==0){
                        System.out.println(dotPalce(noLength)+dotPalce(billIdLength)+dotPalce(productNameLength)+dotPalce(quantityLength)+dotPalce(priceLength)+dotPalce(tolatLength)+dotPalce(dateLength)+"+");
                        System.out.println(centerPlace(noLength,"No")+centerPlace(billIdLength,"Bill Id")+centerPlace(productNameLength,"Product Name")+centerPlace(quantityLength,"Quantity")+centerPlace(priceLength,"Price")+centerPlace(tolatLength,"Tolat")+centerPlace(dateLength,"Auth_date")+"|");
                        System.out.println(dotPalce(noLength)+dotPalce(billIdLength)+dotPalce(productNameLength)+dotPalce(quantityLength)+dotPalce(priceLength)+dotPalce(tolatLength)+dotPalce(dateLength)+"+");
                    }
                    System.out.println(centerPlace(noLength,String.valueOf(i+offSet+1))+centerPlace(billIdLength,String.valueOf(reportList.get(i).getBillId()))+centerPlace(productNameLength,reportList.get(i).getProductName())+centerPlace(quantityLength,String.valueOf(reportList.get(i).getQuantity()))+centerPlace(priceLength,String.valueOf(reportList.get(i).getPrice()))+centerPlace(tolatLength,reportList.get(i).tolat())+centerPlace(dateLength,reportList.get(i).getDate())+"|");
                    if (i==(reportList.size()-1)){
                        System.out.println(dotPalce(noLength)+dotPalce(billIdLength)+dotPalce(productNameLength)+dotPalce(quantityLength)+dotPalce(priceLength)+dotPalce(tolatLength)+dotPalce(dateLength)+"+");
                    }
                }

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
                                    isExit = true;
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
                                    isExit = true;
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
                                    isExit = true;
                                    break;
                                }
                            }
                        }
                    }else {
                        if (!Pattern.matches("[0]",input)){
                            System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                        }else {
                            isExit = true;
                            break;
                        }
                    }
                }while (true);
            }while (!isExit);
        }
    }
    public void displayEmployeeTable(){
        IReport iReport = new Reportimp();
        int[] num = iReport.getReportEmployee();
        int status0 = 9;
        int status1 = 11;
        int status2 = 9;
        System.out.println(dotPalce(status0)+dotPalce(status1)+dotPalce(status2)+"+");
        System.out.println(centerPlace(status0,"Hoạt Động")+centerPlace(status1,"Nghỉ Chế Độ")+centerPlace(status2,"Nghỉ Việc")+"|");
        System.out.println(dotPalce(status0)+dotPalce(status1)+dotPalce(status2)+"+");
        System.out.println(centerPlace(status0,String.valueOf(num[0]))+centerPlace(status1,String.valueOf(num[1]))+centerPlace(status2,String.valueOf(num[2]))+"|");
        System.out.println(dotPalce(status0)+dotPalce(status1)+dotPalce(status2)+"+");
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
}
