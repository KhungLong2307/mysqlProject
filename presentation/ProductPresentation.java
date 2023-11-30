package presentation;

import business.IProduct;
import business.ProductImp;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductPresentation {
    static List<Product> productList = new ArrayList<>();
    static int offSet = 0;
    static int maxOffSet = 0;
    static boolean isOutTable = false;
    static boolean isUpdateOk = false;
    public void menu(Scanner sc){
        boolean isExit = false;
        do{
            System.out.println("+-------------------------------------------+");
            System.out.println("|           PRODUCT MANAGEMENT              |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Danh sách sản phẩm                     |");
            System.out.println("| 2. Thêm mới sản phẩm                      |");
            System.out.println("| 3. Cập nhật sản phẩm                      |");
            System.out.println("| 4. Tìm kiếm sản phẩm                      |");
            System.out.println("| 5. Cập nhật trạng thái sản phẩm           |");
            System.out.println("| 6. Thoát                                  |");
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
                    isExit= true;
                    break;
            }
        }while (!isExit);
    }
    public void case1(Scanner sc){
        IProduct iProduct = new ProductImp();
        offSet = 0;
        isOutTable = false;
        do {
            productList.clear();
            maxOffSet = iProduct.getProductLength();
            productList.addAll(iProduct.getProduct(offSet));
            displayTable();
            bar(sc);
        }while (!isOutTable);
    }
    public void case2(Scanner sc){
        Product product = new Product();
        if (product.input(sc)){
            IProduct iProduct = new ProductImp();
            if (iProduct.createProduct(product)){
                System.out.println(colorGreen("Thêm mới thành công"));
            }
        }
    }
    public void case3(Scanner sc){
        do {
            System.out.println("1. Nhập mã sản phẩm đã biết");
            System.out.println("2. Chọn mã sản phẩm từ bảng");
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
        String input = "";
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập tên sản phẩm cần tìm kiếm");
            input = sc.nextLine().trim();
            if (input.isEmpty()||input.length()>150){
                System.out.println(colorRed("Tên sản phẩm quá dài hoặc đang để trống"));
            }else {
                break;
            }
        }while (true);
        IProduct iProduct = new ProductImp();
        offSet = 0;
        isOutTable = false;
        do {
            productList.clear();
            maxOffSet = iProduct.getSearchProductLength(input);
            productList.addAll(iProduct.getSearchProduct(offSet,input));
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
    public void case5(Scanner sc){
        do {
            System.out.println("1. Nhập mã sản phẩm đã biết");
            System.out.println("2. Chọn mã sản phẩm từ bảng");
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
    public boolean updateById(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã sản phẩm");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(input.length()!=5){
                    System.out.println(colorRed("Mã sản phẩm phải là 5 ký tự !!"));
                }else {
                    IProduct iProduct = new ProductImp();
                    Product product = iProduct.findProduct(input);
                    if (product==null){
                        System.out.println(colorRed("Không tìm thấy sản phẩm"));
                    }else {
                        if (product.update(sc)){
                            if (iProduct.updateProduct(product)){
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
        IProduct iProduct = new ProductImp();
        isUpdateOk = false;
        offSet = 0;
        isOutTable = false;
        do {
            productList.clear();
            maxOffSet = iProduct.getProductLength();
            productList.addAll(iProduct.getProduct(offSet));
            displayTable();
            barUpdate(sc);
        }while (!isOutTable);
    }
    public void displayTable(){
        int noLength = 2;
        if (String.valueOf(offSet).length()>noLength){
            noLength = String.valueOf(offSet).length();
        }
        int productIdLength = 5;
        int productNameLength = 12;
        int manufacturerLength = 12;
        int createdLength = 10;
        int batchLength = 5;
        int quantityLength = 8;
        int statusLength = 15;
        for (Product product : productList) {
            if (product.getName().length() > productNameLength) {
                productNameLength = product.getName().length();
            }
            if (product.getManufacturer().length() > manufacturerLength) {
                manufacturerLength = product.getManufacturer().length();
            }
        }
        for (int i=0;i<productList.size();i++){
            if (i==0){
                System.out.println(dotPalce(noLength)+dotPalce(productIdLength)+dotPalce(productNameLength)+dotPalce(manufacturerLength)+dotPalce(createdLength)+dotPalce(batchLength)+dotPalce(quantityLength)+dotPalce(statusLength)+"+");
                System.out.println(centerPlace(noLength,"No")+centerPlace(productIdLength,"Id")+centerPlace(productNameLength,"Product Name")+centerPlace(manufacturerLength,"Manufacturer")+centerPlace(createdLength,"Created")+centerPlace(batchLength,"Batch")+centerPlace(quantityLength,"Quantity")+centerPlace(statusLength,"Status")+"|");
                System.out.println(dotPalce(noLength)+dotPalce(productIdLength)+dotPalce(productNameLength)+dotPalce(manufacturerLength)+dotPalce(createdLength)+dotPalce(batchLength)+dotPalce(quantityLength)+dotPalce(statusLength)+"+");
            }
            String insertStatus = "";
            if (productList.get(i).isStatus()){
                insertStatus = "Hoạt Động";
            }else {
                insertStatus = "Không hoạt động";
            }
            System.out.println(centerPlace(noLength,String.valueOf(i+offSet+1))+centerPlace(productIdLength,productList.get(i).getId())+centerPlace(productNameLength,productList.get(i).getName())+centerPlace(manufacturerLength,productList.get(i).getManufacturer())+centerPlace(createdLength,productList.get(i).getCreated())+centerPlace(batchLength,String.valueOf(productList.get(i).getBatch()))+centerPlace(quantityLength,String.valueOf(productList.get(i).getQuantity()))+centerPlace(statusLength,insertStatus)+"|");
            if (i==(productList.size()-1)){
                System.out.println(dotPalce(noLength)+dotPalce(productIdLength)+dotPalce(productNameLength)+dotPalce(manufacturerLength)+dotPalce(createdLength)+dotPalce(batchLength)+dotPalce(quantityLength)+dotPalce(statusLength)+"+");
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
                System.out.println("Hãy nhập vị trí sản phẩm cần cập nhật trạng thái (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>productList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Product product = productList.get(postision);
                        IProduct iProduct = new ProductImp();
                        System.out.println("Tên sản phẩm đang cập nhật trạng thái: "+colorGreen(product.getName()));
                        System.out.println("Trạng thái hiện tại là: "+(product.isStatus()?colorGreen("Hoạt động"):colorGreen("Không hoạt động")));
                        System.out.println("Chuyển trạng thái");
                        System.out.println("1. Hoạt động");
                        System.out.println("2. Không hoạt động");
                        do {
                            input = sc.nextLine().trim();
                            if (!Pattern.matches("[12]",input)){
                                System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                            }else {
                                if (Integer.parseInt(input)==1){
                                    product.setStatus(true);
                                }else {
                                    product.setStatus(false);
                                }
                                if (iProduct.updateProduct(product)){
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
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>productList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Product product = productList.get(postision);
                        IProduct iProduct = new ProductImp();
                        if (product.update(sc)){
                            if (iProduct.updateProduct(product)){
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
    public boolean updateStatus(Scanner sc){
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        do {
            System.out.println("Hãy nhập mã sản phẩm");
            String input = sc.nextLine().trim();
            if(input.toLowerCase().equals("exit")){
                isReturn = false;
                break;
            }else {
                if(input.length()!=5){
                    System.out.println(colorRed("Mã sản phẩm phải là 5 ký tự !!"));
                }else {
                    IProduct iProduct = new ProductImp();
                    Product product = iProduct.findProduct(input);
                    if (product==null){
                        System.out.println(colorRed("Không tìm thấy sản phẩm"));
                    }else {
                        System.out.println("Tên sản phẩm đang cập nhật trạng thái: "+colorGreen(product.getName()));
                        System.out.println("Trạng thái hiện tại là: "+(product.isStatus()?colorGreen("Hoạt động"):colorGreen("Không hoạt động")));
                        System.out.println("Chuyển trạng thái");
                        System.out.println("1. Hoạt động");
                        System.out.println("2. Không hoạt động");
                        do {
                            input = sc.nextLine().trim();
                            if (!Pattern.matches("[12]",input)){
                                System.out.println(colorRed("Hãy chọn 1 hoặc 2"));
                            }else {
                                if (Integer.parseInt(input)==1){
                                    product.setStatus(true);
                                }else {
                                    product.setStatus(false);
                                }
                                if (iProduct.updateProduct(product)){
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
