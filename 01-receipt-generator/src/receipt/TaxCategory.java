package receipt;

public enum TaxCategory {
    REDUCED(0.07),   
    STANDARD(0.19);  

    private final double rate;

    TaxCategory(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}