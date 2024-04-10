package com.dao;

import java.sql.*;

import com.entity.User;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean userRegister(User u) {
		boolean f = false;

		try {
			String sql = "insert into user_details(full_name,email,password) values(?,?,?)";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setString(1, u.getFullName());
			pds.setString(2, u.getEmail());
			pds.setString(3, u.getPassword());

			int i = pds.executeUpdate();

			if (i == 1)
				f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public User login(String em, String psw) {
		User u = null;

		try {
			String sql = "select * from user_details where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, em);
			ps.setString(2, psw);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFullName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}
	
	public boolean checkOldPassword(int user_id, String oldPassword)
	{
		boolean f=false;
		
		try {
			String sql="select *from user_details where id=? and password=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setInt(1, user_id);
			pds.setString(2, oldPassword);
			
			ResultSet rs = pds.executeQuery();
			
			if(rs.next())
				f=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean changePassword(int user_id, String newPassword)
	{
		boolean f=false;
		
		try {
			String sql="update user_details set password=? where id=? ";
			PreparedStatement pds = conn.prepareStatement(sql);
			
			pds.setString(1, newPassword);
			pds.setInt(2, user_id);
			
			int i = pds.executeUpdate();
			
			if(i == 1)
				f=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}
}
