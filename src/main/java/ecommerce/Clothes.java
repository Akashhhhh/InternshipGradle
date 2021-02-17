package ecommerce;

/**
 * This class is basically inherited from Product class and also includes some more properties i.e deliveryCost, tax,
 * quantity.
 *
 * @author Akash Gupta
 */
public class Clothes extends Product {
    /**
     * This is the constructor of the Clothe class.
     *
     * @param name        Name of the particular clothe
     * @param basePrice   Base Price of the clothe
     * @param description Some description about the particular clothe
     * @param weight      Weight of the particular clothe
     * @param quantity    Quantity of the particular clothe
     */

    public Clothes(String name, double basePrice, String description, double weight, int quantity) {
        super(name, basePrice, description, weight,quantity,30,8);
    }


}
