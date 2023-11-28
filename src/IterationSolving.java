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
            this.phi.setPoint(i, this.phi.calculatePoint(super.getPoint(i).getX(), this.calculateLambda(getPoint(i))));
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
    public double calculateLambda(Point2D point2D)
    { return 1 / super.differential(point2D); }
    public double calculateStep(double leftBorder, double rightBorder)
    { return rightBorder / (rightBorder - leftBorder); }

    public void calculateInitSolutions()
    {
        double leftBorder = this.getPoint(0).getX();
        double rightBorder = this.getPoint(this.points.size() - 1).getX();
        System.out.println(this.calculateStep(leftBorder, rightBorder));
    }
    public void wegsteinMethod()
    {
        double solution;
        double q = super.differentialAbsoluteGrade();
        System.out.println("q = " + q + "; " + super.getEpsilon() * (1 - q) / q);
        double x0 = this.getInitSolution(0).getX(), x1, x2, y0;
        boolean flag = true; int it = 0;
        x1 = this.phi.calculatePoint(x0).getY();
        x2 = this.phi.calculatePoint(x1).getY();
        if (Math.abs(x1 - x0) > super.getEpsilon() * (1 - q) / q)
        {
            double x0_tmp = x0, x1_tmp = x1;
            do {
                System.out.println("iteration " + it); it++;
                System.out.println("x1 = " + x1 + " x2 = " + x2 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
                double x2_tmp = (x2 * x0_tmp - x1 * x1_tmp) / (x2 + x0_tmp - x1 - x1_tmp);
                x0 = x1; x0_tmp = x1_tmp; x1 = x2; x1_tmp = x2_tmp;
                System.out.println("x0 = " + x0 + " x1 = " + x1 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
                if (Math.abs(x2 - x1_tmp) > super.getEpsilon() * (1 - q) / q)
                    flag = false;
            } while (flag && it <= 1000);
            solution = x2;
            y0 = super.calculatePoint(x2).getY();
            System.out.println("x = " + solution + " f(x) = " + y0);
            System.out.println("x2 = " + x2 + " |phi`(x2)| = " + Math.abs(this.phi.differential(new Point2D(x2, y0))));
        }
    }
}
