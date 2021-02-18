package ecommerce;


/**
 * This class is basically inherited from Product class and also includes some more properties i.e deliveryCost, tax,
 * quantity.
 *
 * @author Akash Gupta
 */
public class Bikes extends Product {
    /**
     * This is the constructor of the Bike class.
     *
     * @param name        Name of the particular bike
     * @param basePrice   Base Price of the bike
     * @param description Some description about the particular bike
     * @param weight      Weight of the particular bike
     * @param quantity    Quantity of the particular bike
     */

    public Bikes(String name, double basePrice, String description, double weight, int quantity) {
        super(name, basePrice, description, weight, quantity, 30, 8);
    }


}

