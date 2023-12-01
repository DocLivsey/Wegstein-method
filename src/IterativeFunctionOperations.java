import java.io.FileNotFoundException;

public class IterativeFunctionOperations extends MathFunctionOperations {
    IterativeFunction iterativeFunction;
    IterativeFunctionOperations(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    {
        super(pathToPoints, mathFunction);
        this.iterativeFunction = (x, lambda) -> {
            double y = x - super.calculatePoint(x).getY() * lambda;
            return new Point2D(x, y);
        };
    }
    IterativeFunctionOperations()
    {
        super();
        this.iterativeFunction = (x, lambda) -> {
            double y = x - super.calculatePoint(x).getY() * lambda;
            return new Point2D(x, y);
        };
    }
    public IterativeFunction getIterativeFunction()
    { return iterativeFunction; }
    public void setIterativeFunction(IterativeFunction iterativeFunction)
    { this.iterativeFunction = iterativeFunction; }
    public Point2D calculatePoint(double x, double lambda)
    {
        if (Math.abs(this.iterativeFunction.iterativeFunction(x, lambda).getY()) < super.getEpsilon())
            return new Point2D(x, 0);
        if (Math.abs(this.iterativeFunction.iterativeFunction(x, lambda).getY()) == Double.POSITIVE_INFINITY)
            return  new Point2D(x, 1 / super.getEpsilon());
        return this.iterativeFunction.iterativeFunction(x, lambda);
    }
    public double calculateLambda(Point2D point2D)
    {
        if (super.differential(point2D) == 0)
            return 1 / super.getEpsilon();
        if (Math.abs(super.differential(point2D)) > (1 / super.getEpsilon()))
            return super.getEpsilon();
        return 1 / super.differential(point2D);
    }
    @Override
    public double differential(Point2D point)
    {
        double lambda = this.calculateLambda(point);
        double dx = point.getX() + super.getEpsilon();
        double dy = this.calculatePoint(dx, lambda).getY() - point.getY();
        return dy / super.getEpsilon();
    }
    @Override
    public void printFunction()
    {
        System.out.print(Main.HEADER_OUTPUT + "Итерационная " + Main.RESET);
        super.printFunction();
    }
}
