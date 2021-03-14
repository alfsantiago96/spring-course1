package com.educandoweb.course.entities;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(){

    }

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        orderItemPK.setOrder(order);
        orderItemPK.setProduct(product);
        this.quantity = quantity;
        this.price = price;

    }

    @JsonIgnore //Responsavel por chamar Order, o qual se encontra no construtor.
    public Order getOrder(){
        return orderItemPK.getOrder();
    }

    public void setOrder(Order order){
        orderItemPK.setOrder(order);
    }

    public Product getProduct(){
        return orderItemPK.getProduct();
    }

    public void setProduct(Product product){
        orderItemPK.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderItemPK.equals(orderItem.orderItemPK) && getQuantity().equals(orderItem.getQuantity()) && getPrice().equals(orderItem.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemPK, getQuantity(), getPrice());
    }

    public double getSubTotal(){
        double subtotal = getPrice() * getQuantity();
        return subtotal;
    }
}
