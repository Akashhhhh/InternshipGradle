package ecommerce.service;

import ecommerce.dao.ProductDao;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductModel;

import java.sql.Connection;

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
     * @param obj object of product class
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void addNewProduct(ProductModel obj, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateProduct(obj);
        // dao
        productDao.insertProductToDb(obj, con);
    }

    /**
     * This method is used for validation when product information is updated in database
     * @param qt units of product that is updated
     * @param prodName product name
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateProduct(int qt, String prodName, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (!validator.validateString(prodName)) {
            throw new InvalidInputException(400, "Check the prodName");
        }
        if (qt <= 0) {
            throw new InvalidInputException(400, "Quantity should be at least 1");
        }
        productDao.updateProductToDb(qt, prodName, con);
    }

    /**
     * This method is used for validation when product information is deleted in database
     * @param name product name
     * @param con connection
     * @throws InvalidInputException for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void deleteProduct(String name, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (!validator.validateString(name)) {
            throw new InvalidInputException(400, "Check the product name");

        }
        productDao.deleteProductToDb(name,con);
    }
    public  void checkProdName(String prodName)throws InvalidInputException{
        if(!validator.validateString(prodName)){
            throw new InvalidInputException(400, "Check the product name");
        }
    }

    public ProductModel getProduct(String name, Connection con) throws ApplicationRuntimeException, InvalidInputException {

        if (!validator.validateString(name)) {
            throw new InvalidInputException(400, "Check the product name");

        }
      return productDao.getProductToDb(name,con);
    }
}
