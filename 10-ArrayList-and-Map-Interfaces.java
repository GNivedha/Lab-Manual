import java.util.*;

class Student {

    int rollNo;
    String name;
    double percentage;

    Student(int rollNo, String name, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
    }

    public String toString() {
        return rollNo + "\t" + name + "\t" + percentage;
    }
}

public class StudentRecord {

    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        HashMap<Integer, Student> map = new HashMap<>();

        Student s1 = new Student(101, "Rahul", 88.5);
        Student s2 = new Student(102, "Sneha", 91.2);
        Student s3 = new Student(103, "Kiran", 84.8);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        map.put(s1.rollNo, s1);
        map.put(s2.rollNo, s2);
        map.put(s3.rollNo, s3);

        System.out.println("Student Records (ArrayList)");
        System.out.println("--------------------------------");
        System.out.println("Roll\tName\tPercentage");
        System.out.println("--------------------------------");

        for (Student s : list) {
            System.out.println(s);
        }

        int searchRoll = 102;

        System.out.println("\nSearching for Roll No : " + searchRoll);

        if (map.containsKey(searchRoll)) {

            Student s = map.get(searchRoll);

            System.out.println("\nRecord Found");

            System.out.println("Roll No : " + s.rollNo);
            System.out.println("Name : " + s.name);
            System.out.println("Percentage : " + s.percentage);

        } else {

            System.out.println("Record Not Found");
        }

        list.remove(0);

        System.out.println("\nAfter Removing First Student");
        System.out.println("--------------------------------");
        System.out.println("Roll\tName\tPercentage");
        System.out.println("--------------------------------");

        for (Student s : list) {
            System.out.println(s);
        }

        System.out.println("\nStudent Records (HashMap)");
        System.out.println("--------------------------------");

        for (Map.Entry<Integer, Student> entry : map.entrySet()) {

            Student s = entry.getValue();

            System.out.println(entry.getKey() + " -> " + s.name + " (" + s.percentage + "%)");
        }
    }
}
