package ecommerce.controller;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.ProductService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is used to manage add , update and delete product operation.
 *
 * @author Akash Gupta
 */
public class ProductController {
    Scanner sc = new Scanner(System.in);
    ProductService productService = new ProductService();
    private static Logger logger = java.util.logging.Logger.getLogger(ProductController.class.getName());

    /**
     * This method is used when we to add product to database
     *
     * @param con connection
     */
    public void add(Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter product name: ");
        String prodName = sc.next();

        logger.info("Selling Price: ");
        float sell_price = sc.nextFloat();

        logger.info("Description: ");
        String description = sc.next();

        logger.info("Type: ");
        ;
        String type = sc.next();

        logger.info("Units: ");
        int qt = sc.nextInt();

        Product obj = new Product(prodName, sell_price, description, type, qt);

        productService.addNewProduct(obj, con);

    }

    /**
     * This method is used when we to update product information in database
     *
     * @param con connection
     */
    public void update(Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter product name: ");
        String prodName = sc.next();

        logger.info("Enter quantity: ");
        int qt = sc.nextInt();


        productService.updateProduct(qt, prodName, con);


    }

    /**
     * This method is used when we to delete  product in database
     *
     * @param con connection
     */
    public void delete(Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter product name: ");
        String prodName = sc.next();


        productService.checkProdName(prodName);
        productService.deleteProduct(prodName, con);


    }

    /**
     * This method is used for providing interface
     *
     * @param con connection
     */
    public void admin(Connection con) {

        logger.info("\n" + "1.Add Product" + "\n" +
                "2.Update an existing Product" + "\n" +
                "3.Delete an exiting Product" + "\n" +
                "4.Exit"
        );


        int n = sc.nextInt();
        try {
            switch (n) {
                case 1:
                    add(con);
                    break;
                case 2:
                    update(con);
                    break;
                case 3:
                    delete(con);
                    break;
                case 4:
                    break;

            }
        } catch (InvalidInputException e) {
            logger.info("Error Code: " + e.getErroCode() + "|" + "Error Description: " + e.getErrorDesc());

        } catch (ApplicationRuntimeException e) {
            logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
        }
    }
}
