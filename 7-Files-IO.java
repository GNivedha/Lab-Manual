import java.io.*;
import java.util.Scanner;

public class StudentFile {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

            System.out.println("Enter Details of 3 Students");

            for (int i = 1; i <= 3; i++) {

                System.out.println("\nStudent " + i);

                System.out.print("Roll No : ");
                int roll = sc.nextInt();
                sc.nextLine();

                System.out.print("Name : ");
                String name = sc.nextLine();

                System.out.print("Marks : ");
                int marks = sc.nextInt();

                bw.write(roll + "," + name + "," + marks);
                bw.newLine();
            }

            bw.close();

            System.out.println("\nStudent records saved successfully.");

            BufferedReader br = new BufferedReader(new FileReader("students.txt"));

            System.out.println("\n----- Student Records -----");

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

            System.out.print("\nEnter Roll Number to Search : ");
            int searchRoll = sc.nextInt();

            br = new BufferedReader(new FileReader("students.txt"));

            boolean found = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int roll = Integer.parseInt(data[0]);

                if (roll == searchRoll) {

                    System.out.println("\nStudent Found");

                    System.out.println("Roll No : " + data[0]);
                    System.out.println("Name : " + data[1]);
                    System.out.println("Marks : " + data[2]);

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nStudent Not Found");
            }

            br.close();

        }
        catch (IOException e) {
            System.out.println("File Error : " + e.getMessage());
        }

        sc.close();
    }
}
