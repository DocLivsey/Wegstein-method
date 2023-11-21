public class Point2D {
    protected double x;
    protected double y;
    Point2D()
    { this.x = 0; this.y = 0; }
    Point2D(double x, double y)
    { this.x = x; this.y = y; }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x)
    { this.x = x; }
    public void setY(double y)
    { this.y = y; }
    @Override
    public String toString()
    { return super.toString(); }
    @Override
    public boolean equals(Object obj)
    { return super.equals(obj); }
    public Point2D clone() throws CloneNotSupportedException
    { return (Point2D) super.clone(); }
    public static Point2D createFromStringPair(String pair)
    {
        double x, y;
        if (MathBase.severalNumeric(pair))
        {
            String[] splitPair = pair.trim().split("\\s+");
            x = Double.parseDouble(splitPair[0]);
            y = Double.parseDouble(splitPair[1]);
            return new Point2D(x, y);
        }
        return new Point2D();
    }
    public void print()
    { System.out.println("{ " + this.x + "; " + this.y + " }"); }
}
