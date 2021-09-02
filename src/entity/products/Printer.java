package entity.products;

import entity.Product;
import entity.Type;

public class Printer extends Product {

    private boolean isCartridge;

    public Printer(Type type, String name, double price, String color, int quantityInStock, boolean isCartridge) {
        super(type, name, price, color, quantityInStock);
        this.isCartridge = isCartridge;
    }

    public String getIsCartridge() {
        if (isCartridge) {
            return "Yes";
        }
        return "No";
    }

    @Override
    public void showFeatures() {
        System.out.println(String.format("Cartridge: %s", getIsCartridge()));
    }
}
