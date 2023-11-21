import java.io.*;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String ERROR = "\u001B[31m"; // RED
    public static final String INPUT = "\u001B[32m"; // GREEN
    public static final String COMMENT = "\u001B[33m"; // YELLOW
    public static final String CHOOSE = "\u001B[34m"; // BLUE
    public static final String OUTPUT = "\u001B[35m"; // PURPLE
    public static final String HEADER_OUTPUT = "\u001B[36m"; // CYAN
    public static void main(String[] args) throws IOException{
        String pathToPoints = "src/pointsInput.txt";
        String pathToCoefficients = "src/coefficientsInput.txt";
        String pathToFunction = "src/functionInput.txt";

        String func = "(x + 2) + (x - 1)";
        MathFunctionActions function = new MathFunctionActions(); function.splitByBrackets(func);
    }
}