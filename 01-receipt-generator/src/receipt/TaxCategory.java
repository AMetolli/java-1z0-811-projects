public enum TaxCategory {
    STANDARD(0.19),
    REDUCED(0.07); 


    private final double taxRate; 

    private TaxCategory(double taxRate) {
    this.taxRate = taxRate;
    }

    public double getTaxRate() {
    return taxRate;

    }

} 

