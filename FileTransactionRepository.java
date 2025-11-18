import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileTransactionRepository implements TransactionRepository {
    private static final String FILE_NAME = "transactions.dat";
    private List<Transaction> transactions;

    public FileTransactionRepository() {
        this.transactions = loadFromFile();
    }

    @Override
    public void save(Transaction transaction) {
        this.transactions.add(transaction);
        saveToFile();
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(this.transactions); // Returnerar en kopia för inkapsling
    }

    @Override
    public Optional<Transaction> findById(int id) {
        return transactions.stream()
                           .filter(t -> t.getId() == id)
                           .findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        boolean removed = transactions.removeIf(t -> t.getId() == id);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    // Privat metod för att hantera filskrivning (Inkapsling av I/O-logik)
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this.transactions);
        } catch (IOException e) {
            System.err.println("Fel vid sparande till fil: " + e.getMessage());
        }
    }

    // Privat metod för att ladda data från fil (Inkapsling av I/O-logik)
    private List<Transaction> loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Suppress unchecked cast warning here, assuming we only store List<Transaction>
                return (List<Transaction>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Fel vid laddning från fil. Börjar med tom lista.");
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}