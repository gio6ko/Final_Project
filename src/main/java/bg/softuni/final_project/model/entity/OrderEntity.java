package bg.softuni.final_project.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private UserEntity buyer;
    private Integer orderPrice;
    private List<BookEntity> orderItems = new ArrayList<>();


    public OrderEntity() {
    }

    public OrderEntity(UserEntity buyer, Integer orderPrice, List<BookEntity> orderItems) {
        this.buyer = buyer;
        this.orderPrice = orderPrice;
        this.orderItems = orderItems;
    }

    @ManyToOne
    public UserEntity getBuyer() {
        return buyer;
    }

    public OrderEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public OrderEntity setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    @ManyToMany
    public List<BookEntity> getOrderItems() {
        return orderItems;
    }

    public OrderEntity setOrderItems(List<BookEntity> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
