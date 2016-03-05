package uz.alano.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;

import uz.alano.warehouse.comparators.ApplianceComparatorByInputPower;
import uz.alano.warehouse.comparators.ClothesComparatorBySize;
import uz.alano.warehouse.comparators.FoodComparatorByCalorie;
import uz.alano.warehouse.comparators.ProductComparatorByName;
import uz.alano.warehouse.comparators.ProductComparatorByPrice;
import uz.alano.warehouse.product.Product;
import uz.alano.warehouse.product.Appliance;
import uz.alano.warehouse.product.Clothes;
import uz.alano.warehouse.product.Food;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Warehouse {
    private final static Logger LOGGER = Logger.getLogger(Warehouse.class.getSimpleName());

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

    public String foodSortedByCalorie() {
        return sortedProduct(Food.class, new FoodComparatorByCalorie());
    }

    public String clothesSortedBySize() {
        return sortedProduct(Clothes.class, new ClothesComparatorBySize());
    }

    public String applianceSortedByInputPower() {
        return sortedProduct(Appliance.class, new ApplianceComparatorByInputPower());
    }

    public <T extends Product> String sortedProduct(Class<T> typeProduct, Comparator<T> comparator) {
        List<T> applianceList = groupProducts().get(typeProduct)
                .stream()
                .map(p -> (T) p)
                .collect(Collectors.toCollection(ArrayList::new));

        applianceList.sort(comparator);

        return applianceList.toString();
    }

    public String sortedProduct(Comparator<Product> comparator) {
        products.sort(comparator);

        return products.toString();
    }

    public String productsSortedByName() {
        return sortedProduct(new ProductComparatorByName());
    }

    public String productsSortedByPrice() {
        return sortedProduct(new ProductComparatorByPrice());
    }

    public String productsGroupedAndSortedByName() {
        return productsGroupedAndSorted(new ProductComparatorByName());
    }

    public String productsGroupedAndSortedByPrice() {
        return productsGroupedAndSorted(new ProductComparatorByPrice());
    }

    private Map<Class, List<Product>> groupProducts() {
        Map<Class, List<Product>> groupedProducts = new HashMap<>();
        for (Product p : products) {
            if (!groupedProducts.containsKey(p.getClass())) {
                groupedProducts.put(p.getClass(), new ArrayList<>());
            }

            groupedProducts.get(p.getClass()).add(p);
        }

        return groupedProducts;
    }

    private String productsGroupedAndSorted(Comparator<Product> comparator) {
        Map<Class, List<Product>> groupedProducts = groupProducts();

        for (List<Product> list : groupedProducts.values()) {
            list.sort(comparator);
        }

        return groupedProducts.toString();
    }

    private static Warehouse createDefaultWarehouse(){
        Warehouse warehouse = new Warehouse();
        warehouse.add(new Food(2, 10, "Apple", 10, new GregorianCalendar(2015, 2, 1), 5));
        warehouse.add(new Food(2, 25, "Potato", 30, new GregorianCalendar(2015, 2, 1), 20));
        warehouse.add(new Food(3, 50, "Chips", 30, new GregorianCalendar(2014, 4, 22), 30));
        warehouse.add(new Food(4, 60, "Marshmallow", 20, new GregorianCalendar(2015, 7, 13), 30));
        warehouse.add(new Food(5, 22, "Bread", 20, new GregorianCalendar(2015, 8, 11), 20));
        warehouse.add(new Appliance(6, 500, "Mixer", 220));
        warehouse.add(new Appliance(7, 1000, "Drill", 220));
        warehouse.add(new Appliance(8, 5000, "TV", 220));
        warehouse.add(new Appliance(9, 100, "Flashlight", 1));
        warehouse.add(new Appliance(10, 150, "Charger", 5));
        warehouse.add(new Clothes(11, 150, "T-Shirt", (byte) 35, "Cotton"));
        warehouse.add(new Clothes(12, 300, "Bikini", (byte) 55, "Plastic"));
        warehouse.add(new Clothes(13, 150, "Short", (byte) 33, "Shorts"));
        warehouse.add(new Clothes(14, 350, "Jeans", (byte) 44, "Denim"));
        warehouse.add(new Clothes(15, 250, "Pullover", (byte) 35, "Wool"));

        return warehouse;
    }

    private static Warehouse loadFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path, Warehouse.class);
    }

    private static void saveToFile(Warehouse warehouse, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), warehouse);
    }

    public static void main(String[] args) {
        Warehouse warehouse;
        try {
            warehouse = loadFromFile("warehouse.json");
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "File not found.");
            warehouse = createDefaultWarehouse();
        }

        System.out.println("Sorted by name:");
        System.out.println(warehouse.productsSortedByName());

        System.out.println("Sorted by price:");
        System.out.println(warehouse.productsSortedByPrice());

        System.out.println("Grouped by product type and sorted by name:");
        System.out.println(warehouse.productsGroupedAndSortedByName());

        System.out.println("Grouped by product type and sorted by price:");
        System.out.println(warehouse.productsGroupedAndSortedByPrice());

        System.out.println("Food sorted by calorie:");
        System.out.println(warehouse.foodSortedByCalorie());

        System.out.println("Appliance sorted by input power:");
        System.out.println(warehouse.applianceSortedByInputPower());

        System.out.println("Clothes sorted by size:");
        System.out.println(warehouse.clothesSortedBySize());

        try {
            saveToFile(warehouse, "warehouse.json");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Saving to file failed.");
        }
    }
}
