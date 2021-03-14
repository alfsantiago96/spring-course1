package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @ManyToOne //Relacionamento entre objetos, para que o JPA transformar em chaves estrangeiras na DB
    @JoinColumn(name = "client_id") // nome da chave estrangeira/coluna a ser adicionada no DB
    private User client;

    //Enums as OrderStatus == Integer, somente dentro da classe
    private Integer orderStatus;

    @OneToMany(mappedBy = "orderItemPK.order")
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(){

    }

    public Order(Long id, Instant moment, OrderStatus orderStatus,User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
        this.orderStatus = orderStatus.getCode();
    }

    public Set<OrderItem> getOrderItems(){
        return orderItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getMoment(), order.getMoment()) && Objects.equals(getClient(), order.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getClient());
    }
}
