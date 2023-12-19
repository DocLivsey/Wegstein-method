import java.io.*;
import java.util.*;

public class IterationSolving extends EquationSolving {
    protected IterativeFunctionOperations phi;
    protected ArrayList<Point2D> initSolutions;
    IterationSolving(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    {
        super(pathToPoints, mathFunction);
        this.phi = new IterativeFunctionOperations(pathToPoints, mathFunction);
        for (int i = 0; i < super.points.size(); i++)
            this.phi.setPoint(i, this.phi.calculatePoint(super.getPoint(i).getX(), this.phi.calculateLambda(getPoint(i))));
        this.initSolutions = new ArrayList<>();
    }
    IterationSolving(ArrayList<Point2D> points, MathFunction mathFunction, IterativeFunctionOperations phi)
    { super(points, mathFunction); this.phi = phi; this.initSolutions = new ArrayList<>(); }
    IterationSolving()
    {
        super();
        this.phi = new IterativeFunctionOperations();
        this.initSolutions = new ArrayList<>();
    }
    public IterativeFunctionOperations getPhi()
    { return phi; }
    public ArrayList<Point2D> getInitSolutions()
    { return initSolutions; }
    public Point2D getInitSolution(int index)
    { return this.initSolutions.get(index); }
    public void setPhi(IterativeFunctionOperations phi)
    { this.phi = phi; }
    public void setInitSolutions(ArrayList<Point2D> initSolutions)
    { this.initSolutions = initSolutions; }
    public void setInitSolution(int index, Point2D initSolution)
    { this.initSolutions.set(index, initSolution); }
    public void addInitSolution(Point2D initSolution)
    { this.initSolutions.add(initSolution); }
    public void printInitSolutions()
    {
        System.out.println(Main.HEADER_OUTPUT + "Начальные приближения" + Main.RESET);
        for (Point2D point : this.initSolutions)
            point.print();
    }
    public void calculateInitSolutions()
    {
        Point2D leftBorder;
        Point2D rightBorder;
        for (int i = 0; i < super.getPoints().size() - 1; i++)
        {
            leftBorder = super.getPoint(i);
            rightBorder = super.getPoint(i + 1);
            if (leftBorder.getY() * rightBorder.getY() < 0)
                this.addInitSolution(leftBorder);
            else if (leftBorder.getY() == 0)
                this.addSolution(leftBorder);
        }
    }
    public double differentialAbsoluteGrade()
    {

        double absMax = Math.abs(super.differential(super.getPoint(0)));
        for (Point2D point : super.getPoints())
            absMax = Math.max(absMax, Math.abs(super.differential(point)));
        if (absMax == Double.POSITIVE_INFINITY)
            return 1 / super.getEpsilon();
        return absMax;
    }
    public void wegsteinMethod()
    {
        this.calculateInitSolutions();
        double q = this.differentialAbsoluteGrade();
        for (Point2D initSolution : this.initSolutions)
        {
            initSolution.print();
            double x0 = initSolution.getX(), ξ = x0, x1, x2, lambda;
            int it = 0;
            lambda = this.phi.calculateLambda(super.calculatePoint(x0));
            x1 = this.phi.calculatePoint(x0, lambda).getY();
            double x0_tmp = x0, x1_tmp = x1;
            if (Math.abs(x1 - x0) > super.getEpsilon() * (1 - q) / q)
            {
                do {
                    it++;
                    lambda = this.phi.calculateLambda(super.calculatePoint(x1));
                    x2 = this.phi.calculatePoint(x1, lambda).getY();
                    double x2_tmp = (x2 * x0_tmp - x1 * x1_tmp) / (x2 + x0_tmp - x1 - x1_tmp);
                    x0 = x1;
                    x0_tmp = x1_tmp;
                    x1 = x2;
                    x1_tmp = x2_tmp;
                    ξ = x2;
                } while (Math.abs(x2 - x1_tmp) > super.getEpsilon() * (1 - q) / q && it <= 1000);
            }
            super.addSolution(super.calculatePoint(ξ));
        }
        if (super.getSolutions().isEmpty())
            System.out.println(Main.ERROR + "Метод Вегстейна корней не нашел" + Main.RESET);
    }
}
