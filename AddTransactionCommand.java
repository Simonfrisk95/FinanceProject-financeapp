import java.util.Scanner;

public class AddTransactionCommand implements Command {
    private final PersonalFinanceManager manager;
    private final Scanner scanner;

    public AddTransactionCommand(PersonalFinanceManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public String getCommandName() {
        return "Lägg till transaktion";
    }

    @Override
    public void execute() {
        try {
            System.out.print("Ange belopp (positivt för intäkt, negativt för kostnad): ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Ange beskrivning: ");
            String description = scanner.nextLine();

            System.out.print("Ange kategori: ");
            String category = scanner.nextLine();

            Transaction newTransaction = new Transaction(amount, description, category);
            manager.addTransaction(newTransaction);
        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt belopp. Försök igen.");
        }
    }
}