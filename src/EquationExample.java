public final class EquationExample {
    public static MathFunction getExamples(int exampleNumber)
    {
        return switch (exampleNumber)
        {
            case 1 -> (x) -> new Point2D(x, (x - 1) * (x + 3));
            case 2 -> (x) -> new Point2D(x, Math.sin(x));
            case 3 -> (x) -> new Point2D(x, x * x - x);
            case 4 -> (x) -> new Point2D(x, (x - 25) * (x + 3) * (x + 7));
            case 5 -> (x) -> new Point2D(x, Math.sin(Math.log(x)));
            case 6 -> (x) -> new Point2D(x, Math.sin(x) / x);
            case 7 -> (x) -> new Point2D(x, (x - 3) * (x + 4) * (x + 0.1333) * (x - 9.15));
            case 8 -> (x) -> new Point2D(x, Math.exp(Math.sin(x * x)) - Math.exp(Math.cos(x * x)));
            case 9 -> (x) -> new Point2D(x, Math.sqrt(x) - 1);
            default -> (x) -> new Point2D(x, 0);
        };
    }
}
