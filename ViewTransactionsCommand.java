import java.util.List;
public class ViewTransactionsCommand implements Command {
    private final PersonalFinanceManager manager;

    public ViewTransactionsCommand(PersonalFinanceManager manager) {
        this.manager = manager;
    }

    @Override
    public String getCommandName() {
        return "Visa alla transaktioner och balans";
    }

    @Override
    public void execute() {
        System.out.println("\n--- Alla Transaktioner ---");
        List<Transaction> transactions = manager.getAllTransactions();
        
        if (transactions.isEmpty()) {
            System.out.println("Inga transaktioner registrerade.");
        } else {
            transactions.forEach(System.out::println);
        }

        double balance = manager.calculateTotalBalance();
        System.out.printf("\nTOTAL BALANS: %.2f kr\n", balance);
        System.out.println("--------------------------\n");
    }
}