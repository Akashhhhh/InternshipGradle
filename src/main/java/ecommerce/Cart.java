package ecommerce;

import java.util.Scanner;
/**
 * This class is the Cart interface.
 *
 * @author Akash Gupta
 */
public class Cart {
    /**
     * This class takes input from user as what all to add in cart and then in end it shows all items added
     in cart.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Books[] books = new Books[2];
        Clothes[] clothes = new Clothes[2];
        Laptop[] laptop = new Laptop[2];
        Bikes[] bikes = new Bikes[2];
        Product[] products = new Product[10];
        books[0] = new Books("Harry Potter", 100, "Science Fiction", 200, 1);
        books[1] = new Books("A Promised Land", 200, "Romantic", 300, 1);
        clothes[0] = new Clothes("Shirt", 1000, "Casual", 200, 1);
        clothes[1] = new Clothes("Jean", 2000, "Black Denim", 300, 1);
        laptop[0] = new Laptop("Dell", 100, "16gb ram", 1, 1);
        laptop[1] = new Laptop("Mac", 30, "Too expensive", 1, 1);
        bikes[0] = new Bikes("Kawasaki", 71900, "bs6", 500, 1);
        bikes[1] = new Bikes("Hayabusa", 42999, "bs4", 600, 1);
        int i = 0;
        int totalCartValue = 0;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.println("List of items: \n1. Books \n2. Clothes \n3. Laptop \n4. Bikes\n" +
                    "Enter the category name you want: \n" +
                    "To view the cart type 'Show'\n" +
                    "To finish the shopping type 'Exit'\n"
            );

            String category = sc.nextLine();
            final String action1 = "Enter the product name: ";
            final String action2 = "Enter the quantity: ";
            final String action3 = "Added to cart";
            switch (category) {
                case "Books":
                    System.out.println(books[0].getName());
                    System.out.println(books[1].getName());
                    System.out.println(action1);


                    String s1 = sc.nextLine();
                    switch (s1) {
                        case "Harry Potter":
                            System.out.println(action2);

                            int qt = sc.nextInt();
                            products[i] = new Product(books[0].getName(), books[0].getBasePrice(),
                                    books[0].getDescription(), books[0].getWeight(),
                                    qt, books[0].getDeliveryCost(), books[0].getTax());
                            System.out.println();
                            i++;
                            break;
                        case "A Promised Land":
                            System.out.println(action2);

                            qt = sc.nextInt();
                            products[i] = new Product(books[1].getName(), books[1].getBasePrice(),
                                    books[1].getDescription(), books[1].getWeight(),
                                    qt, books[1].getDeliveryCost(), books[1].getTax());
                            System.out.println(action3);
                            i++;
                            break;
                        default:
                            continue;
                    }
                    break;
                case "Clothes":
                    System.out.println(clothes[0].getName());
                    System.out.println(clothes[1].getName());
                    System.out.println(action1);

                    String s2 = sc.nextLine();
                    switch (s2) {
                        case "Shirt":
                            System.out.println(action2);

                            int qt = sc.nextInt();
                            products[i] = new Product(clothes[0].getName(), clothes[0].getBasePrice(),
                                    clothes[0].getDescription(), clothes[0].getWeight(),
                                    qt, clothes[0].getDeliveryCost(), clothes[0].getTax());
                            System.out.println(action3);
                            i++;
                            break;
                        case "Jean":
                            System.out.println(action2);

                            qt = sc.nextInt();
                            products[i] = new Product(clothes[1].getName(), clothes[1].getBasePrice(),
                                    clothes[1].getDescription(), clothes[1].getWeight(),
                                    qt, clothes[1].getDeliveryCost(), clothes[1].getTax());

                            System.out.println(action3);
                            i++;
                            break;
                        default:
                            continue;
                    }
                    break;
                case "Laptop":
                    System.out.println(laptop[0].getName());
                    System.out.println(laptop[1].getName());
                    System.out.println(action1);

                    String s3 = sc.nextLine();
                    switch (s3) {
                        case "Dell":
                            System.out.println(action2);
                            sc = new Scanner(System.in);
                            int qt = sc.nextInt();
                            products[i] = new Product(laptop[0].getName(), laptop[0].getBasePrice(), laptop[0].getDescription(),
                                    laptop[0].getWeight(),
                                    qt, laptop[0].getDeliveryCost(), laptop[0].getTax());
                            System.out.println(action3);
                            i++;
                            break;
                        case "Mac":
                            System.out.println(action2);

                            qt = sc.nextInt();
                            products[i] = new Product(laptop[1].getName(), laptop[1].getBasePrice(), laptop[1].getDescription(),
                                    laptop[1].getWeight(),
                                    qt, laptop[1].getDeliveryCost(), laptop[1].getTax());
                            System.out.println(action3);
                            i++;
                            break;
                        default:
                            continue;
                    }
                    break;
                case "Bikes":
                    System.out.println(bikes[0].getName());
                    System.out.println(bikes[1].getName());
                    System.out.println(action1);

                    String s4 = sc.nextLine();
                    switch (s4) {
                        case "Kawasaki":
                            System.out.println(action2);

                            int qt = sc.nextInt();
                            products[i] = new Product(bikes[0].getName(), bikes[0].getBasePrice(),
                                    bikes[0].getDescription(), bikes[0].getWeight(),
                                    qt, bikes[0].getDeliveryCost(), bikes[0].getTax());
                            System.out.println(action3);

                            i++;
                            break;
                        case "Hayabusa":
                            System.out.println(action2);

                            qt = sc.nextInt();
                            products[i] = new Product(bikes[1].getName(), bikes[1].getBasePrice(),
                                    bikes[1].getDescription(), bikes[1].getWeight(),
                                    qt, bikes[1].getDeliveryCost(), bikes[1].getTax());
                            System.out.println(action3);
                            i++;
                            break;
                        default:
                            continue;
                    }
                    break;
                case "Exit":
                    flag = false;
                    break;
                case "Show":
                    if (i == 0) System.out.println("Cart is empty");
                    else {
                        for (int j = 0; j < i; j++) {
                            products[j].info();

                        }
                    }
                    break;
                default:
                    continue;
            }

        }
        System.out.println("Your Final Purchase");
        if (i == 0) {
            System.out.println("Cart is empty");
        } else {
            for (int j = 0; j < i; j++) {
                products[j].info();
                totalCartValue+=(products[j].getBasePrice() +
                        (products[j].getTax()*products[j].getBasePrice())/100 +
                        (products[j].getDeliveryCost()*products[j].getWeight())/100)*products[j].getQuantity();
            }
        }

        System.out.println("TotalCartValue: " + totalCartValue);
    }
}
