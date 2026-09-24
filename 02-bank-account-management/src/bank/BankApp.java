package bank;

import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== WILLKOMMEN BEI DER BANK ===");
        System.out.print("Haben Sie bereits ein Konto? (j/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        BankAccount currentAccount = null;

        if (answer.equals("j")) {
            System.out.print("Kontonummer: ");
            String accNum = scanner.nextLine().trim();
            System.out.print("PIN: ");
            String pin = scanner.nextLine().trim();

            currentAccount = bankService.authenticate(accNum, pin);

            if (currentAccount == null) {
                System.err.println("Fehler: Ungültige Kontonummer oder PIN!");
                scanner.close();
                return;
            }
            System.out.println("\nLogin erfolgreich! Willkommen " + currentAccount.getAccountHolder());

        } else if (answer.equals("n")) {
            System.out.print("Wählen Sie eine Kontonummer: ");
            String accNum = scanner.nextLine().trim();
            System.out.print("Ihr Name: ");
            String holder = scanner.nextLine().trim();
            System.out.print("Startguthaben: ");
            
            try {
                double initialBalance = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Legen Sie eine PIN fest: ");
                String pin = scanner.nextLine().trim();

                if (bankService.createAccount(accNum, holder, initialBalance, pin)) {
                    System.out.println("Konto erfolgreich angelegt!");
                    currentAccount = bankService.authenticate(accNum, pin);
                } else {
                    scanner.close();
                    return;
                }
            } catch (NumberFormatException e) {
                System.err.println("Ungültiges Startguthaben!");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Ungültige Eingabe. Programm wird beendet.");
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== KONTO-MENÜ (" + currentAccount.getAccountNumber() + ") ===");
            System.out.println("1. Kontostand anzeigen");
            System.out.println("2. Geld einzahlen");
            System.out.println("3. Geld abheben");
            System.out.println("4. Geld überweisen");
            System.out.println("5. Beenden");
            System.out.print("Wahl: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println(currentAccount);
                case "2" -> {
                    System.out.print("Einzahlungsbetrag: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        if (bankService.deposit(currentAccount, amount)) {
                            System.out.println("Einzahlung erfolgreich! Neuer Kontostand: " + currentAccount.getBalance() + " €");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ungültige Eingabe!");
                    }
                }
                case "3" -> {
                    System.out.print("Auszahlungsbetrag: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        if (bankService.withdraw(currentAccount, amount)) {
                            System.out.println("Auszahlung erfolgreich! Neuer Kontostand: " + currentAccount.getBalance() + " €");
                        } else {
                            System.err.println("Auszahlung fehlgeschlagen (nicht genügend Guthaben).");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ungültige Eingabe!");
                    }
                }
                case "4" -> {
                    System.out.print("Ziel-Kontonummer: ");
                    String toAcc = scanner.nextLine().trim();
                    System.out.print("Überweisungsbetrag: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        if (bankService.transfer(currentAccount, toAcc, amount)) {
                            System.out.println("Überweisung erfolgreich!");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ungültige Eingabe!");
                    }
                }
                case "5" -> {
                    running = false;
                    System.out.println("Erfolgreich ausgeloggt. Auf Wiedersehen!");
                }
                default -> System.err.println("Ungültige Auswahl (1-5).");
            }
        }

        scanner.close();
    }
}