package ecommerce.model;

import java.util.UUID;

public class OrderDisplayResponseModel {
    UUID orderId;
    UUID custId;

    // @InvalidNameAnnotation
    private float totalPrice;

    private String quantity; // list of units of every product
    private String productIds;

    public OrderDisplayResponseModel() {
    }

    public OrderDisplayResponseModel(UUID orderId, UUID custId, float totalPrice, String quantity, String productIds) {
        this.orderId = orderId;
        this.custId = custId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.productIds = productIds;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustId() {
        return custId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProductIds() {
        return productIds;
    }
}
