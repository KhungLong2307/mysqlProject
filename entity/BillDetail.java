package entity;

import business.IProduct;
import business.ProductImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BillDetail {
    static List<Product> productList = new ArrayList<>();
    static int offSet = 0;
    static int maxOffSet = 0;
    static boolean isOutTable = false;
    static boolean isUpdateOk = false;
    private int id = 0;
    private int billId = 0;
    private String productId ="";
    private int quantity = 0;
    private float price = 0.0f;
    private boolean type = true;
    private String productName = "";

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public BillDetail() {
    }

    public BillDetail(int id, int billId, String productId, int quantity, float price) {
        this.id = id;
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public boolean input(Scanner sc){
        boolean isExit =false;
        boolean isReturn = true;
            do {
                System.out.println("1. Nhập mã sản phẩm đã biết");
                System.out.println("2. Chọn mã sản phẩm từ bảng");
                System.out.println("0. Thoát");
                String input = sc.nextLine().trim();
                if (!Pattern.matches("[120]",input)){
                    System.out.println(colorRed("Hãy chọn đúng chức năng cho phép!!!"));
                }else {
                    if (Integer.parseInt(input)==0){
                        isExit = true;
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
        while(!isExit){
            if (type){
                System.out.println("Hãy nhập số lượng nhập");
            }else {
                System.out.println("Hãy nhập sô lượng xuất");
            }
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (!Pattern.matches("\\d+",input)||Integer.parseInt(input)<=0){
                    System.out.println(colorRed("Số lượng không hợp lệ!!!"));
                }else {
                    quantity = Integer.parseInt(input);
                    break;
                }
            }
        }
        while(!isExit){
            if (type){
                System.out.println("Hãy nhập giá nhập");
            }else {
                System.out.println("Hãy nhập giá xuất");
            }
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (Pattern.matches("\\d+.\\d+",input)||Pattern.matches("\\d+",input)){
                    price = Float.parseFloat(input);
                    break;
                }else {
                    if (type){
                        System.out.println(colorRed("Giá nhập không hợp lệ"));
                    }else {
                        System.out.println(colorRed("Giá xuât không hợp lệ"));
                    }
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
        boolean isReturn = true;
        while(!isExit){
            System.out.println("Hãy nhập số lượng mới");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.isEmpty()){
                    break;
                }else {
                    if(!Pattern.matches("\\d+",input)||Integer.parseInt(input)<=0){
                        System.out.println(colorRed("Số lượng không hợp lệ"));
                    }else {
                        quantity = Integer.parseInt(input);
                        break;
                    }
                }
            }
        }
        while (!isExit){
            if (type){
                System.out.println("Hãy nhập giá nhập mới");
            }else {
                System.out.println("Hãy nhập giá xuất mới");
            }
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")){
                isExit = true;
                break;
            }else {
                if (input.isEmpty()){
                    break;
                }else {
                    if (Pattern.matches("\\d+.\\d+",input)||Pattern.matches("\\d+",input)){
                        price = Float.parseFloat(input);
                        break;
                    }else {
                        if (type){
                            System.out.println(colorRed("Giá nhập không hợp lệ"));
                        }else {
                            System.out.println(colorRed("Giá xuât không hợp lệ"));
                        }
                    }
                }
            }
        }
        if (isExit) {
            isReturn = false;
        }
        return isReturn;
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
                        productId = product.getId();
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
    public void barUpdate(Scanner sc){
        String input = "";
        isUpdateOk = false;
        System.out.println("5. Chọn vị trí");
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
                System.out.println("Hãy nhập vị trí sản phẩm cần lấy id (No)");
                input = sc.nextLine().trim();
                if (input.toLowerCase().equals("exit")){
                    break;
                }else {
                    if (!Pattern.matches("\\d+",input)||(Integer.parseInt(input)-offSet)<=0||(Integer.parseInt(input)-offSet)>productList.size()){
                        System.out.println(colorRed("Vị trí không hợp lệ!! Vui lòng nhập lại!!!"));
                    }else {
                        int postision = Integer.parseInt(input)-1-offSet;
                        Product product = productList.get(postision);
                        productId = product.getId();
                        isOutTable = true;
                        isUpdateOk = true;
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
