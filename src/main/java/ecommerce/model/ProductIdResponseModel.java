package ecommerce.model;

import java.util.UUID;

public class ProductIdResponseModel {
    UUID prodId ;
    public ProductIdResponseModel(){

    }
    public ProductIdResponseModel(UUID prodId){
        this.prodId = prodId;
    }

    public UUID getProdId() {
        return prodId;
    }

    public void setProdId(UUID prodId) {
        this.prodId = prodId;
    }
}
