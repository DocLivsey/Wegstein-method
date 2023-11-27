import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EquationSolving extends MathFunctionOperations {
    protected ArrayList<Point2D> solutions;
    protected Point2D initSolution;
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
}