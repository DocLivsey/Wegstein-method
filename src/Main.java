import java.io.*;
public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String ERROR = "\u001B[31m"; // RED
    public static final String INPUT = "\u001B[32m"; // GREEN
    public static final String COMMENT = "\u001B[33m"; // YELLOW
    public static final String CHOOSE = "\u001B[34m"; // BLUE
    public static final String OUTPUT = "\u001B[35m"; // PURPLE
    public static final String HEADER_OUTPUT = "\u001B[36m"; // CYAN
    public static boolean isNumeric(String string)
    {
        System.out.printf("Parsing string: \"%s\"%n", string);
        if(string == null || string.isEmpty()) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
    public static void main(String[] args) throws IOException{
        String path = "src";
        File thisDir = new File(path);
        Vector vector = new Vector(3); //vector.createVector();
        //vector.writeInDesiredFolder(thisDir);

        path = "src/coefficientInput.txt";
        Polynomial polynomial = new Polynomial(path);
    }
}