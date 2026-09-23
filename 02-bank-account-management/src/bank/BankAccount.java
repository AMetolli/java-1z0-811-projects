package bank;

public class BankAccount {
    
    private String accountNumber; 
    private String accountHolder; 
    private double balance;
    private String pin;

    public BankAccount(String accountNumber, String accountHolder, double balance, String pin) {
        this.accountNumber = accountNumber; 
        this.accountHolder = accountHolder;
        this.balance = balance; 
        this.pin = pin;
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return String.format("Konto [%s] | Inhaber: %s | Kontostand: %.2f €", 
                accountNumber, accountHolder, balance);
    }
}