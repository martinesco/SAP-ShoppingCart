package service;

import entity.Product;
import entity.Type;
import java.util.*;

public class ShoppingCart {

    private static Map<String, Product> products = new LinkedHashMap<>();
    private static Map<Type, Map<String, Integer>> cart = new HashMap<>();

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static double totalCost = 0;
    private static int itemsCount = 0;
    private static double sumOfType = 0;
    private static final double DISCOUNT_10 = (1 - 0.1);

  //  Engine engine;


    public void operate(String command, List<Product> products) {

        System.out.println("## Enter the name of the item you want to " + command);

        Scanner input2 = new Scanner(System.in);
        String productName = input2.nextLine();

        for (Product product : products) {

            if (product.getName().equals(productName)) {
                Type type = product.getType();

                if (command.equals("add")) {
                    addProduct(productName, type);
                } else if (command.equals("remove")) {
                    removeProduct(productName, type);
                }
                return;
            }
        }
        System.err.println("There is no product with this name!");
        System.err.println("Try again. Enter only the name of the product!");

    }

    private void addProduct(String productName, Type type) {
        if (!products.containsKey(productName)) {
            products.put(productName, addProductToCart(productName, type));

            if (!cart.containsKey(type)) {
                cart.put(type, new LinkedHashMap<>());
                cart.get(type).put(productName, 1);
            } else {
                cart.get(type).put(productName, 1);
            }
            itemsCount++;

        } else {
            if (products.get(productName).getQuantityInStock() > 0) {
                Product product = products.get(productName);
                product.setQuantityInStock(product.getQuantityInStock() - 1);
                cart.get(type).put(productName, cart.get(type).get(productName) + 1);
                itemsCount++;
                System.out.println(String.format("%s %s quantity has been increased by 1!%s", ANSI_GREEN, productName, ANSI_RESET));
            } else {
                System.err.println("Stock is finished!!!");
            }
        }

    }

    private void removeProduct(String productName, Type type) {
        if (itemsCount == 0) {
            System.out.println("Your shopping cart is empty!");
            return;
        }
        if (cart.get(type).get(productName) > 0) {
            returnProduct(productName, type);
            cart.get(type).put(productName, cart.get(type).get(productName) - 1);

            if (cart.get(type).get(productName) == 0) {
                products.remove(productName);
                cart.get(type).remove(productName);
            }

            itemsCount--;
        } else {
            System.err.println("You do not have this item!!!");
        }
    }

    public void showCart() {
        if (itemsCount == 0) {
            System.out.println("Cart is empty!!!");
        } else {
            System.out.println("Your items :");

            for (Product product : products.values()) {
                if (product != null) {
                    System.out.println(String.format("Product: %s%s%s Amount: %d", ANSI_CYAN, product.getName(), ANSI_RESET, cart.get(product.getType()).get(product.getName())));
                }
            }
            System.out.println("Total items : " + itemsCount);
        }
    }

    public void showCost() {
        totalCost = 0;
        System.out.println("If there are more than 2 product of the same type, their price is discounted with 10%");

        totalCost = calculateCartCost();
        System.out.println(String.format("Total cost: %s%.2f%s $", ANSI_GREEN, totalCost, ANSI_RESET));
    }

    public static double getCost() {
        totalCost = 0;
        totalCost = calculateCartCost();

        return totalCost;
    }

    private static double calculateCartCost() {

        cart.forEach((type, innerMap) -> {
            innerMap.forEach((name, amount) -> {
                if (innerMap.size() >= 2) {
                    sumOfType += products.get(name).getPrice() * amount * DISCOUNT_10;
                } else {
                    sumOfType += products.get(name).getPrice() * amount;
                }
            });

            System.out.println(String.format("Sum of products of type %s is: %.2f", type, sumOfType));
            totalCost += sumOfType;
            sumOfType = 0;
        });

        if (totalCost > 100.00) {
            totalCost *= DISCOUNT_10;
            System.out.println("Your shopping cart has reached 100$ and you have 10% total price discount");
        }

        return totalCost;
    }


    public Map<String, Product> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public Map<Type, Map<String, Integer>> getCart() {
        return Collections.unmodifiableMap(cart);
    }


    public Product addProductToCart(String name, Type type) {

        for (Product product : Shop.getInventory()) {
            if (product.getName().equals(name) && product.getType().equals(type)) {

                product.setQuantityInStock(product.getQuantityInStock() - 1);
                System.out.println(String.format("%sThe product has been successfully added to the cart!%s", ANSI_GREEN, ANSI_RESET));
                return product;
            }
        }
        System.err.println("Something went wrong!");
        return null;
    }

    public void returnProduct(String name, Type type) {

        for (Product product : Shop.getInventory()) {
            if (product.getName().equals(name) && product.getType().equals(type)) {

                product.setQuantityInStock(product.getQuantityInStock() + 1);
                System.out.println(String.format("%sYou successfully returned a product!%s", ANSI_GREEN, ANSI_RESET));
                return;
            }
        }
    }

}
