package uz.alano.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.alano.warehouse.comparators.*;
import uz.alano.warehouse.product.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    List<Product> products = new LinkedList<>();
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void add(Product p) {
        this.products.add(p);
    }

    public String FoodSortedByCalorie() {
        List<Food> foodList = this.groupProducts().get(Food.class)
                .stream()
                .map(p -> (Food) p)
                .collect(Collectors.toCollection(LinkedList::new));

        foodList.sort(new FoodComparatorByCalorie());

        return foodList.toString();
    }

    public String AppliancesSortedByInputPower() {
        List<Appliance> applianceList = this.groupProducts().get(Appliance.class)
                .stream()
                .map(p -> (Appliance) p)
                .collect(Collectors.toCollection(LinkedList::new));

        applianceList.sort(new ApplianceComparatorByInputPower());

        return applianceList.toString();
    }

    public String ClothesSortedBySize() {
        List<Clothes> clothesList = this.groupProducts().get(Clothes.class)
                .stream()
                .map(p -> (Clothes) p)
                .collect(Collectors.toCollection(LinkedList::new));

        clothesList.sort(new ClothesComparatorBySize());

        return clothesList.toString();
    }

    public String ProductsSortedByName() {
        return this.ProductsSorted(new ProductComparatorByName());
    }

    public String ProductsSortedByPrice() {
        return this.ProductsSorted(new ProductComparatorByPrice());
    }

    public String ProductsGroupedAndSortedByName() {
        return this.ProductsGroupedAndSorted(new ProductComparatorByName());
    }

    public String ProductsGroupedAndSortedByPrice() {
        return this.ProductsGroupedAndSorted(new ProductComparatorByPrice());
    }

    private Map<Class, List<Product>> groupProducts() {
        Map<Class, List<Product>> groupedProducts = new HashMap<>();
        for (Product p : this.products) {
            if (!groupedProducts.containsKey(p.getClass())) {
                groupedProducts.put(p.getClass(), new LinkedList<>());
            }

            groupedProducts.get(p.getClass()).add(p);
        }

        return groupedProducts;
    }

    private String ProductsSorted(Comparator<Product> comparator) {
        this.products.sort(comparator);

        return products.toString();
    }

    private String ProductsGroupedAndSorted(Comparator<Product> comparator) {
        Map<Class, List<Product>> groupedProducts = this.groupProducts();

        for (List<Product> list : groupedProducts.values()) {
            list.sort(comparator);
        }

        return groupedProducts.toString();
    }

    public static void main(String[] args) {
        Warehouse warehouse;
        try {
            warehouse = loadFromFile("warehouse.json");
        } catch (IOException e) {
            warehouse = createDefaultWarehouse();
        }

        System.out.println("Sorted by name:");
        System.out.println(warehouse.ProductsSortedByName());

        System.out.println("Sorted by price:");
        System.out.println(warehouse.ProductsSortedByPrice());

        System.out.println("Grouped by product type and sorted by name:");
        System.out.println(warehouse.ProductsGroupedAndSortedByName());

        System.out.println("Grouped by product type and sorted by price:");
        System.out.println(warehouse.ProductsGroupedAndSortedByPrice());

        System.out.println("Food sorted by calorie:");
        System.out.println(warehouse.FoodSortedByCalorie());

        System.out.println("Appliance sorted by input power:");
        System.out.println(warehouse.AppliancesSortedByInputPower());

        System.out.println("Clothes sorted by size:");
        System.out.println(warehouse.ClothesSortedBySize());

        try {
            saveToFile(warehouse, "warehouse.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Warehouse createDefaultWarehouse(){
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

    public static Warehouse loadFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path, Warehouse.class);
    }

    public static void saveToFile(Warehouse warehouse, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), warehouse);
    }

}
