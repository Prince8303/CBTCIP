import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String owner, double initialDeposit) {
        accounts.put(accountNumber, new Account(accountNumber, owner, initialDeposit));
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bank loadAccounts() {
        Bank bank = new Bank();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            bank.accounts = (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bank;
    }
}
