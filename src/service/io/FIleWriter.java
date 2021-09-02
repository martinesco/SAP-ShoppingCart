package service.io;

import entity.Product;
import entity.Type;
import service.ShoppingCart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FIleWriter {

    public static void giveReceipt(Map<String, Product> cart, Map<Type, Map<String, Integer>> amount) throws IOException {
        BufferedWriter output = null;
        try {
            File file = new File("Receipt.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write("Your have bought: \n");

            for (Product product : cart.values()) {
                if (product != null) {
                    output.write(String.format("Product name: %s Amount: [%d] Original unit price: %.2f $ %n", product.getName(), amount.get(product.getType()).get(product.getName()), product.getPrice()));
                }
            }
            output.write(String.format("The price you paid is: %.2f $ ", ShoppingCart.getCost()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                output.close();
        }
    }

    public static void remainStock(List<Product> inventory) throws IOException {
        BufferedWriter output = null;
        try {
            File file = new File("Available_Items.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write("Inventory has : \n");

            for (Product product : inventory) {
                output.write(String.format("Amount: (%d) \t Product name: %s %n", product.getQuantityInStock(), product.getName()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                output.close();
        }
    }



}
