package model;

public class OrderDetail {
    private int oId;
    private int pId;
    private int quantity;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int oId, int pId, int quantity, double price) {
        this.oId = oId;
        this.pId = pId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
