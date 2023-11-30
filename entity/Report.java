package entity;

public class Report {
    private String productName = "";
    private int quantity = 0;
    private float price = 0.0f;
    private String date = "";
    private double tolat = 0.0;
    private int billId = 0;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Report(String productName, int quantity, float price, String date, double tolat) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.tolat = tolat;
    }

    public Report() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTolat() {
        return tolat;
    }

    public void setTolat(double tolat) {
        this.tolat = tolat;
    }
    public String tolat(){
        return String.valueOf(quantity*price);
    }
}
