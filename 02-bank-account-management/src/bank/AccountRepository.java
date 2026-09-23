package bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static final Path PATH = Path.of("..", "..", "data", "accounts.json");

    private AccountRepository() {
    }

    public static List<BankAccount> loadAccounts() {
        if (!Files.exists(PATH)) {
            return new ArrayList<>();
        }

        try {
            String jsonText = Files.readString(PATH);
            return AccountJsonParser.parseAccounts(jsonText);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveAccounts(List<BankAccount> accounts) {
        try {
            if (PATH.getParent() != null && !Files.exists(PATH.getParent())) {
                Files.createDirectories(PATH.getParent());
            }

            String jsonText = AccountJsonParser.toJson(accounts);
            Files.writeString(PATH, jsonText);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
        }
    }
}