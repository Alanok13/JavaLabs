package uz.alano.warehouse;

import uz.alano.warehouse.ReportService.DefaultReportService;
import uz.alano.warehouse.ReportService.ReportService;
import uz.alano.warehouse.product.Appliance;
import uz.alano.warehouse.product.Clothes;
import uz.alano.warehouse.product.Food;
import uz.alano.warehouse.product.Product;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WarehouseConsoleMenu {
    private final static Logger LOGGER = Logger.getLogger(Warehouse.class.getSimpleName());
    private Scanner in = new Scanner(System.in);
    private ReportService reportService;
    private Warehouse warehouse;
    public ReportService getReportService() {
        return reportService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void init() {
        System.out.println("Enter: " +
                "\n1 - Create empty warehouse" +
                "\n2 - Load warehouse from file" +
                "\n3 - Create warehouse with default content");
        switch (in.nextByte()) {
            case 1: {
                warehouse = new Warehouse();
                reportService = new DefaultReportService(warehouse);

                break;
            }
            case 2: {
                while (true) {
                    System.out.println("Enter path to file: ");
                    try {
                        warehouse = Warehouse.loadFromFile(in.next());
                        reportService = new DefaultReportService(warehouse);
                    } catch (IOException e) {
                        System.out.println("Incorrect path. Try again.");
                        continue;
                    }

                    break;
                }

                break;
            }
            case 3: {
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

                reportService = new DefaultReportService(warehouse);
            }
        }
    }

    public void runMainMenu() {
        while (true) {
            System.out.println("Change action: " +
                    "\n1 - Add product" +
                    "\n2 - Remove product" +
                    "\n3 - Show all products" +
                    "\n4 - Show reports" +
                    "\n5 - Save to file" +
                    "\n6 - Exit");
            switch (in.nextByte()) {
                case 1: {
                    warehouse.add(createProductMenu());
                    break;
                }
                case 2: {
                    warehouse.remove(removeProductsMenu());
                    break;
                }
                case 3: {
                    System.out.println(warehouse.getProducts());
                    break;
                }
                case 4: {
                    reportMenu();
                    break;
                }
                case 5: {
                    saveMenu();
                    break;
                }
                case 6:{
                    return;
                }
            }
        }
    }

    private void saveMenu() {
        System.out.print("Enter file name:");
        String name = in.next();
        try {
            Warehouse.saveToFile(warehouse, name);
        }
        catch (IOException e){
            LOGGER.log(Level.WARNING, "Saving to file failed.");
            System.out.println("Saving failed.");
        }
    }

    private void reportMenu() {
        System.out.println("Change report: " +
                "\n1 - Sorted by name" +
                "\n2 - Sorted by price" +
                "\n3 - Grouped by product type and sorted by name" +
                "\n4 - Grouped by product type and sorted by price" +
                "\n5 - Food sorted by calorie" +
                "\n6 - Appliance sorted by input power" +
                "\n7 - Clothes sorted by size" +
                "\n");

        switch (in.nextByte()) {
            case 1: {
                System.out.println(reportService.productsSortedByName());
                break;
            }
            case 2: {
                System.out.println(reportService.productsSortedByPrice());
                break;
            }
            case 3: {
                System.out.println(reportService.productsGroupedAndSortedByName());
                break;
            }
            case 4: {
                System.out.println(reportService.productsGroupedAndSortedByPrice());
                break;
            }
            case 5: {
                System.out.println(reportService.foodSortedByCalorie());
                break;
            }
            case 6: {
                System.out.println(reportService.applianceSortedByInputPower());
                break;
            }
            case 7: {
                System.out.println(reportService.clothesSortedBySize());
                break;
            }
        }
    }

    private Product createProductMenu() {
        System.out.println("Change product type: " +
                "\n1 - Appliance" +
                "\n2 - Clothes" +
                "\n3 - Food");
        byte type = in.nextByte();

        System.out.print("EAN: ");
        long ean = in.nextLong();
        System.out.print("Price: ");
        double price = in.nextDouble();
        System.out.print("Name: ");
        String name = in.next();

        Product product = null;
        switch (type) {
            case 1: {

                System.out.print("Input power: ");
                int inputPower = in.nextInt();
                product = new Appliance(ean, price, name, inputPower);

                break;
            }
            case 2: {
                System.out.print("Size: ");
                byte size = in.nextByte();
                System.out.print("Material: ");
                String material = in.next();
                product = new Clothes(ean, price, name, size, material);

                break;
            }
            case 3: {
                System.out.print("Calorie: ");
                int calorie = in.nextInt();

                System.out.print("Date: ");
                String date = in.next();
                String[] splitDate = date.split("-");
                int days = Integer.parseInt(splitDate[0]);
                int month = Integer.parseInt(splitDate[1]);
                int year = Integer.parseInt(splitDate[2]);
                GregorianCalendar creationDate = new GregorianCalendar(days, month, year);

                System.out.print("Expiration time: ");
                int expirationTime = in.nextInt();

                product = new Food(ean, price, name, calorie, creationDate, expirationTime);
            }
        }

        return product;
    }

    private String removeProductsMenu() {
        System.out.print("Input product name: ");

        return (in.next());
    }
}
