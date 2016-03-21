package uz.alano.warehouse;

import uz.alano.warehouse.comparators.*;
import uz.alano.warehouse.product.Appliance;
import uz.alano.warehouse.product.Clothes;
import uz.alano.warehouse.product.Food;
import uz.alano.warehouse.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    private Warehouse warehouse;

    public ReportService(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public <T extends Product> String sortedProduct(Class<T> typeProduct, Comparator<T> comparator) {
        List<T> applianceList = warehouse.groupProducts().get(typeProduct)
                .stream()
                .map(p -> (T) p)
                .collect(Collectors.toCollection(ArrayList::new));

        applianceList.sort(comparator);

        return applianceList.toString();
    }

    public String sortedProduct(Comparator<Product> comparator) {
        warehouse.getProducts().sort(comparator);

        return warehouse.getProducts().toString();
    }

    private String productsGroupedAndSorted(Comparator<Product> comparator) {
        Map<Class, List<Product>> groupedProducts = warehouse.groupProducts();

        for (List<Product> list : groupedProducts.values()) {
            list.sort(comparator);
        }

        return groupedProducts.toString();
    }
}
