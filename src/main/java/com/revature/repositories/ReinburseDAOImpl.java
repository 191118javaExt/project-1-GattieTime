package com.revature.repositories;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.models.ReceiptTemplate;
import com.revature.models.Reinbursement;
import com.revature.utils.ConnectionUtil;

public class ReinburseDAOImpl implements ReinburseDAO {

	// Logger logger = LogManager.getLogger(ReinburseDAOImpl.class);

	@Override
	public List<Reinbursement> findAll() {
		List<Reinbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM reinburs;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				Double amount = rs.getDouble("amount");
				Timestamp submit = rs.getTimestamp("submit");
				Timestamp resolved = rs.getTimestamp("resolved");
				String descript = rs.getString("descript");
				InputStream receipt = rs.getBinaryStream("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status");
				int type = rs.getInt("rein_type");

				Reinbursement r = new Reinbursement(id, amount, submit, resolved, descript, receipt, author, resolver,
						status, type);

				list.add(r);
			}
			rs.close();

		} catch (SQLException e) {
			// logger.warn("Unable to retrieve all reimbursements", e);

		}
		return list;
	}

	@Override
	public Reinbursement findById(int id) {

		Reinbursement r = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM reinburs WHERE id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Double amount = rs.getDouble("amount");
				Timestamp submit = rs.getTimestamp("submit");
				Timestamp resolved = rs.getTimestamp("resolved");
				String descript = rs.getString("descript");
				InputStream receipt = rs.getBinaryStream("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status");
				int type = rs.getInt("rein_type");

				r = new Reinbursement(id, amount, submit, resolved, descript, receipt, author, resolver, status, type);
			}
			rs.close();

		} catch (SQLException e) {
			// logger.warn("Unable to retrieve all reimbursements", e);

		}
		return r;
	}

	@Override
	public boolean updateReinburse(Reinbursement r) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			PreparedStatement stmt;

			if (r.getReceipt() == null) {
				String sql = "UPDATE reinburs SET amount = ?, submit = ?, resolved = ?, descript = ?, author = ?, resolver = ?, status = ?, rein_type = ? Where id = ?;";

				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, r.getAmount());
				stmt.setTimestamp(2, r.getSubmit());
				stmt.setTimestamp(3, r.getResolved());
				stmt.setString(4, r.getDescript());
				stmt.setInt(5, r.getAuthor());
				stmt.setInt(6, r.getResolver());
				stmt.setInt(7, r.getStatus());
				stmt.setInt(8, r.getType());
				stmt.setInt(9, r.getId());
			} else {

				String sql = "UPDATE reinburs SET amount = ?, submit = ?, resolved = ?, descript = ?, receipt = ?, author = ?, resolver = ?, status = ?, rein_type = ? Where id = ?;";

				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, r.getAmount());
				stmt.setTimestamp(2, r.getSubmit());
				stmt.setTimestamp(3, r.getResolved());
				stmt.setString(4, r.getDescript());
				stmt.setBinaryStream(5, r.getReceipt());
				stmt.setInt(6, r.getAuthor());
				stmt.setInt(7, r.getResolver());
				stmt.setInt(8, r.getStatus());
				stmt.setInt(9, r.getType());
				stmt.setInt(10, r.getId());
			}

			if (!stmt.execute()) {
				// logger.warn("Reimbursement update failed to execute.");
				return false;
			}

		} catch (SQLException ex) {
			// logger.warn("Unable to update reimbursement.", ex);
		}
		return true;
	}

	@Override
	public boolean insertReinburse(Reinbursement r) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO reinburs (amount, submit, descript, author, status, rein_type) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, r.getAmount());
			stmt.setTimestamp(2, r.getSubmit());
			stmt.setString(3, r.getDescript());
			// stmt.setBinaryStream(4, r.getReceipt());
			stmt.setInt(4, r.getAuthor());
			stmt.setInt(5, r.getStatus());
			stmt.setInt(6, r.getType());

			stmt.execute();

		} catch (SQLException ex) {
			// logger.warn("Unable to insert reimbursement.", ex);
			System.out.println(ex);
		}
		return true;
	}

	@Override
	public List<Reinbursement> findByUserId(int userId) {
		List<Reinbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM reinburs WHERE author = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);

			Timestamp submit;
			Timestamp resolved;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Double amount = rs.getDouble("amount");
				if (rs.getTimestamp("submit") != null) {
					submit = rs.getTimestamp("submit");
				} else {
					submit = null;
				}
				if (rs.getTimestamp("resolved") != null) {
					resolved = rs.getTimestamp("resolved");
				} else {
					resolved = null;
				}
				String descript = rs.getString("descript");
				InputStream receipt = null; // rs.getBinaryStream("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status");
				int type = rs.getInt("rein_type");

				Reinbursement r = new Reinbursement(id, amount, submit, resolved, descript, receipt, author, resolver,
						status, type);

				list.add(r);
			}
			rs.close();

		} catch (SQLException e) {
			// logger.warn("Unable to retrieve all reimbursements", e);
			System.out.println(e);
		}
		return list;
	}

	@Override
	public boolean updateReceipt(ReceiptTemplate rt) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			PreparedStatement stmt;
			String sql = "UPDATE reinburs SET receipt = ? Where id = ?;";

			stmt = conn.prepareStatement(sql);
			stmt.setBytes(1, Base64.getEncoder().encode(rt.receiptDataURL.getBytes()));
			stmt.setInt(2, rt.id);

			if (!stmt.execute()) {
				// logger.warn("Reimbursement update failed to execute.");
				return false;
			}

		} catch (SQLException ex) {
			// logger.warn("Unable to update reimbursement.", ex);
		}
		return true;

	}

}
