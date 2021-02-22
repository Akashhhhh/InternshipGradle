package ecommerce.service;

import ecommerce.dao.ProductDao;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;

public class ProductService {

    public static void addNewProduct(Product obj, Connection con) throws InvalidInputException {
        if(Validate.validateProduct(obj)){
           // dao
            boolean answer= ProductDao.insertProductToDb(obj,con);
            if(answer)
            {
                System.out.println("Product is added successfully");
            }
            else
            {
                System.out.println("Something went wrong");
            }



        }
        else throw new InvalidInputException(400, "Check the inputs");
    }
    public static void updateProduct(int qt, String prodName, Connection con) throws InvalidInputException {
        if(Validate.validateString(prodName)&&qt>=1){
            //jdbc code..

            boolean answer= ProductDao.updateProductToDb(qt,prodName,con);
            if(answer)
            {
                System.out.println("Product is updated successfully");
            }
            else
            {
                System.out.println("Something went wrong");
            }
        }
        else throw new InvalidInputException(400, "Check the inputs(quantity should be atleast 1)");
    }
    public static void deleteProduct(String name, Connection con) throws InvalidInputException {
        if(Validate.validateString(name)){
            boolean answer= ProductDao.deleteProductToDb(name,con);
            if(answer)
            {
                System.out.println("Product is deleted sucessfully");
            }
            else
            {
                System.out.println("Something went wrong");
            }
        }
        else throw new InvalidInputException(400, "Check the inputs");
    }

}
