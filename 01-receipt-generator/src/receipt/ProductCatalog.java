package receipt;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> catalog = new ArrayList<>();

    public ProductCatalog() {
        
        catalog.add(new Product("Milch", 0.99, TaxCategory.REDUCED));
        catalog.add(new Product("Apfel", 0.49, TaxCategory.REDUCED));
        catalog.add(new Product("Cola", 1.49, TaxCategory.STANDARD));
        catalog.add(new Product("Schokolade", 1.29, TaxCategory.REDUCED));
        catalog.add(new Product("Kopfhörer", 19.99, TaxCategory.STANDARD));
    }

    
    public Product findProduct(String name) {
        for (Product p : catalog) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

  
    public void printCatalog() {
        System.out.println("--- Verfügbares Sortiment ---");
        for (Product p : catalog) {
            System.out.println("- " + p.getName() + " (" + p.getPrice() + " EUR)");
        }
        System.out.println("-----------------------------");
    }
}