package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Doctor;


public class DoctorDAO {

	private Connection conn;

	public DoctorDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean addDoctor(Doctor d)
	{
		boolean f = false;
		
		try {
			String sql = "insert into doctor(full_name,dob,qualification,specialist,email,mobile_number,password) values (?,?,?,?,?,?,?)";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setString(1, d.getFullName());
			pds.setString(2, d.getDob());
			pds.setString(3, d.getQualification());
			pds.setString(4, d.getSpecialist());
			pds.setString(5, d.getEmail());
			pds.setString(6, d.getMobNo());
			pds.setString(7, d.getPassword());
			
			int i = pds.executeUpdate();
			
			if(i == 1)
				f = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return f;
	}
	
	public List<Doctor> getAllDoctor() {
		List<Doctor> list = new ArrayList<Doctor>();
		Doctor d = null;
		try {

			String sql = "select * from doctor order by id desc";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));
				list.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Doctor getDoctorByID(int id) {
		
		Doctor d = null;
		try {

			String sql = "select * from doctor where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	public boolean updateDoctor(Doctor d)
	{
		boolean f = false;
		
		try {
			String sql = "update doctor set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobile_number=?,password=? where id=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setString(1, d.getFullName());
			pds.setString(2, d.getDob());
			pds.setString(3, d.getQualification());
			pds.setString(4, d.getSpecialist());
			pds.setString(5, d.getEmail());
			pds.setString(6, d.getMobNo());
			pds.setString(7, d.getPassword());
			pds.setInt(8, d.getId());
			int i = pds.executeUpdate();
			
			if(i == 1)
				f = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return f;
	}
	
	public boolean deleteDoctor(int id)
	{
		boolean f = false;
		try {
			String sql="delete from doctor where id=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setInt(1, id);
			
			int i = pds.executeUpdate();
			if(i==1)
				f=true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return f;
	}
	
	public Doctor doctorLogin(String email, String pass)
	{
		Doctor d = null;
		
		try {
			String sql = "select *from doctor where email=? and password=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setString(1, email);
			pds.setString(2, pass);
			
			ResultSet rs = pds.executeQuery();
			
			while(rs.next())
			{
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return d;
	}
	
	public int countDoctor()
	{
		int i=0;
		try {
			String sql = "select count(*) as count from doctor";
			PreparedStatement pds = conn.prepareStatement(sql);
			
			ResultSet rs = pds.executeQuery();
			if(rs.next())
			{
				i=rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return i;
	}
	
	public int countAppointment()
	{
		int i=0;
		try {
			String sql = "select count(*) as count from appointment";
			PreparedStatement pds = conn.prepareStatement(sql);
			
			ResultSet rs = pds.executeQuery();
			if(rs.next())
			{
				i=rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return i;
	}
	
	public int countUser()
	{
		int i=0;
		try {
			String sql = "select count(*) as count from user_details";
			PreparedStatement pds = conn.prepareStatement(sql);
			
			ResultSet rs = pds.executeQuery();
			if(rs.next())
			{
				i=rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return i;
	}
	
	public int countSpecialist()
	{
		int i=0;
		try {
			String sql = "select count(*) as count from specialist";
			PreparedStatement pds = conn.prepareStatement(sql);
			
			ResultSet rs = pds.executeQuery();
			if(rs.next())
			{
				i=rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return i;
	}
	
	public int countAppointmentByDocotrId(int did)
	{
		int i=0;
		try {
			String sql = "select count(*) as count from appointment where doct_id=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setInt(1, did);
			
			ResultSet rs = pds.executeQuery();
			if(rs.next())
			{
				i=rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return i;
	}
	
	public boolean checkOldPassword(int user_id, String oldPassword)
	{
		boolean f=false;
		
		try {
			String sql="select *from doctor where id=? and password=?";
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
			String sql="update doctor set password=? where id=? ";
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
	
	public boolean editDoctorProfile(Doctor d)
	{
		boolean f = false;
		
		try {
			String sql = "update doctor set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobile_number=? where id=?";
			PreparedStatement pds = conn.prepareStatement(sql);
			pds.setString(1, d.getFullName());
			pds.setString(2, d.getDob());
			pds.setString(3, d.getQualification());
			pds.setString(4, d.getSpecialist());
			pds.setString(5, d.getEmail());
			pds.setString(6, d.getMobNo());
			
			pds.setInt(7, d.getId());
			int i = pds.executeUpdate();
			
			if(i == 1)
				f = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return f;
	}
}
