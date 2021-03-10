package ecommerce.service;

import ecommerce.dao.ProductDao;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductDisplayResponseModel;

import java.sql.Connection;
import java.util.UUID;
import java.util.Vector;

/**
 * This class is used for validating the inputs
 * @author  Akash Gupta
 */
public class ProductService {

    static ProductDao productDao ;
    Validator validator ;
    public ProductService(ProductDao productDao, Validator validator){
        this.productDao = productDao;
        this.validator = validator;

    }
    public ProductService(){
        productDao = new ProductDao();
        validator = new Validator();

    }

    /**
     * This method is used for validation when product is added to database
     * @param product object of product class
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void addNewProduct(Product product, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateProduct(product);
        // dao
        productDao.insertProductToDb(product, con);
    }

    /**
     * This method is used for validation when product information is updated in database
     * @param qt units of product that is updated
     * @param prodId product id
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateProduct(int qt, String prodId, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateUUID(prodId.toString());
        if (qt <= 0) {
            throw new InvalidInputException(400, "Quantity should be at least 1");
        }
        productDao.updateProductToDb(qt, UUID.fromString(prodId), con);
    }

    /**
     * This method is used for validation when product information is deleted in database
     * @param prodId product name
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void deleteProduct(String prodId, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateUUID(prodId.toString());
        productDao.deleteProductToDb(UUID.fromString(prodId),con);
    }
    public  void checkProdName(String prodName)throws InvalidInputException{
        if(!validator.validateString(prodName)){
            throw new InvalidInputException(400, "Check the product name");
        }
    }

    public ProductDisplayResponseModel getProduct(String prodId, Connection con) throws ApplicationRuntimeException, InvalidInputException {

        validator.validateUUID(prodId);
        Product product = productDao.getProductToDb(UUID.fromString(prodId),con);
        if(product==null){
            throw new InvalidInputException(400,"Product does not exist");
        }
        ProductDisplayResponseModel productDisplayResponseModel = new ProductDisplayResponseModel(UUID.fromString(prodId),product.getProdName()
                ,product.getSellPrice(),product.getDescription(),product.getType(),product.getQuantity());

        return productDisplayResponseModel;
    }

    public Vector<ProductDisplayResponseModel> getMenu(Connection con)throws ApplicationRuntimeException {
       return productDao.getMenuToDb(con);
    }
}
