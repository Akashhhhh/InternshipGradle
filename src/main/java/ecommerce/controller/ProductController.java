package ecommerce.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductModel;
import ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is used to manage add , update and delete product operation.
 *
 * @author Akash Gupta
 */
@RequestMapping("/user")
@RestController
public class ProductController {

    ProductService productService = new ProductService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @PostMapping("/addProduct")
    public ResponseEntity add(@Valid @RequestBody Product product)  {
        try {
            productService.addNewProduct(product, con);
            return  new ResponseEntity("Product added", HttpStatus.OK);

        } catch (InvalidInputException e) {
           e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }
        return  new ResponseEntity("Product not added", HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/updateProduct")
    public ResponseEntity update(@Valid @RequestBody ProductModel productModel)  {

        int qt = productModel.getQuantity();
        String prodName = productModel.getProdName();
        try {
            productService.updateProduct(qt, prodName, con);
            return  new ResponseEntity("Product updated to database", HttpStatus.OK);

        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
           e.logError();
        }

        return  new ResponseEntity("Product not updated to database", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity delete(@RequestBody ObjectNode objectnode)  {


        String prodName = objectnode.get("prodName").asText();
        try {
            productService.deleteProduct(prodName, con);
            return  new ResponseEntity("Product Deleted", HttpStatus.OK);

        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }

        return  new ResponseEntity("Product not deleted ", HttpStatus.BAD_REQUEST);

    }




}
