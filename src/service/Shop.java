package service;

import entity.Product;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private static List<Product> inventory;


    public Shop() {
        inventory = new ArrayList<>();
    }

    public static List<Product> getInventory() {
        return inventory;
    }

    public void loadProduct(Product product) {
        inventory.add(product);
    }




}
