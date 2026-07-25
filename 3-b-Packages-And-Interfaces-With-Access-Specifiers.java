import java.util.Scanner;

public class PiCalculator {

    // Private member variable
    private double piValue;
    private int terms;

    // Public constant
    public final String SERIES = "Leibniz Series";

    // Constructor
    public PiCalculator(int terms) {
        this.terms = terms;
        calculatePi();
    }

    // Private method
    private void calculatePi() {
        piValue = 0.0;

        for (int i = 0; i < terms; i++) {
            if (i % 2 == 0)
                piValue += 4.0 / (2 * i + 1);
            else
                piValue -= 4.0 / (2 * i + 1);
        }
    }

    // Public method
    public double getPiValue() {
        return piValue;
    }

    // Protected method
    protected void displayPrecisionInfo() {
        System.out.println("Protected Method - Displaying Precision Info:");
        System.out.println("Precision used: " + terms + " terms");
        System.out.println("Series used: " + SERIES + " (4/1 - 4/3 + 4/5 - 4/7 + 4/9 ...)");
    }

    // Default access method
    void displayPrivateData() {
        System.out.println("Private Data - Accessed only within class:");
        System.out.println("Raw computed value (private): " + piValue);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Pi Calculator using Access Specifiers ===");

        System.out.print("Enter the number of terms for Pi approximation: ");
        int n = sc.nextInt();

        PiCalculator obj = new PiCalculator(n);

        System.out.println("Calculating Pi using Leibniz Series...");

        System.out.println("Public Method - Displaying Result:");
        System.out.println("Approximated value of Pi: " + obj.getPiValue());

        obj.displayPrecisionInfo();

        obj.displayPrivateData();

        // The following line will cause a compile-time error
        // because piValue is private.
        // System.out.println(obj.piValue);

        sc.close();
    }
}
