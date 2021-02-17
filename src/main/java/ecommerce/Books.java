package ecommerce;

/**
 * This class is basically inherited from Product class and also includes some more properties i.e deliveryCost, tax,
 * quantity.
 *
 * @author Akash Gupta
 */
public class Books extends Product {
    /**
     * This is the constructor of the Book class.
     *
     * @param name        Name of the particular book
     * @param basePrice   Base Price of the book
     * @param description Some description about the particular book
     * @param weight      Weight of the particular book
     * @param quantity    Quantity of the particular book
     */

    public Books(String name, double basePrice, String description, double weight, int quantity) {
        super(name, basePrice, description, weight,quantity,30,8);
    }


}
