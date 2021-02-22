package ecommerce.controller;

import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.ProductService;
import ecommerce.service.Validate;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class ProductController {
    Scanner sc = new Scanner(System.in);
    
    public void add(Logger logger, Connection con) throws InvalidInputException {
        logger.info("Enter product name: ");
        String prodName = sc.next();

       logger.info("Selling Price: ");
        float sell_price = sc.nextFloat();

        logger.info("Description: ");
        String description = sc.next();

        logger.info("Type: ");;
        String type = sc.next();

        logger.info("Units: ");
        int qt = sc.nextInt();

        Product obj = new Product(prodName,sell_price,description,type,qt);
        ProductService.addNewProduct(obj,con);
    }
    public void update(Logger logger, Connection con) throws InvalidInputException {
         logger.info("Enter product name: ");
         String prodName = sc.next();

         logger.info("Enter quantity: ");
         int qt = sc.nextInt();

         ProductService.updateProduct(qt,prodName,con);


    }
    public  void delete(Logger logger, Connection con) throws InvalidInputException {
        logger.info("Enter product name: ");
        String prodName = sc.next();

        if(Validate.validateString(prodName))
            ProductService.deleteProduct(prodName,con);
        throw new InvalidInputException(400, "Check the inputs");

    }
    public void admin(Logger logger, Connection con) throws InvalidInputException {

        logger.info("\n"+"1.Add Product"+"\n"+
                         "2.Update an existing Product"+"\n"+
                         "3.Delete an exiting Product"+"\n"+
                          "4.Exit"
        );



        int n  =  sc.nextInt();
        switch (n){
            case 1:
                add(logger,con);
                break;
            case 2:
                update(logger,con);
                break;
            case 3:
                delete(logger,con);
                break;
            case 4:
                break;

        }
    }
}
