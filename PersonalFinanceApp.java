import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PersonalFinanceApp {
    private final Scanner scanner = new Scanner(System.in);
    private final PersonalFinanceManager manager;
    private final List<Command> commands;
    private boolean running = true;

    public PersonalFinanceApp() {
        // 1. Initiera Repository (VG: Filsparning)
        TransactionRepository repository = new FileTransactionRepository();

        // 2. Initiera Manager (Kärnlogik)
        this.manager = new PersonalFinanceManager(repository);

        // 3. Initiera Kommandon (VG: Command Pattern)
        this.commands = Arrays.asList(
            new AddTransactionCommand(manager, scanner),
            new ViewTransactionsCommand(manager),
            // Lägg till fler kommandon här (t.ex. Delete, Report etc.)
            new ExitCommand(() -> this.running = false) // Lambda för avslut
        );
    }

    public void run() {
        System.out.println("--- Personal Finance Terminal-Applikation ---");

        while (running) {
            displayMenu();
            System.out.print("Ange ditt val (1-" + commands.size() + "): ");
            String input = scanner.nextLine();
            
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= commands.size()) {
                    Command selectedCommand = commands.get(choice - 1);
                    selectedCommand.execute(); // Polymorfism i aktion!
                } else {
                    System.out.println("Ogiltigt val. Försök igen.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig input. Ange ett nummer.");
            }
        }

        scanner.close();
        System.out.println("Programmet avslutat.");
    }

    private void displayMenu() {
        System.out.println("\n--- Meny ---");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i).getCommandName());
        }
        System.out.println("------------");
    }

    public static void main(String[] args) {
        new PersonalFinanceApp().run();
    }
}