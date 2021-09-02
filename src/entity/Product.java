package entity;

public abstract class Product implements Item {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private Type type;
    private String name;
    private double price;
    private String color;
    private int quantityInStock;

    public Product(Type type, String name, double price, String color, int quantityInStock) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void showFeatures() {

    }

    @Override
    public String toString() {
        return String.format("Type: %s --> Name: %s%s%s Price: %.2f$ Color: %s Quantity: %s",
                this.type,
                ANSI_BLUE, this.name, ANSI_RESET,
                this.price,
                this.color,
                this.quantityInStock);
    }
}
