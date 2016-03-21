package uz.alano.warehouse;

public class Bootstrap {
    public static void main(String[] args) {
        WarehouseConsoleMenu menu = new WarehouseConsoleMenu();
        menu.init();
        menu.runMainMenu();
    }
}
