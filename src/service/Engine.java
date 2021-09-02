package service;


import entity.Product;
import entity.Type;
import service.io.FIleWriter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Engine {

    private Shop shop;
    private ShoppingCart shoppingCart;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public Engine(Shop shop, ShoppingCart shoppingCart) {
        this.shop = shop;
        this.shoppingCart = shoppingCart;
    }

    public void start() {
        boolean executing = true;

        do {
            System.out.println("Choose an option: \n" +
                    "1. View all products \n" +
                    "2. Add product \n" +
                    "3. Remove product \n" +
                    "4. List the cart \n" +
                    "5. Finish shopping \n");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String command;

            switch (input) {
                case "1":
                    printAvailableProducts();
                    break;

                case "2":
                    command = "add";
                    shoppingCart.operate(command, shop.getInventory());
                    break;

                case "3":
                    command = "remove";
                    shoppingCart.operate(command, shop.getInventory());
                    break;

                case "4":
                    shoppingCart.showCart();
                    shoppingCart.showCost();
                    break;

                case "5":
                    executing = false;
                    try {
                        FIleWriter.giveReceipt(shoppingCart.getProducts(), shoppingCart.getCart());
                        FIleWriter.remainStock(shop.getInventory());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.err.println("Try again. Enter only one digit!");
                    break;
            }

        } while (executing);
    }

    private void printAvailableProducts() {
        List<Product> products = shop.getInventory();

        System.out.println("-------------------------------------------------");
        System.out.println("\t ### List of products: ###");

        int i = 1;
        for (Product product : products) {
            System.out.print(String.format("%d. %s ", i++, product.toString()));
            product.showFeatures();
        }

        System.out.println("-------------------------------------------------");
    }





}
