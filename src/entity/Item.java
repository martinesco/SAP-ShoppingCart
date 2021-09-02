package entity;

public interface Item {

    Type getType();

    String getName();

    double getPrice();

    int getQuantityInStock();

    void setQuantityInStock(int quantityInStock);

    void showFeatures();


}
