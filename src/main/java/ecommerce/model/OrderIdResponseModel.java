package ecommerce.model;

import java.util.UUID;

public class OrderIdResponseModel {
    UUID orderId;

    public OrderIdResponseModel() {
    }

    public OrderIdResponseModel(UUID orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
