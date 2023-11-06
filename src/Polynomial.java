import java.util.*;

public class Polynomial {
    protected TreeMap<String, Double> coefficients;
    protected int polynomialDegree;
    Polynomial(Vector coefficients)
    {
        Scanner scanMsg = new Scanner(System.in);
        System.out.println(Main.COMMENT + "Вы уверены, что коэффициенты записаны упорядоченно?");
        String answer = scanMsg.nextLine();
        if (answer.equals("yes") || answer.equals("Yes") || answer.equals("Да") || answer.equals("да"))
        {
            this.polynomialDegree = coefficients.getVectorSize();
            this.coefficients = new TreeMap<>();
            System.out.println("Они записаны по возрастанию или оп убыванию? \n" + "1 - по возрастанию, 2 - по убыванию" + Main.RESET);
            answer = scanMsg.nextLine();
            switch (answer)
            {
                case "1":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.coefficients.put("x^" + (this.polynomialDegree - i), coefficients.getItem(i));
                    break;
                case "2":
                    for (int i = 0; i < this.polynomialDegree; i++)
                        this.coefficients.put("x^" + i, coefficients.getItem(i));
                    break;
                default:
                    this.polynomialDegree = 0;
                    this.coefficients = null;
                    break;
            }
        }
        else
        {
            this.polynomialDegree = 0;
            this.coefficients = null;
        }
    }
    Polynomial()
    {
        Scanner scanPolynomial = new Scanner(System.in);
        System.out.println(Main.INPUT + "Введите степень полинома" + Main.RESET);
        this.polynomialDegree = scanPolynomial.nextInt();
        System.out.println(Main.INPUT + "Введите коэффициенты при соответсвущих степенях" + Main.RESET);
        this.coefficients = new TreeMap<>();
        for (int i = 0; i < this.polynomialDegree; i++)
        {
            System.out.print("x^" + (this.polynomialDegree - i) + ": ");
            this.coefficients.put("x^" + (this.polynomialDegree - i), scanPolynomial.nextDouble());
        }
    }
}
