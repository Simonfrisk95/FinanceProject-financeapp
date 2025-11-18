import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    // Kontrakt för CRUD-operationer
    void save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(int id);
    boolean deleteById(int id);
}