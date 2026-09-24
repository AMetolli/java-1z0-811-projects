package bank;

import java.util.List;
import java.util.Optional;

public class BankService {

    private final List<BankAccount> accounts;

    public BankService() {
        this.accounts = AccountRepository.loadAccounts();
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public Optional<BankAccount> findAccount(String accountNumber) {
        return accounts.stream()
                .filter(acc -> acc.getAccountNumber().equalsIgnoreCase(accountNumber))
                .findFirst();
    }

    public BankAccount authenticate(String accountNumber, String pin) {
        Optional<BankAccount> accountOpt = findAccount(accountNumber);
        if (accountOpt.isPresent() && accountOpt.get().validatePin(pin)) {
            return accountOpt.get();
        }
        return null;
    }

    public boolean createAccount(String accountNumber, String accountHolder, double initialBalance, String pin) {
        if (findAccount(accountNumber).isPresent()) {
            System.err.println("Konto mit dieser Kontonummer existiert bereits!");
            return false;
        }

        if (initialBalance < 0) {
            System.err.println("Startguthaben darf nicht negativ sein!");
            return false;
        }

        BankAccount newAccount = new BankAccount(accountNumber, accountHolder, initialBalance, pin);
        accounts.add(newAccount);
        AccountRepository.saveAccounts(accounts);
        return true;
    }

    public boolean deposit(BankAccount account, double amount) {
        account.deposit(amount);
        AccountRepository.saveAccounts(accounts);
        return true;
    }

    public boolean withdraw(BankAccount account, double amount) {
        boolean success = account.withdraw(amount);
        if (success) {
            AccountRepository.saveAccounts(accounts);
        }
        return success;
    }

    public boolean transfer(BankAccount fromAccount, String toAccountNumber, double amount) {
        if (fromAccount.getAccountNumber().equalsIgnoreCase(toAccountNumber)) {
            System.err.println("Start- und Zielkonto dürfen nicht identisch sein!");
            return false;
        }

        Optional<BankAccount> toAccountOpt = findAccount(toAccountNumber);
        if (toAccountOpt.isEmpty()) {
            System.err.println("Zielkonto wurde nicht gefunden!");
            return false;
        }

        BankAccount toAccount = toAccountOpt.get();
        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            AccountRepository.saveAccounts(accounts);
            return true;
        }

        return false;
    }
}