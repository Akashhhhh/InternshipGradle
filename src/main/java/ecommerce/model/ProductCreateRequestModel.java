package ecommerce.model;

public class ProductCreateRequestModel {
    private String prodName;
    private int sellPrice;
    private String description;
    private String type;
    private int quantity;

    public ProductCreateRequestModel(){

    }
    public ProductCreateRequestModel(String prodName, int sellPrice, String description, String type, int quantity ){
        this.prodName = prodName;
        this.sellPrice = sellPrice;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
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
