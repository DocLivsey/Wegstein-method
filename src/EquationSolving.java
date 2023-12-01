import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EquationSolving extends MathFunctionOperations {
    protected ArrayList<Point2D> solutions;
    EquationSolving(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    { super(pathToPoints, mathFunction); this.solutions = new ArrayList<>(); }
    EquationSolving(ArrayList<Point2D> points, MathFunction mathFunction)
    { super(points, mathFunction); this.solutions = new ArrayList<>(); }
    EquationSolving()
    { super(); this.solutions = new ArrayList<>(); }
    public ArrayList<Point2D> getSolutions()
    { return solutions; }
    public Point2D getSolution(int index)
    { return this.solutions.get(index); }
    public void setSolutions(ArrayList<Point2D> solutions)
    { this.solutions = solutions; }
    public void addSolution(Point2D solution)
    { this.solutions.add(solution); }
    public void printSolutions()
    {
        System.out.println(Main.HEADER_OUTPUT + "Решения уравнения" + Main.RESET);
        if (this.solutions.isEmpty())
            System.out.println(Main.ERROR + "Решений нет" + Main.RESET);
        for (Point2D point : this.solutions)
            point.print();
    }
}