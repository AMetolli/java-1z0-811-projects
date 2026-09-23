package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountJsonParser {

    private static final Pattern ACCOUNT_PATTERN = Pattern.compile(
        "\"accountNumber\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*" +
        "\"accountHolder\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*" +
        "\"balance\"\\s*:\\s*([0-9.]+)\\s*,\\s*" +
        "\"pin\"\\s*:\\s*\"([^\"]+)\""
    );

    private AccountJsonParser() {
    }

    public static List<BankAccount> parseAccounts(String jsonText) {
        List<BankAccount> accounts = new ArrayList<>();
        Matcher matcher = ACCOUNT_PATTERN.matcher(jsonText);

        while (matcher.find()) {
            String accountNumber = matcher.group(1);
            String accountHolder = matcher.group(2);
            double balance = Double.parseDouble(matcher.group(3));
            String pin = matcher.group(4);

            accounts.add(new BankAccount(accountNumber, accountHolder, balance, pin));
        }

        return accounts;
    }

    public static String toJson(List<BankAccount> accounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            sb.append("  {\n");
            sb.append("    \"accountNumber\": \"").append(acc.getAccountNumber()).append("\",\n");
            sb.append("    \"accountHolder\": \"").append(acc.getAccountHolder()).append("\",\n");
            sb.append("    \"balance\": ").append(acc.getBalance()).append(",\n");
            sb.append("    \"pin\": \"").append(acc.getPin()).append("\"\n");
            sb.append("  }");

            if (i < accounts.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }
}