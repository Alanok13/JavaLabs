package uz.alano.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.alano.warehouse.product.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void add(Product p) {
        products.add(p);
    }

    public boolean remove(String name){
        Product selectedProduct = null;
        for (Product p : products){
            if (p.getName().equals(name)){
                selectedProduct = p;
            }
        }

        if (selectedProduct == null){
            return false;
        }

        products.remove(selectedProduct);

        return true;
    }

    public Map<Class, List<Product>> groupProducts() {
        Map<Class, List<Product>> groupedProducts = new HashMap<>();
        for (Product p : products) {
            if (!groupedProducts.containsKey(p.getClass())) {
                groupedProducts.put(p.getClass(), new ArrayList<>());
            }

            groupedProducts.get(p.getClass()).add(p);
        }

        return groupedProducts;
    }

    public static Warehouse loadFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Warehouse.class);
    }

    public static void saveToFile(Warehouse warehouse, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), warehouse);
    }

//    public static void main(String[] args) {
//        Warehouse warehouse;
//        try {
//            warehouse = loadFromFile("warehouse.json");
//        } catch (IOException e) {
//
//            warehouse = createDefaultWarehouse();
//        }
//
//        try {
//            saveToFile(warehouse, "warehouse.json");
//        } catch (IOException e) {
//
//        }
//    }
}
