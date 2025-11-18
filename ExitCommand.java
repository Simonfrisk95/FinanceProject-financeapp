public class ExitCommand implements Command {
    private final Runnable exitAction;

    // Tar emot en funktion/lambda som ska köras vid avslut (Inkapsling)
    public ExitCommand(Runnable exitAction) {
        this.exitAction = exitAction;
    }

    @Override
    public String getCommandName() {
        return "Avsluta programmet";
    }

    @Override
    public void execute() {
        System.out.println("Sparar data och avslutar...");
        exitAction.run();
    }
}