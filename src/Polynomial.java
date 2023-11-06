import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Polynomial {
    protected TreeMap<String, Double> polynomialCoefficients;
    protected int polynomialDegree;
    Polynomial(String pathToFile) throws FileNotFoundException
    {
        File input = new File(pathToFile);
        Scanner scan = new Scanner(input);
        String line = scan.nextLine();
        String[] strArr = line.trim().split("\\s+");

        Scanner scanMsg = new Scanner(System.in);
        System.out.println(Main.COMMENT + "Вы уверены, что коэффициенты записаны упорядоченно?");
        String answer = scanMsg.nextLine();
        if (answer.equals("yes") || answer.equals("Yes") || answer.equals("Да") || answer.equals("да"))
        {
            this.polynomialDegree = strArr.length;
            this.polynomialCoefficients = new TreeMap<>();
            System.out.println("Они записаны по возрастанию или по убыванию? \n" + "1 - по возрастанию, 2 - по убыванию" + Main.RESET);
            answer = scanMsg.nextLine();
            switch (answer)
            {
                case "1":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.polynomialCoefficients.put("X^" + i, Double.parseDouble(strArr[i]));
                    break;
                case "2":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.polynomialCoefficients.put("X^" + (this.polynomialDegree - i), Double.parseDouble(strArr[i]));
                    break;
                default:
                    this.polynomialDegree = 0;
                    this.polynomialCoefficients = null;
                    break;
            }
        }
        else
        {
            this.polynomialDegree = 0;
            this.polynomialCoefficients = null;
        }
    }
    Polynomial(Vector coefficients)
    {
        Scanner scanMsg = new Scanner(System.in);
        System.out.println(Main.COMMENT + "Вы уверены, что коэффициенты записаны упорядоченно?");
        String answer = scanMsg.nextLine();
        if (answer.equals("yes") || answer.equals("Yes") || answer.equals("Да") || answer.equals("да"))
        {
            this.polynomialDegree = coefficients.getVectorSize();
            this.polynomialCoefficients = new TreeMap<>();
            System.out.println("Они записаны по возрастанию или по убыванию? \n" + "1 - по возрастанию, 2 - по убыванию" + Main.RESET);
            answer = scanMsg.nextLine();
            switch (answer)
            {
                case "1":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.polynomialCoefficients.put("X^" + i, coefficients.getItem(i));
                    break;
                case "2":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.polynomialCoefficients.put("X^" + (this.polynomialDegree - i), coefficients.getItem(i));
                    break;
                default:
                    this.polynomialDegree = 0;
                    this.polynomialCoefficients = null;
                    break;
            }
        }
        else
        {
            this.polynomialDegree = 0;
            this.polynomialCoefficients = null;
        }
    }
    Polynomial()
    {
        Scanner scanPolynomial = new Scanner(System.in);
        System.out.println(Main.INPUT + "Введите степень полинома" + Main.RESET);
        this.polynomialDegree = scanPolynomial.nextInt();
        System.out.println(Main.INPUT + "Введите коэффициенты при соответсвущих степенях" + Main.RESET);
        this.polynomialCoefficients = new TreeMap<>();
        for (int i = 0; i < this.polynomialDegree; i++)
        {
            System.out.print("x^" + (this.polynomialDegree - i) + ": ");
            this.polynomialCoefficients.put("X^" + (this.polynomialDegree - i), scanPolynomial.nextDouble());
        }
    }
    int getPolynomialDegree() { return this.polynomialDegree; }
    TreeMap<String, Double> getPolynomialCoefficients()
    { return this.polynomialCoefficients; }
    Double getCoefficient(String degree)
    {
        if (Main.isNumeric(degree))
            return this.polynomialCoefficients.get("X^" + degree);
        else
        {
            if (this.polynomialCoefficients.containsKey(degree))
                return this.polynomialCoefficients.get(degree);
            else
                System.out.println(Main.ERROR + "Такой степени нет в полиноме" + Main.RESET);
            return Double.NaN;
        }
    }
    Set<String> getDegrees(Double coefficient)
    { return this.polynomialCoefficients.keySet(); }
    void setPolynomialDegree(int polynomialDegree)
    { this.polynomialDegree = polynomialDegree; }
    void setPolynomialCoefficients(TreeMap<String, Double> polynomialCoefficients)
    { this.polynomialCoefficients = polynomialCoefficients; }
    void setCoefficientForDegree(String degree, Double coefficient)
    {
        if (Main.isNumeric(degree))
        {
            if (this.polynomialCoefficients.containsKey("X^" + degree))
                this.polynomialCoefficients.put("X^" + degree, coefficient);
            else
                System.out.println(Main.ERROR + "Такой степени нет в полиноме" + Main.RESET);
        }
        else
            System.out.println(Main.ERROR + "Такой степени нет в полиноме" + Main.RESET);
    }
    void print()
    {
        int count = 1;
        for (Map.Entry<String, Double> entry : this.polynomialCoefficients.entrySet())
        {
            if (count < this.polynomialDegree)
            {
                System.out.print(entry.getValue() + entry.getKey() + " + ");
                count++;
            }
            else
                System.out.print(entry.getValue() + entry.getKey());
        }
    }
    void printFormatted()
    {
        int count = 1;
        for (Map.Entry<String, Double> entry : this.polynomialCoefficients.entrySet())
        {
            DecimalFormat shortOut = new DecimalFormat("#.##");
            String result = shortOut.format(entry.getValue());
            if (count < this.polynomialDegree)
            {
                System.out.print(result + entry.getKey() + " + ");
                count++;
            }
            else
            {
                System.out.print(result + entry.getKey());
            }
        }
    }
}
