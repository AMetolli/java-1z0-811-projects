package receipt;

public class Product {
    private String name;
    private double price;
    private TaxCategory taxCategory;

    public Product(String name, double price, TaxCategory taxCategory) {
        this.name = name;
        this.price = price;
        this.taxCategory = taxCategory;
    }

    public String getName() {
    return name;
    }

    public double getPrice() {
    return price;
    }

    public TaxCategory getTaxCategory() {
    return taxCategory;
    }

    public double getTaxRate() {
        return taxCategory.getRate();
    }
}