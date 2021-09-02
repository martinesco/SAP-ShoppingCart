package service.io;

import entity.Product;
import entity.Type;
import entity.products.Camera;
import entity.products.Laptop;
import entity.products.Phone;
import entity.products.Printer;
import service.Shop;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {


    public static void readProducts(Shop shop)  {

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader("Factory.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" -> ");
                String type = parts[0];

                String[] data = parts[1].split(", ");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                String color = data[2];
                int quantityInStock = Integer.parseInt(data[3]);

                switch (type) {

                    case "CAMERA":
                        shop.loadProduct(new Camera(Type.CAMERA, name, price, color, quantityInStock));
                        break;

                    case "PHONE":
                        String model = data[4];
                        String battery = data[5];
                        shop.loadProduct(new Phone(Type.PHONE, name, price, color, quantityInStock, model, battery));
                        break;

                    case "PRINTER":
                        boolean isCartridge = Boolean.parseBoolean(data[4]);
                        shop.loadProduct(new Printer(Type.PRINTER, name, price, color, quantityInStock, isCartridge));
                        break;

                    case "LAPTOP":
                        String m = data[4];
                        String processor = data[5];
                        String videoCard = data[6];
                        String memory = data[7];
                        shop.loadProduct(new Laptop(Type.LAPTOP, name, price, color, quantityInStock, m, processor, videoCard, memory));
                        break;

                    default:
                        System.out.println("An error occurred while reading the line: " + line);
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
