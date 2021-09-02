package entity.products;

import entity.Product;
import entity.Type;

public class Camera extends Product {

    public Camera(Type type, String name, double price, String color, int quantityInStock) {
        super(type, name, price, color, quantityInStock);
    }

    @Override
    public void showFeatures() {
        System.out.println();
    }
}
