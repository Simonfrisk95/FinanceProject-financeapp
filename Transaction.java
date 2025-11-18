import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L; // För filsparning
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    private final int id;
    private final LocalDate date;
    private final double amount;
    private final String description;
    private final String category;

    public Transaction(double amount, String description, String category) {
        this.id = counter.incrementAndGet();
        this.date = LocalDate.now();
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    // Getters för inkapslade fält
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Datum: %s | Belopp: %.2f kr | Kategori: %s | Beskrivning: %s",
                             id, date, amount, category, description);
    }
}