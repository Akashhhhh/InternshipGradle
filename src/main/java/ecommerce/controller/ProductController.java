package ecommerce.controller;

import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductModel;
import ecommerce.service.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is used to manage add , update and delete product operation.
 *
 * @author Akash Gupta
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    ProductService productService = new ProductService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/addProduct")
    public ResponseEntity add(@Valid @RequestBody ProductModel productModel) {
        try {
            productService.addNewProduct(productModel, con);

        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product added", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updateProduct")
    public ResponseEntity update(@Valid @RequestBody ProductModel productModel) {

        int qt = productModel.getQuantity();
        String prodName = productModel.getProdName();

        ProductModel product = null;
        try {
            product = productService.getProduct(prodName, con);
            if (product != null) {
                productService.updateProduct(qt, prodName, con);
            } else {
                return new ResponseEntity("Product not exist", HttpStatus.OK);

            }

        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Product updated", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @DeleteMapping("/deleteProduct")
    public ResponseEntity delete(@Valid @RequestBody ProductModel productModel) {

        ProductModel product = null;
        String prodName = productModel.getProdName();
        try {
            product = productService.getProduct(prodName, con);
            if (product != null) {
                productService.deleteProduct(prodName, con);
            } else {
                return new ResponseEntity("Product does not exist ", HttpStatus.OK);

            }

        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Product deleted ", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/displayProduct")
    public ResponseEntity displayProduct(@Valid @RequestBody ProductModel productModel) {
        ProductModel product = null;
        String name = productModel.getProdName();
        try {
            product = productService.getProduct(name, con);

        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        }
        if (product != null) {
            return new ResponseEntity(product, HttpStatus.OK);

        }
        return new ResponseEntity("Product not exist", HttpStatus.OK);
    }


}
