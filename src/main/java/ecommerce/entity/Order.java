package ecommerce.entity;

import java.util.UUID;

public class Order {
    UUID orderId=UUID.randomUUID();
    UUID custId;

    private String orderName;


    private String timeOrder; // time when order is placed
    private float totalPrice;

    private String quantity; // list of units of every product
    private String productIds;
    public Order(UUID custId,float totalPrice, String quantity, String productIds){
        this.custId=custId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.productIds = productIds;
    }

    public String getOrderTime() {
        return timeOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustId() {
        return custId;
    }

    public String getProductIds() {
        return productIds;
    }
}
