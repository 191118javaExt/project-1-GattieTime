package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				int role = rs.getInt("role_id");
				

				User u = new User(id, name, password, first, last, email, role);

				list.add(u);
			}
			rs.close();

		} catch (SQLException e) {
			logger.warn("Unable to retrieve all reinbursements", e);

		}
		return list; 
	}

	@Override
	public User findById(int id) {
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM user WHERE id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				int role = rs.getInt("role_id");
				

				u = new User(id, name, password, first, last, email, role);
			}
			rs.close();

		} catch (SQLException e) {
			logger.warn("Unable to retrieve all reinbursements", e);

		}
		return u;
	}

	@Override
	public User findByName(String name) {
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM user WHERE user_name = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("user_password");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				int role = rs.getInt("role_id");
				

				u = new User(id, name, password, first, last, email, role);
			}
			rs.close();

		} catch (SQLException e) {
			logger.warn("Unable to retrieve all reinbursements", e);

		}
		return u;
	}

	@Override
	public User findByEmail(String email) {
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM user WHERE user_name = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				int role = rs.getInt("role_id");
				

				u = new User(id, name, password, first, last, email, role);
			}
			rs.close();

		} catch (SQLException e) {
			logger.warn("Unable to retrieve all reinbursements", e);

		}
		return u;
	}

	@Override
	public boolean insertUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO users (user_name, user_password, first_name, last_name, email, role_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole());

			stmt.execute();
			
			//TODO set role name

		} catch (SQLException ex) {
			logger.warn("Unable to insert user.", ex);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE users SET user_name =?, user_password = ?, first_name = ?, last_name = ?, email = ?, role_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole());

			if (!stmt.execute()) {
				logger.warn("Reinbursement update failed to execute.");
				return false;
			}

		} catch (SQLException ex) {
			logger.warn("Unable to update reinbursement.", ex);
		}
		return true;
	}

}
