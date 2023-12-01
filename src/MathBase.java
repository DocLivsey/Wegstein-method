public class MathBase {
    protected final double epsilon = 1E-10;
    public double getEpsilon() { return this.epsilon; }
    @Override
    public boolean equals(Object obj)
    { return super.equals(obj); }
    @Override
    protected Object clone() throws CloneNotSupportedException
    { return super.clone(); }
    @Override
    public String toString()
    { return super.toString(); }
    public static boolean isNumeric(String string)
    {
        if(string == null || string.isEmpty()) {
            System.out.println(Main.ERROR + "String cannot be parsed, it is null or empty." + Main.RESET);
            return false;
        }
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(Main.ERROR + "Input String cannot be parsed to Integer." + Main.RESET);
        }
        return false;
    }
    public static boolean severalNumeric(String string)
    {
        String[] strArr = string.trim().split("\\s+");
        for (String s : strArr)
            if (!isNumeric(s)) return false;
        return true;
    }
}
