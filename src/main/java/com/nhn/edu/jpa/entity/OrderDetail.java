package com.nhn.edu.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @EmbeddedId
    private Pk pk = new Pk();

    private String description;

    @ManyToOne
    @MapsId("orderId")
    private Order order;


    public OrderDetail() {
        // nothing
    }

    public OrderDetail(String type) {
        this.pk.setType(type);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "order_id")
        private Long orderId;

        private String type;

    }

}
