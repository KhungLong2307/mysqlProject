package presentation;

import business.BillDetailImp;
import business.BillImp;
import business.IBill;
import business.IBillDetail;
import entity.Account;
import entity.Bill;
import entity.BillDetail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserManagement {
    static List<Bill> billList = new ArrayList<>();
    static List<BillDetail> billDetailList = new ArrayList<>();
    static IBill iBill = new BillImp();
    static IBillDetail iBillDetail = new BillDetailImp();
    static int offSet = 0;
    static int maxOffSet = 0;
    static boolean isOutTable = false;
    static boolean isUpdateOk = false;
    static Account loginInfor = null;
    static boolean isType = false;
    static boolean isContinue = false;
    static int billId = -1;
    static LocalDate localDate = LocalDate.now();
    public void menu(Scanner sc, Account login){
        boolean isExit = false;
        loginInfor = login;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|             USER MANAGEMENT               |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Danh sách phiếu nhập                   |");
            System.out.println("| 2. Tạo phiếu nhập                         |");
            System.out.println("| 3. Cập nhật thông tin phiếu nhập          |");
            System.out.println("| 4. Tìm kiếm phiếu nhập                    |");
            System.out.println("| 5. Danh sách phiếu xuất                   |");
            System.out.println("| 6. Tạo phiếu xuất                         |");
            System.out.println("| 7. Cập nhật thông tin phiếu xuất          |");
            System.out.println("| 8. Tìm kiếm phiếu xuất                    |");
            System.out.println("| 9. thoát                                  |");
            System.out.println("+-------------------------------------------+");
            int choice = 0;
            do{
                System.out.print("Chức năng: ");
                String input = sc.nextLine();
                if(!Pattern.matches("\\d+",input)||Integer.parseInt(input)<=0||Integer.parseInt(input)>9){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép!!"));
                }else {
                    choice = Integer.parseInt(input);
                    break;
                }
            }while (true);
            switch (choice){
                case 1:
                    isType = true;
                    case1(sc);
                    break;
                case 2:
                    isType = true;
                    case2(sc);
                    break;
                case 3:
                    isType = true;
                    case3(sc);
                    break;
                case 4:
                    isType = true;
                    case4(sc);
                    break;
                case 5:
                    isType = false;
                    case1(sc);
                    break;
                case 6:
                    isType = false;
                    case2(sc);
                    break;
                case 7:
                    isType = false;
                    case3(sc);
                    break;
                case 8:
                    isType = false;
                    case4(sc);
                    break;
                case 9:
                    isExit = true;
                    break;
            }
        }while (!isExit);
    }
    public void case1(Scanner sc){
        offSet = 0;
        isOutTable = false;
        do {
            billList.clear();
            maxOffSet = iBill.getBillByStatuslength(loginInfor.getEmpId(),isType,"");
            billList.addAll(iBill.getBillByStatus(offSet,loginInfor.getEmpId(),isType,""));
            displayTable();
            bar(sc);
        }while (!isOutTable);
    }
    public void case2(Scanner sc){
        Bill bill = new Bill();
        bill.setType(isType);
        bill.setIdCreated(loginInfor.getEmpId());
        if (bill.input(sc)){
            iBill.createBill(bill);
            System.out.println(colorGreen("Thêm mới thành công"));
            bill = iBill.findBill(loginInfor.getEmpId(),-1);
            do {
                System.out.println("Bạn có muốn nhập phiếu chi tiết không");
                System.out.println("1. Có");
                System.out.println("2. Không");
                String input = sc.nextLine().trim();
                if (!Pattern.matches("[12]",input)){
                    System.out.println(colorRed("Hãy chọn 1 hoặc 2!!!"));
                }else {
                    if (Integer.parseInt(input)==1){
                        BillDetail billDetail = new BillDetail();
                        billDetail.setType(isType);
                        billDetail.setBillId(bill.getId());
                        if (billDetail.input(sc)){
                            IBillDetail iBillDetail1 = new BillDetailImp();
                            iBillDetail1.createBillDetail(billDetail);
                            System.out.println(colorGreen("Đã tạo phiếu chi tiết"));
                        }
                    }else {
                        break;
                    }
                }
            }while (true);
        }
    }
    public void case3(Scanner sc){
        isContinue = false;
        do {
            System.out.println("1. Nhập mã bill đã biết");
            System.out.println("2. Chọn vị trí bill từ bảng");
            System.out.println("0. Thoát");
            String input = sc.nextLine().trim();
            if (!Pattern.matches("[120]",input)){
                System.out.println(colorRed("Hãy chọn đúng chức năng cho phép!!!"));
            }else {
                if (Integer.parseInt(input)==0){
                    break;
                }else if (Integer.parseInt(input)==1){
                    if (updateById(sc)){
                        isContinue = true;
                        break;
                    }
                }else if (Integer.parseInt(input)==2){
                    updateBySelect(sc);
                    if (isUpdateOk){
                        isContinue = true;
                        break;
                    }
                }
            }
        }while (true);
        if (isContinue){
            do {
                System.out.println("Bạn có muốn cập nhật luôn phiếu chi tiết không");
                System.out.println("1. Có");
                System.out.println("2. Không");
                String input = sc.nextLine().trim();
                if (!Pattern.matches("[12]",input)){
                    System.out.println(colorRed("Hãy chọn 1 hoặc 2!!!"));
                }else {
                    if (Integer.parseInt(input)==1){
                        updateBillDetail(sc);
                        break;
                    }else {
                        break;
                    }
                }
            }while (true);
        }
    }
    public void case4(Scanner sc){
        String input ="";
        System.out.println(colorGreen("Có thẻ nhập exit đẻ thoát!!!"));
        do {
            System.out.println("Hãy nhập từ khóa tìm kiếm!!!");
            input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                break;
            }else {
                offSet = 0;
                isOutTable = false;
                do {
                    billList.clear();
                    maxOffSet = iBill.getBillByStatuslength(loginInfor.getEmpId(),isType,input);
                    if (maxOffSet==0){
                        System.out.println(colorRed("Không tìm thấy dữ liệu"));
                        bar(sc);
                    }else {
                        billList.addAll(iBill.getBillByStatus(offSet,loginInfor.getEmpId(),isType,input));
                        displayTable();
                        barUpdate(sc);
                    }
                }while (!isOutTable);
                break;
            }
        }while (true);
    }
    public void displayTable(){
        int noLength = Math.max(String.valueOf(offSet).length(),2);
        int idLength = 2;
        for (Bill bill : billList) {
            if (String.valueOf(bill.getId()).length() > idLength) {
                idLength = String.valueOf(bill.getId()).length();
            }
        }
        for (int i=0;i<billList.size();i++){
            if (i==0){
                System.out.println(dotPalce(noLength)+dotPalce(idLength)+dotPalce(5)+dotPalce(4)+dotPalce(10)+dotPalce(10)+dotPalce(7)+dotPalce(10)+dotPalce(6)+"+");
                System.out.println(centerPlace(noLength,"No")+centerPlace(idLength,"Id")+centerPlace(5,"Code")+centerPlace(4,"Type")+centerPlace(9,"Id Created")+centerPlace(10,"Created")+centerPlace(7,"Id Auth")+centerPlace(10,"Auth Date")+centerPlace(6,"Status")+"|");
                System.out.println(dotPalce(noLength)+dotPalce(idLength)+dotPalce(5)+dotPalce(4)+dotPalce(10)+dotPalce(10)+dotPalce(7)+dotPalce(10)+dotPalce(6)+"+");
            }
            String insertType = "";
            if (billList.get(i).isType()){
                insertType = "Nhập";
            }else {
                insertType = "Xuất";
            }
            String insertStatus = "";
            if (billList.get(i).getStatus()==0){
                insertStatus = "Tạo";
            } else if (billList.get(i).getStatus()==1) {
                insertStatus = "Hủy";
            }else {
                insertStatus = "Duyệt";
            }
            System.out.println(centerPlace(noLength,String.valueOf(i+offSet+1))+centerPlace(idLength,String.valueOf(billList.get(i).getId()))+centerPlace(5,billList.get(i).getCode())+centerPlace(5,insertType)+centerPlace(10,billList.get(i).getIdCreated())+centerPlace(10,billList.get(i).getCeated())+centerPlace(7,billList.get(i).getIdAuth())+centerPlace(10,billList.get(i).getAuthDate())+centerPlace(6,insertStatus)+"|");
            if (i==billList.size()-1){
                System.out.println(dotPalce(noLength)+dotPalce(idLength)+dotPalce(5)+dotPalce(4)+dotPalce(10)+dotPalce(10)+dotPalce(7)+dotPalce(10)+dotPalce(6)+"+");
            }
        }
    }
    public boolean updateById(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã bill");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(!Pattern.matches("\\d+",input)){
                    System.out.println(colorRed("Mã bill chỉ bao gồm số"));
                }else {
                    Bill bill = iBill.findBill("",Integer.parseInt(input));
                    if (bill==null||!bill.getIdCreated().equals(loginInfor.getEmpId())){
                        System.out.println(colorRed("Không tìm thấy bill"));
                    }else if(bill.getStatus()==2){
                        System.out.println(colorRed("Sản phẩm đâ duyệt không thể sủa đổi"));
                    } else {
                        if (bill.update(sc)){
                            if (iBill.updateBillIn(bill)){
                                System.out.println(colorGreen("Cập nhật thành công"));
                                billId = bill.getId();
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
        offSet = 0;
        isOutTable = false;
        isUpdateOk = false;
        do {
            billList.clear();
            maxOffSet = iBill.getBillByStatuslength(loginInfor.getEmpId(),isType,"");
            billList.addAll(iBill.getBillByStatus(offSet,loginInfor.getEmpId(),isType,""));
            displayTable();
            barUpdate(sc);
        }while (!isOutTable);
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
        System.out.println("3. Chi tiết phiếu");
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
                    if (!Pattern.matches("[345890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==3){
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
                    if (!Pattern.matches("[345670]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==3){
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
                    if (!Pattern.matches("[34567890]",input)){
                        System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                    }else {
                        if (Integer.parseInt(input)==3){
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
                if (!Pattern.matches("[3450]",input)){
                    System.out.println(colorRed("Hãy nhập đúng chức năng cho phép"));
                }else {
                    if (Integer.parseInt(input)==3){
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
        if (Integer.parseInt(input)==3){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập vị trí bill hiển thị (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    input = "-1";
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>billList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        billId = billList.get(postision).getId();
                        updateBillDetail(sc);
                        isOutTable = false;
                        break;
                    }
                }
            }while (true);
        }
        if (Integer.parseInt(input)==5){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập vị trí bill cần cập nhật (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    input = "-1";
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>billList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Bill bill = billList.get(postision);
                        if (!bill.getIdCreated().equals(loginInfor.getEmpId())){
                            System.out.println("Không tìm thấy bill!!!");
                        }else if(bill.getStatus()==2){
                            System.out.println(colorRed("Sản phẩm đâ duyệt không thể sủa đổi"));
                        }else {
                            if (bill.update(sc)){
                                if (iBill.updateBillIn(bill)){
                                    System.out.println(colorGreen("Cập nhật thành công"));
                                    billId = bill.getId();
                                    isOutTable = true;
                                    isUpdateOk = true;
                                }
                            }else {
                                System.out.println(colorRed("Bạn đã thoát khỏi cập nhật!!"));
                                isOutTable = false;
                            }
                        }
                        break;
                    }
                }
            }while (true);
        }
    }
    public void updateBillDetail(Scanner sc){
        offSet = 0;
        isOutTable = false;
        do {
            billDetailList.clear();
            maxOffSet = iBillDetail.getBillDetailToUpdateLength(billId);
            if (maxOffSet==0){
                System.out.println(colorRed("Phiếu chi tiết không tồn tại!!!"));
                createBillDetail(sc);
                isOutTable = true;
            }else {
                billDetailList.addAll(iBillDetail.getBillDetailToUpdate(offSet,billId));
                displayTableBillDetail();
                barUpdateBillDetail(sc);
            }
        }while (!isOutTable);
    }
    public void createBillDetail(Scanner sc){
        do {
            System.out.println("Bạn có muốn nhập phiếu chi tiết không");
            System.out.println("1. Có");
            System.out.println("2. Không");
            String input = sc.nextLine().trim();
            if (!Pattern.matches("[12]",input)){
                System.out.println(colorRed("Hãy chọn 1 hoặc 2!!!"));
            }else {
                if (Integer.parseInt(input)==1){
                    BillDetail billDetail = new BillDetail();
                    billDetail.setType(isType);
                    billDetail.setBillId(billId);
                    if (billDetail.input(sc)){
                        IBillDetail iBillDetail1 = new BillDetailImp();
                        iBillDetail1.createBillDetail(billDetail);
                        System.out.println(colorGreen("Đã tạo phiếu chi tiết"));
                    }
                }else {
                    break;
                }
            }
        }while (true);
    }
    public void displayTableBillDetail(){
        int nameLength = 12;
        int billDetailIdLength = 14;
        int billIdLength = 7;
        int productIdLenght = 10;
        int quantityLength = 8;
        int priceLength  = 5;
        for (BillDetail billDetail: billDetailList){
            if (billDetail.getProductName().length()>nameLength) nameLength = billDetail.getProductName().length();
        }
        for (int i=0;i<billDetailList.size();i++){
            if (i==0){
                System.out.println(dotPalce(nameLength)+dotPalce(billDetailIdLength)+dotPalce(billIdLength)+dotPalce(productIdLenght)+dotPalce(quantityLength)+dotPalce(priceLength)+"+");
                System.out.println(centerPlace(nameLength,"Product Name")+centerPlace(billDetailIdLength,"Bill Detail Id")+centerPlace(billIdLength,"Bill Id")+centerPlace(productIdLenght,"Product Id")+centerPlace(quantityLength,"Quantity")+centerPlace(priceLength,"Price")+"|");
                System.out.println(dotPalce(nameLength)+dotPalce(billDetailIdLength)+dotPalce(billIdLength)+dotPalce(productIdLenght)+dotPalce(quantityLength)+dotPalce(priceLength)+"+");
            }
            System.out.println(centerPlace(nameLength,billDetailList.get(i).getProductName())+centerPlace(billDetailIdLength,String.valueOf(billDetailList.get(i).getId()))+centerPlace(billIdLength,String.valueOf(billDetailList.get(i).getBillId()))+centerPlace(productIdLenght,billDetailList.get(i).getProductId())+centerPlace(quantityLength,String.valueOf(billDetailList.get(i).getQuantity()))+centerPlace(priceLength,String.valueOf(billDetailList.get(i).getPrice()))+"|");
            if (i==(billDetailList.size()-1)){
                System.out.println(dotPalce(nameLength)+dotPalce(billDetailIdLength)+dotPalce(billIdLength)+dotPalce(productIdLenght)+dotPalce(quantityLength)+dotPalce(priceLength)+"+");
            }
        }
    }
    public void barUpdateBillDetail(Scanner sc){
        String input = "";
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
                    if (!Pattern.matches("[5890]",input)){
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
                    if (!Pattern.matches("[5670]",input)){
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
                    if (!Pattern.matches("[567890]",input)){
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
                if (!Pattern.matches("[50]",input)){
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
        if (Integer.parseInt(input)==5){
            System.out.println(colorGreen("Có thể nhập exit để thoát"));
            do {
                System.out.println("Hãy nhập vị trí bill detail cần cập nhật (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>billDetailList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        BillDetail billDetail = billDetailList.get(postision);
                        if (billDetail.update(sc)){
                            if (iBillDetail.updateBillDetail(billDetail)){
                                System.out.println(colorGreen("Cập nhật thành công"));
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
