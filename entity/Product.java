package entity;

import java.util.Scanner;
import java.util.regex.Pattern;

import business.IProduct;
import business.ProductImp;

public class Product {
    private String id = "";
    private String name = "";
    private String manufacturer = "";
    private String created = "";
    private int batch = 0;
    private int quantity = 0;
    private boolean status = false;

    public Product() {
    }

    public Product(String id, String name, String manufacturer, String created, int batch, int quantity, boolean status) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.created = created;
        this.batch = batch;
        this.quantity = quantity;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean input(Scanner sc) {
        boolean isReturn = true;
        System.out.println(colorGreen("Có thể nhập exit để thoát"));
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Hãy nhập mã sản phẩm gồm 5 chữ số");
            id = sc.nextLine().trim();
            if (id.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (id.length() != 5) {
                    System.out.println(colorRed("Mã sản phẩm phải là 5 ký tự !!"));
                } else {
                    IProduct iProduct = new ProductImp();
                    if (!iProduct.productCheckId(id)) {
                        System.out.println(colorRed("Mã sản phẩm đã tồn tại!! Vui lòng nhập lại!!"));
                    } else {
                        break;
                    }

                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy nhập tên sản phẩm");
            name = sc.nextLine();
            if (name.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (name.length() <= 0) {
                    System.out.println(colorRed("Tên sản phẩm không được để trống!!!"));
                } else if (name.length() > 150) {
                    System.out.println(colorRed("Tên sản phẩm quá dài!!!"));
                } else {
                    IProduct iProduct = new ProductImp();
                    if (!iProduct.productCheckName(name)) {
                        System.out.println(colorRed("Tên sản phẩm đã tồn tại!!! Vui lòng nhập lại!!!"));
                    } else {
                        break;
                    }
                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy nhập tên nhà sản xuất");
            manufacturer = sc.nextLine();
            if (manufacturer.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (manufacturer.length() <= 0) {
                    System.out.println(colorRed("Tên nhà sản xuất đang để trống!!!"));
                } else if (manufacturer.length() > 200) {
                    System.out.println(colorRed("Tên nhà sản xuất quá dài!!!"));
                } else {
                    break;
                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy nhập số lô đang chứa sản phẩm");
            String input = sc.nextLine();
            if (input.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (!Pattern.matches("\\d+", input)) {
                    System.out.println(colorRed("Số lô chứa sản phẩm chỉ bao gồm ký tự số!!!"));
                } else {
                    if (Integer.parseInt(input) > 32767) {
                        System.out.println(colorRed("Số lô vượt quá giới hạn cho phép!!! giới hạn là 32767!!!"));
                    } else {
                        IProduct iProduct = new ProductImp();
                        if (!iProduct.productCheckBatch(Integer.parseInt(input))) {
                            System.out.println(colorRed("Số lô đã tồn tại!! Vui lòng nhập lại!!!"));
                        } else {
                            batch = Integer.parseInt(input);
                            break;
                        }
                    }
                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy chọn trạng thái sản phẩm");
            System.out.println("1. Hoạt động");
            System.out.println("2. Không hoạt động");
            String input = sc.nextLine();
            if (input.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (Integer.parseInt(input) == 1) {
                    status = true;
                    break;
                } else {
                    status = false;
                    break;
                }
            }
        }
        if (isExit) {
            isReturn = false;
        }
        return isReturn;
    }

    public boolean update(Scanner sc) {
        System.out.println(colorGreen("Nhập exit để thoát khỏi chức năng"));
        System.out.println(colorGreen("Bỏ trống ô và nhấn Enter nếu không muốn cập nhật ô đấy"));
        boolean isExit = false;
        boolean isReturn = true;
        while (!isExit) {
            System.out.println("Hãy nhập tên sản phẩm");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (input.isEmpty()) {
                    break;
                } else if (input.length() > 150) {
                    System.out.println(colorRed("Tên sản phẩm quá dài!!!"));
                } else {
                    IProduct iProduct = new ProductImp();
                    if (!iProduct.productCheckName(input)) {
                        System.out.println(colorRed("Tên sản phẩm đã tồn tại hoặc giống với tên sản phẩm trước đó!!! Vui lòng nhập lại!!!"));
                    } else {
                        name = input;
                        break;
                    }
                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy nhập tên nhà sản xuất");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (input.isEmpty()) {
                    break;
                } else if (input.length() > 200) {
                    System.out.println(colorRed("Tên nhà sản xuất quá dài!!!"));
                } else {
                    manufacturer = input;
                    break;
                }
            }
        }
        while (!isExit) {
            System.out.println("Hãy nhập số lô đang chứa sản phẩm");
            String input = sc.nextLine().trim();
            if (input.toLowerCase().equals("exit")) {
                isExit = true;
                break;
            } else {
                if (input.isEmpty()) {
                    break;
                } else {
                    if (!Pattern.matches("\\d+", input)) {
                        System.out.println(colorRed("Số lô chứa sản phẩm chỉ bao gồm ký tự số!!!"));
                    } else {
                        if (Integer.parseInt(input) > 32767) {
                            System.out.println(colorRed("Số lô vượt quá giới hạn cho phép!!! giới hạn là 32767!!!"));
                        } else {
                            IProduct iProduct = new ProductImp();
                            if (!iProduct.productCheckBatch(Integer.parseInt(input))) {
                                System.out.println(colorRed("Số lô đã tồn tại hoặc giống với sô lô hiện tại!! Vui lòng nhập lại!!!"));
                            } else {
                                batch = Integer.parseInt(input);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (isExit){
            isReturn = false;
        }
        return isReturn;
    }
    public String colorGreen(String text) {
        return "\u001B[32m" + text + "\u001B[0m";
    }

    public String colorRed(String text) {
        return "\u001B[31m" + text + "\u001B[0m";
    }
}
