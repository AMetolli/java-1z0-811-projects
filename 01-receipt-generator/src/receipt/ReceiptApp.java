package receipt;

import java.util.Scanner;

public class ReceiptApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductCatalog catalog = new ProductCatalog();
        ReceiptService cart = new ReceiptService();

        System.out.println("=== Kassen-App gestartet ===");
        catalog.printCatalog();
        System.out.println("Tippe einen Produktnamen ein oder 'print', um den Bon zu drucken.");

        boolean running = true;
        while (running) {
            System.out.print("\nProdukt eingeben: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("print")) {
                running = false;
                continue;
            }

            Product product = catalog.findProduct(input);
            if (product == null) {
                System.out.println("Produkt nicht gefunden! Bitte versuche es noch einmal.");
                continue;
            }

            System.out.print("Menge: ");
            String qtyInput = scanner.nextLine().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(qtyInput);
                if (quantity <= 0) {
                    System.out.println("Menge muss größer als 0 sein.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine ganze Zahl eingeben.");
                continue;
            }

            
            cart.addItem(product, quantity);
            System.out.println(quantity + "x " + product.getName() + " hinzugefügt.");
        }

        
        cart.printReceipt();
        scanner.close();
    }
}