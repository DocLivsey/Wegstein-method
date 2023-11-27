import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IterationSolving extends EquationSolving {
    protected MathFunctionOperations phi;
    protected Point2D initSolution;
    IterationSolving(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    {
        super(pathToPoints, mathFunction);
        this.phi = new MathFunctionOperations(pathToPoints, mathFunction);
        for (int i = 0; i < this.points.size(); i++)
            this.phi.setPoint(i, this.calculatePhi(this.getPoint(i)));
        this.initSolution = new Point2D(0, 0);
    }
    IterationSolving(ArrayList<Point2D> points, MathFunction mathFunction, MathFunctionOperations phi)
    { super(points, mathFunction); this.phi = phi; this.initSolution = new Point2D(0, 0); }
    IterationSolving()
    {
        super();
        this.phi = new MathFunctionOperations();
        this.initSolution = new Point2D(0, 0);
    }
    public MathFunctionOperations getPhi()
    { return phi; }
    public Point2D getInitSolution()
    { return initSolution; }
    public void setPhi(MathFunctionOperations phi)
    { this.phi = phi; }
    public void setInitSolution(Point2D initSolution)
    { this.initSolution = initSolution; }
    public double calculateLambda(Point2D point2D)
    { return super.differential(point2D); }
    public Point2D calculatePhi(Point2D point)
    {
        double lambda = this.calculateLambda(point);
        double y = point.getX() - super.calculatePoint(point.getX()).getY() * lambda;
        return new Point2D(point.getX(), y);
    }
    public double differentialAbsoluteGradeOfPhi()
    {
        double absMax = Math.abs(this.phi.differential(this.getPhi().getPoint(0)));
        for (Point2D point : this.getPhi().getPoints())
            absMax = Math.max(absMax, Math.abs(this.phi.differential(point)));
        return absMax;
    }
    public void wegsteinMethod()
    {
        double solution;
        double q = this.differentialAbsoluteGradeOfPhi() - 0.1;
        System.out.println("q = " + q);
        double x0 = this.initSolution.getX(), x1, x2, y0;
        boolean flag = true; int it = 0;
        do {
            System.out.println("iteration " + it); it++;
            x1 = this.phi.calculatePoint(x0).getY();
            x2 = this.phi.calculatePoint(x1).getY();
            double x0_tmp = x0, x1_tmp = x1;
            System.out.println("x1 = " + x1 + " x2 = " + x2 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
            if (Math.abs(x2 - x1_tmp) > super.getEpsilon() * (1 - q) / q)
            {
                double x2_tmp = (x2 * x0_tmp - x1 * x1_tmp) / (x2 + x0_tmp - x1 - x1_tmp);
                x0 = x1; x0_tmp = x1_tmp; x1 = x2; x1_tmp = x2_tmp;
            }
            System.out.println("x0 = " + x0 + " x1 = " + x1 + " x0tmp = " + x0_tmp + " x1tmp = " + x1_tmp);
            y0 = super.calculatePoint(x2).getY();
            System.out.println("x2 = " + x2 + " |phi`(x2)| = " + Math.abs(this.phi.differential(new Point2D(x2, y0))));
            if (Math.abs(this.phi.differential(new Point2D(x2, y0))) <= q)
                flag = false;
        } while (flag && it <= 1000);
        solution = x2;
        System.out.println("x = " + solution + " f(x) = " + y0);
    }
}
