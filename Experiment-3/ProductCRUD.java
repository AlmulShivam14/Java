import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n1. Create\n2. Read\n3. Update\n4. Delete\n5. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> createProduct(conn, sc);
                    case 2 -> readProducts(conn);
                    case 3 -> updateProduct(conn, sc);
                    case 4 -> deleteProduct(conn, sc);
                    case 5 -> running = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createProduct(Connection conn, Scanner sc) throws SQLException {
        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("ID: "); ps.setInt(1, sc.nextInt());
            System.out.print("Name: "); ps.setString(2, sc.next());
            System.out.print("Price: "); ps.setDouble(3, sc.nextDouble());
            System.out.print("Quantity: "); ps.setInt(4, sc.nextInt());
            ps.executeUpdate();
            conn.commit();
            System.out.println("Product added.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Insert failed.");
        }
    }

    static void readProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("ProductID") + " | " +
                                   rs.getString("ProductName") + " | " +
                                   rs.getDouble("Price") + " | " +
                                   rs.getInt("Quantity"));
            }
        }
    }

    static void updateProduct(Connection conn, Scanner sc) throws SQLException {
        String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("ID to update: "); int id = sc.nextInt();
            System.out.print("New Name: "); ps.setString(1, sc.next());
            System.out.print("New Price: "); ps.setDouble(2, sc.nextDouble());
            System.out.print("New Quantity: "); ps.setInt(3, sc.nextInt());
            ps.setInt(4, id);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Product updated.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Update failed.");
        }
    }

    static void deleteProduct(Connection conn, Scanner sc) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.print("ID to delete: "); ps.setInt(1, sc.nextInt());
            ps.executeUpdate();
            conn.commit();
            System.out.println("Product deleted.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Delete failed.");
        }
    }
}
