package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDAO;
import com.db.DBConnect;
import com.entity.Doctor;

@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String full_name = req.getParameter("full_name");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			String password = req.getParameter("password");
			
			int id = Integer.parseInt(req.getParameter("id")); 
			
			Doctor d = new Doctor(id,full_name, dob, qualification, specialist, email, mobno, password);
			
			DoctorDAO dao = new DoctorDAO(DBConnect.getConn());
			boolean f = dao.updateDoctor(d);
			
			HttpSession session = req.getSession();
			
			if(f)
			{
				session.setAttribute("succMsg", "Doctor Updated Successfully..");
				resp.sendRedirect("admin/view_doctor.jsp");
			}
			else
			{
				session.setAttribute("errorMSg", "Something wrong on server");
				resp.sendRedirect("admin/view_doctor.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
