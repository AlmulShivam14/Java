import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getInt("StudentID"));
                s.setName(rs.getString("Name"));
                s.setDepartment(rs.getString("Department"));
                s.setMarks(rs.getDouble("Marks"));
                list.add(s);
            }
        }
        return list;
    }

    // updateStudent() and deleteStudent() methods follow similar structure
}
