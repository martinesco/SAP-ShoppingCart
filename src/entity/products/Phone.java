package entity.products;

import entity.Product;
import entity.Type;

public class Phone extends Product {

    private String model;
    private String battery;

    public Phone(Type type, String name, double price, String color, int quantityInStock, String model, String battery) {
        super(type, name, price, color, quantityInStock);
        this.model = model;
        this.battery = battery;
    }

    @Override
    public void showFeatures() {
        System.out.println(String.format("Model: %s Battery:%s", this.model, this.battery));
    }
}

