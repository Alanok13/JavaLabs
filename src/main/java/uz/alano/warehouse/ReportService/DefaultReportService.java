package uz.alano.warehouse.ReportService;

import uz.alano.warehouse.Warehouse;
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

public class DefaultReportService implements ReportService {

    @Override
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    private Warehouse warehouse;

    public DefaultReportService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String foodSortedByCalorie() {
        return sortedProduct(Food.class, new FoodComparatorByCalorie());
    }

    @Override
    public String clothesSortedBySize() {
        return sortedProduct(Clothes.class, new ClothesComparatorBySize());
    }

    @Override
    public String applianceSortedByInputPower() {
        return sortedProduct(Appliance.class, new ApplianceComparatorByInputPower());
    }

    @Override
    public String productsSortedByName() {
        return sortedProduct(new ProductComparatorByName());
    }

    @Override
    public String productsSortedByPrice() {
        return sortedProduct(new ProductComparatorByPrice());
    }

    @Override
    public String productsGroupedAndSortedByName() {
        return productsGroupedAndSorted(new ProductComparatorByName());
    }

    @Override
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
