package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.dao.implementation.OrderDaoJDBC;

import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private List<LineItem> items;
    private int id;
    private double totalPrice;
    private int totalItems;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String shippingAddress;
    private String billingAdrress;
    private int userid;
    public int getId() {
        return id;
    }

    public Order(int userid) {
        this.items = new LinkedList<>();
        this.totalItems = 0;
        this.totalPrice = 0;
        CurrentOrders.addOrder(this);
        this.userid = userid;
        this.id = userid;
    }

    public void addItem(Product product){
        LineItem item = new LineItem(product);
        for (LineItem currentItem:items){
            if (currentItem.name.equals(item.name) ){
                currentItem.increaseQuantity();
                this.totalPrice += item.getDefaultPrice();
                this.totalItems += 1;
                return;
            }
        }
        items.add(item);
        this.totalPrice += item.getDefaultPrice();
        this.totalItems += 1;
    }

    public void remove(Product product){
        items.remove(product);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void modifyQuantity(String name, int newQuantity){
        for (LineItem item: items) {
            if (item.getName().equals(name)){
                if (newQuantity ==0){
                    items.remove(item);
                    setTotalItems();
                    setTotalPrice();
                    break;
                }
                item.setQuantity(newQuantity);
                setTotalItems();
                setTotalPrice();
            }
        }
    }
    public void setTotalPrice(){
        totalPrice =0;
        for (LineItem item: items) {
            totalPrice += item.getDefaultPrice()*item.getQuantity();
        }
    }


    public void setTotalItems(){
        totalItems=0;
        for (LineItem item: items) {
            totalItems += item.getQuantity();
        }
    }
    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", OrderId=" + id +
                ", FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Email=" + email +
                ", Phone=" + phone +
                ", ShippingAdress=" + shippingAddress +
                ", BillingAdress=" + billingAdrress +
                '}';
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setBillingAdrress(String billingAdrress) {
        this.billingAdrress = billingAdrress;
    }

    public void saveOrder(){
        OrderDaoJDBC orderDaoJDBC = new OrderDaoJDBC();
        orderDaoJDBC.saveOrder(this);
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setItems(List<LineItem> items){
        this.items = items;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(){
        this.userid = userid;
    }
}
