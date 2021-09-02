package entity.products;

import entity.Product;
import entity.Type;

public class Laptop extends Product {

    private String model;
    private String processor;
    private String videoCard;
    private String memory;

    public Laptop(Type type, String name, double price, String color, int quantityInStock, String model, String processor, String videoCard, String memory) {
        super(type, name, price, color, quantityInStock);
        this.model = model;
        this.processor = processor;
        this.videoCard = videoCard;
        this.memory = memory;
    }

    @Override
    public void showFeatures() {
        System.out.println(String.format("Model: %s GPU:%s VC:%s Memory:%s", this.model, this.processor, this.videoCard, this.memory));
    }
}
