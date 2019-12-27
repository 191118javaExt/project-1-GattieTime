package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.utils.ConnectionUtil;
import com.revatute.models.User;

public class UserDAOImpl implements UserDAO {
	
	Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM reinburs;";
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
	public User findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}

}
