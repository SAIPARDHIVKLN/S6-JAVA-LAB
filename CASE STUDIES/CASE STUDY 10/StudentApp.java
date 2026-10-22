
import java.sql.*;
import java.util.Scanner;

public class StudentApp {

    static void run(String sql, Object... v) {
        try (Connection c = Db.open();
             PreparedStatement p = c.prepareStatement(sql)) {

            for (int i = 0; i < v.length; i++)
                p.setObject(i + 1, v[i]);

            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                try (ResultSet rs = p.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " "
                                + rs.getString(2) + " "
                                + rs.getInt(3));
                    }
                }
            } else {
                System.out.println(p.executeUpdate()
                        + " row(s) affected.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Record Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Register number: ");
                    String reg = sc.nextLine();
                    System.out.print("Student name: ");
                    String name = sc.nextLine();
                    System.out.print("Marks: ");
                    int marks = sc.nextInt();
                    sc.nextLine();

                    run("INSERT INTO student VALUES (?, ?, ?)",
                            reg, name, marks);
                    break;

                case 2:
                    System.out.print("Register number to search: ");
                    reg = sc.nextLine();
                    run("SELECT * FROM student WHERE reg_no = ?", reg);
                    break;

                case 3:
                    System.out.print("Register number: ");
                    reg = sc.nextLine();
                    System.out.print("New marks: ");
                    marks = sc.nextInt();
                    sc.nextLine();

                    run("UPDATE student SET marks = ? WHERE reg_no = ?",
                            marks, reg);
                    break;

                case 4:
                    System.out.print("Register number to delete: ");
                    reg = sc.nextLine();
                    run("DELETE FROM student WHERE reg_no = ?", reg);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
