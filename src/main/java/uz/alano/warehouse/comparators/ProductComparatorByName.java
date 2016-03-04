package uz.alano.warehouse.comparators;

import uz.alano.warehouse.product.Product;

import java.util.Comparator;

public class ProductComparatorByName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}


