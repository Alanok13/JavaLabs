package uz.alano.warehouse.ReportService;

import uz.alano.warehouse.Warehouse;

public interface ReportService {
    Warehouse getWarehouse();

    void setWarehouse(Warehouse warehouse);

    String foodSortedByCalorie();

    String clothesSortedBySize();

    String applianceSortedByInputPower();

    String productsSortedByName();

    String productsSortedByPrice();

    String productsGroupedAndSortedByName();

    String productsGroupedAndSortedByPrice();
}
