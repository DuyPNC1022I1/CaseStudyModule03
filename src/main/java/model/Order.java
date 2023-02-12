package model;

public class Order {
    private int id;
    private String date;
    private int cusId;
    private double totalMoney;

    public Order() {
    }

    public Order(int id, String date, int cusId, double totalMoney) {
        this.id = id;
        this.date = date;
        this.cusId = cusId;
        this.totalMoney = totalMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
