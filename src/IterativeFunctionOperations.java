public class IterativeFunctionOperations extends MathFunctionOperations {
    IterativeFunction iterativeFunction;
    IterativeFunctionOperations()
    {
        super();
        this.iterativeFunction = (x, lambda) -> {
            double y = x - super.calculatePoint(x).getY() * lambda;
            return new Point2D(x, y);
        };
    }
}
