package org.isdb.firstJsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	public void create(Student student) throws Exception {
		// Primary key will be auto incremented (Configure the table that way)
		String sql = "INSERT INTO studentJsf (name, email, course) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getCourse());
			stmt.executeUpdate();
		}
	}

	public List<Student> readAll() throws Exception {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM studentJsf ORDER BY id";
		try (Connection conn = DatabaseConfig.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Student student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("course"));
				students.add(student);
			}
		}
		return students;
	}

	public void update(Student student) throws Exception {
		String sql = "UPDATE studentJsf SET name=?, email=?, course=? WHERE id=?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getCourse());
			stmt.setLong(4, student.getId());
			stmt.executeUpdate();
		}
	}

	public void delete(Long id) throws Exception {
		String sql = "DELETE FROM studentJsf WHERE id=?";
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		}
	}
}
