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
    public Point2D getSolution(int index)
    { return this.solutions.get(index); }
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
        double solution;
        double q = 0.5;
        double x0 = this.initSolution.getX(), x1, x2, phi;
        boolean flag = true;
        do {
            x1 = super.calculatePoint(x0).getY();
            x2 = super.calculatePoint(x1).getY();
            double x0_tmp = x0, x1_tmp = x1;
            System.out.println("x1 = " + x1 + " x2 = " + x2 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
            if (Math.abs(x2 - x1_tmp) > super.getEpsilon() * (1 - q) / q)
            {
                double x2_tmp = (x2 * x0_tmp - x1 * x1_tmp) / (x2 + x0_tmp - x1 - x1_tmp);
                x0 = x1; x0_tmp = x1_tmp; x1 = x2; x1_tmp = x2_tmp;
            }
            System.out.println("x0 = " + x0 + " x1 = " + x1 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
            phi = super.calculatePoint(x2).getY();
            System.out.println("x2 = " + x2 + " |f`(x2)| = " + Math.abs(super.differential(new Point2D(x2, phi))));
            if (Math.abs(super.differential(new Point2D(x2, phi))) <= q)
                flag = false;
        } while (flag);
        solution = x2;
        System.out.println("x = " + solution + " f(x) = " + phi);
    }
}