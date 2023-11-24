import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EquationSolving extends MathFunctionOperations {
    protected ArrayList<Point2D> solutions;
    protected Point2D initSolution;
    EquationSolving(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    { super(pathToPoints, mathFunction); this.solutions = new ArrayList<>(); this.initSolution = new Point2D(0, 0); }
    EquationSolving(ArrayList<Point2D> points, MathFunction mathFunction)
    { super(points, mathFunction); this.solutions = new ArrayList<>(); this.initSolution = new Point2D(0, 0); }
    EquationSolving()
    {
        super();
        this.solutions = new ArrayList<>();
        this.initSolution = new Point2D(0, 0);
    }
    public ArrayList<Point2D> getSolutions()
    { return solutions; }
    public Point2D getInitSolution()
    { return initSolution; }
    public void setSolutions(ArrayList<Point2D> solutions)
    { this.solutions = solutions; }
    public void setInitSolution(Point2D initSolution)
    { this.initSolution = initSolution; }
    public double differentialAbsoluteGrade()
    {
        double absMax = Math.abs(super.differential(super.getPoint(0)));
        for (Point2D point : super.getPoints())
            absMax = Math.max(absMax, Math.abs(super.differential(point)));
        return absMax;
    }
    public double calculateLambda(Point2D point2D)
    { return 1 / super.differential(point2D); }
    public void wegsteinMethod()
    {
        double q = 0.5;
        double x0;
        do {

            double x1 = this.calculatePoint(1).getX();
            double ξ = 0;
        } while (true);
    }
}