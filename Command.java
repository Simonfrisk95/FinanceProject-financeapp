public interface Command {
    String getCommandName(); // För att visa i menyn
    void execute(); // Metoden som kör kommandots logik (Polymorfism)
}