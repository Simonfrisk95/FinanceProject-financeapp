import java.util.List;
import java.util.Optional;

public class PersonalFinanceManager {
    // Dependency Injection: Manager kapslar in och använder Repository
    private final TransactionRepository repository;

    // Manager tar emot en implementation av Repository via konstruktorn
    public PersonalFinanceManager(TransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(Transaction transaction) {
        repository.save(transaction);
        System.out.println("-> Transaktion med ID " + transaction.getId() + " tillagd och sparad.");
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public boolean deleteTransaction(int id) {
        boolean success = repository.deleteById(id);
        if (success) {
            System.out.println("-> Transaktion med ID " + id + " borttagen.");
        } else {
            System.out.println("-> Fel: Hittade ingen transaktion med ID " + id + ".");
        }
        return success;
    }

    public double calculateTotalBalance() {
        // Enkel affärslogik
        return repository.findAll().stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    
    // Ytterligare metoder för rapporter, filtrering, etc. kan läggas till här
}