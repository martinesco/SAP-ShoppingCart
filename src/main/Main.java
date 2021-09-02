package main;

import service.Engine;
import service.Shop;
import service.ShoppingCart;
import service.io.FileReader;

public class Main {

    public static void main(String[] args) {


        ShoppingCart shoppingCart = new ShoppingCart();
        Shop shop = new Shop();
        FileReader.readProducts(shop);

        Engine engine = new Engine(shop, shoppingCart);
        engine.start();
    }
}
