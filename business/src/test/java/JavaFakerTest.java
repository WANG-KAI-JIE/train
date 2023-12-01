import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class JavaFakerTest {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/train_business";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "12345678";

	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("zh-CN"));

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "INSERT INTO student (id, name, age, mobile, email, school) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			for (int i = 0; i < 100000; i++) {
				int id = i + 1;
				String mobile = faker.phoneNumber().cellPhone();
				String name = faker.name().fullName();
				Integer age = faker.number().numberBetween(18,30);
				String email = faker.internet().emailAddress(name);
				String school = faker.university().name();

				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setInt(3, age);
				stmt.setString(4, mobile);
				stmt.setString(5, email);
				stmt.setString(6, school);

				stmt.executeUpdate();
			}

			System.out.println("Data inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}