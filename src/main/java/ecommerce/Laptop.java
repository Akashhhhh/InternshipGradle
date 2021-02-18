package ecommerce;

/**
 * This class is basically inherited from Product class and also includes some more properties i.e deliveryCost, tax,
 * quantity.
 *
 * @author Akash Gupta
 */
public class Laptop extends Product {
    /**
     * This is the constructor of the Laptop class.
     *
     * @param name        Name of the particular laptop
     * @param basePrice   Base Price of the laptop
     * @param description Some description about the particular laptop
     * @param weight      Weight of the particular laptop
     * @param quantity    Quantity of the particular laptop
     */

    public Laptop(String name, double basePrice, String description, double weight, int quantity) {
        super(name, basePrice, description, weight,quantity,30,8);
    }


}
