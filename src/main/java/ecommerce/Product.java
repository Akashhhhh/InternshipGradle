package ecommerce;

/**
 * <h1>Product Class</h1>
 * This class implements Product constructor and info method.
 *
 * @author Akash Gupta
 */
public class Product {
    private String name;
    private double basePrice;
    private String description;
    private double weight;
    private int quantity;
    private int deliveryCost;
    private int tax;

    /**
     * This is the constructor function of the Product class.
     *
     * @param name         This is the name of the product.
     * @param basePrice    This is the basePrice of the product.
     * @param description  This is the description of the product.
     * @param weight       This is the weight of the product.
     * @param deliveryCost This is the shipping cost of the product.
     * @param tax          This is the tax of the product.
     */
    public Product(String name, double basePrice, String description, double weight, int quantity, int
            deliveryCost, int tax) {
        this.name = name;
        this.basePrice = basePrice;
        this.basePrice = basePrice;
        this.description = description;
        this.weight = weight;
        this.quantity = quantity;
        this.deliveryCost = deliveryCost;
        this.tax = tax;
    }

    /**
     * @return name of the Product.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set name of the Product.
     *
     * @param name This is the name of the Product.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return basePrice of the Product.
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Set basePrice of the Product.
     *
     * @param basePrice This is the basePrice of the Product.
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return description of the Product.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set description of the Product.
     *
     * @param description This is the description of the Product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return weight of the Product.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Set weight of the Product.
     *
     * @param weight This is the weight of the Product.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return quantity of the product
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * This method changes quantity of the product
     *
     * @param quantity This is the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return delivery cost of the product
     */
    public int getDeliveryCost() {
        return this.deliveryCost;
    }

    /**
     * This method changes deliveryCost of product
     *
     * @param deliveryCost Delivery cost of product
     */
    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * @return tax of the product
     */
    public int getTax() {
        return this.tax;
    }

    /**
     * This method changes tax of the product
     *
     * @param tax This is tax of the product
     */
    public void setTax(int tax) {
        this.tax = tax;
    }

    /**
     * This method is used to print all information about the product.
     */
    public void info() {
        System.out.println("Product Name : " + getName());
        System.out.println("Product Price : " + getBasePrice());
        System.out.println("Product Description : " + getDescription());
        System.out.println("Tax on Product: "+getTax());
        System.out.println("Shipping Charges on Product: "+getDeliveryCost());
        System.out.println("Units of Product Ordered: "+getQuantity());
    }



}
