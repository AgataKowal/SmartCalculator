package calculator;

public enum Command {
    EXIT("/exit", "Bye!", true),
    HELP("/help", "The program calculates the sum of numbers." +
            "It supports addition, subtraction, division and multiplication.", false),
    UNKNOWN("/unknown", "Unknown command", false);

    private final String command;
    private final String toPrint;
    private final boolean shouldExit;

    private Command(String command, String toPrint, boolean shouldExit) {
        this.command = command;
        this.toPrint = toPrint;
        this.shouldExit = shouldExit;
    }

    public static Command valueOfCommand(String command) {
        for (Command c : values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public void printDescription() {
        System.out.println(this.toPrint);
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }

}
