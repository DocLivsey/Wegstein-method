import java.io.*;
import java.util.*;
public class MathFunctionActions extends MathBase {
    protected ArrayList<Point2D> points;
    MathFunction mathFunction;
    MathFunctionActions(String pathToFile) {}
    MathFunctionActions(MathFunction mathFunction)
    { this.mathFunction = mathFunction; }
    MathFunctionActions(ArrayList<Point2D> points)
    { this.points = points; }
    MathFunctionActions()
    {
        this.points = new ArrayList<>();
        this.mathFunction = new MathFunction() {
            @Override
            public double function(Point2D point2D)
            { return 0; }
        };
    }
    public ArrayList<Point2D> getPoints()
    { return this.points; }
    public void printPoints()
    {
        for (Point2D point : this.points)
            point.print();
    }
    public void readPointsFromFile(String pathToFile) throws FileNotFoundException {
        File input = new File(pathToFile);
        Scanner fileScan = new Scanner(input);
        while (fileScan.hasNextLine())
        {
            String pairLine = fileScan.nextLine();
            Point2D point2D = Point2D.createFromStringPair(pairLine);
            this.points.add(point2D);
        }
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
        for (String s : byBrackets) System.out.println(s);
        return byBrackets;
    }
    public double linearInterpolation(Point2D leftPoint, Point2D rightPoint, double dx)
    {
        double a = (rightPoint.getY() - leftPoint.getY()) / (rightPoint.getX() - leftPoint.getX());
        double b = leftPoint.getY() - a * leftPoint.getX();
        return a * dx + b;
    }
    public double differential(Point2D point, double dy)
    { return 0; }
}