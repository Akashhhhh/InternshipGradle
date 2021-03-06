package ecommerce.model;

import java.util.UUID;

public class ProductModel {
    UUID prodId ;
    private String prodName;
    private int sellPrice;
    private String description;
    private String type;
    private int quantity;

    public ProductModel(){

    }
    public ProductModel( String prodName, int sellPrice, String description, String type, int quantity ){
        this.prodName = prodName;
        this.sellPrice = sellPrice;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
    }

    public ProductModel(String prodName, int quantity) {
        this.prodName = prodName;
        this.quantity = quantity;
    }

    public ProductModel(String prodName) {
        this.prodName = prodName;
    }

    public ProductModel(UUID prodId, String prodName, int sellPrice, String description, String type, int quantity) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.sellPrice = sellPrice;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
    }

    public UUID getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
