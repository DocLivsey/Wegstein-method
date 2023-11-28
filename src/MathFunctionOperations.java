import java.io.*;
import java.util.*;
public class MathFunctionOperations extends MathBase {
    protected ArrayList<Point2D> points;
    protected MathFunction mathFunction;
    MathFunctionOperations(String pathToPoints, MathFunction mathFunction) throws FileNotFoundException
    {
        this.points = new ArrayList<>();
        this.readPointsFromFile(pathToPoints);
        this.mathFunction = mathFunction;

        for (int i = 0; i < this.points.size(); i++)
        {
            double x = this.getPoint(i).getX();
            double y = this.calculatePoint(this.getPoint(i).getX()).getY();
            this.setPoint(i, new Point2D(x, y));
        }
    }
    MathFunctionOperations(String pathToPoints) throws FileNotFoundException
    {
        this.points = new ArrayList<>();
        this.readPointsFromFile(pathToPoints);
    }
    MathFunctionOperations(ArrayList<Point2D> arguments, MathFunction mathFunction)
    {
        this.points = arguments;
        this.mathFunction = mathFunction;
    }
    MathFunctionOperations(ArrayList<Point2D> points)
    { this.points = points; }
    MathFunctionOperations()
    {
        this.points = new ArrayList<>();
        this.mathFunction = new MathFunction() {
            @Override
            public Point2D function(double x) {
                return new Point2D(Double.NaN, Double.NaN);
            }
        };
    }
    public ArrayList<Point2D> getPoints()
    { return this.points; }
    public MathFunction getMathFunction()
    { return mathFunction; }
    public Point2D getPoint(int index)
    { return this.points.get(index); }
    public void setPoints(ArrayList<Point2D> points)
    { this.points = points; }
    public void setMathFunction(MathFunction mathFunction)
    { this.mathFunction = mathFunction; }
    public void setPoint(int index, Point2D point2D)
    { this.points.set(index, point2D); }
    @Override
    public boolean equals(Object obj)
    { return super.equals(obj); }
    protected MathFunctionOperations cloneMathFunction()
    { return new MathFunctionOperations(this.points, this.mathFunction); }
    public void printPoints()
    {
        System.out.println(Main.HEADER_OUTPUT + "Точки Функции:" + Main.RESET);
        for (Point2D point : this.points)
            point.print();
    }
    public void printDifferentials()
    {
        System.out.println(Main.HEADER_OUTPUT + "Производная Функции в точках:" + Main.RESET);
        for (Point2D point : this.points)
            new Point2D(point.getX(), this.differential(point)).print();
    }
    public void printFunction()
    { System.out.println(super.toString()); }
    public Point2D calculatePoint(double x)
    {
        if (Math.abs(this.mathFunction.function(x).getY()) < super.getEpsilon())
            return new Point2D(x, 0);
        return this.mathFunction.function(x);
    }
    public double differential(Point2D point)
    {
        double dx = point.getX() + super.getEpsilon();
        double dy = this.calculatePoint(dx).getY() - point.getY();
        return dy / super.getEpsilon();
    }
    public double differentialAbsoluteGrade()
    {
        double absMax = Math.abs(this.differential(this.getPoint(0)));
        for (Point2D point : this.getPoints())
            absMax = Math.max(absMax, Math.abs(this.differential(point)));
        if (absMax == Double.POSITIVE_INFINITY)
            return 1 / super.getEpsilon();
        return absMax;
    }
    public void readPointsFromFile(String pathToFile) throws FileNotFoundException {
        File input = new File(pathToFile);
        Scanner fileScan = new Scanner(input);
        while (fileScan.hasNextLine())
        {
            String pairLine = fileScan.nextLine();
            String[] pair = pairLine.trim().split("\\s+");
            if (pair.length == 1)
            {
                Point2D point2D = Point2D.setXFromString(pairLine);
                this.points.add(point2D);
            }
            else if (pair.length == 2)
            {
                Point2D point2D = Point2D.setPairFromString(pairLine);
                this.points.add(point2D);
            }
        }
    }
    public void writePointsInFile()
    {

    }
    public LinkedList<String> splitByBrackets(String function)
    {
        LinkedList<String> byBrackets = new LinkedList<>();
        String[] functionByElements = function.split("");
        for (int i = 0; i < functionByElements.length; i++)
        {
            if (functionByElements[i].equals("("))
            {
                int j = i + 1;
                StringBuilder inBrackets = new StringBuilder();
                while (!functionByElements[j].equals(")"))
                {
                    inBrackets.append(functionByElements[j]);
                    j++;
                }
                byBrackets.add(inBrackets.toString());
            }
        }
        return byBrackets;
    }
}