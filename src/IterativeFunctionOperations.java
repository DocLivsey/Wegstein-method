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
        return this.iterativeFunction.iterativeFunction(x, lambda);
    }
}
