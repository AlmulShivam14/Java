import java.sql.*;
import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "user", "pass")) {
            StudentDAO dao = new StudentDAO(conn);
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n1. Add\n2. View All\n3. Update\n4. Delete\n5. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        Student s = new Student();
                        System.out.print("ID: "); s.setStudentID(sc.nextInt());
                        System.out.print("Name: "); s.setName(sc.next());
                        System.out.print("Dept: "); s.setDepartment(sc.next());
                        System.out.print("Marks: "); s.setMarks(sc.nextDouble());
                        dao.addStudent(s);
                    }
                    case 2 -> dao.getAllStudents().forEach(System.out::println);
                    // Add update and delete logic similarly
                    case 5 -> running = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
