package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SpecialistDAO;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spec_name = req.getParameter("spec_name");
		
		SpecialistDAO dao = new SpecialistDAO(DBConnect.getConn());
		boolean f = dao.addSpecialist(spec_name);
		
		HttpSession session = req.getSession();
		
		if (f) {
			session.setAttribute("succMsg", "Specialist Added");
			resp.sendRedirect("admin/index.jsp");
		} else {
			session.setAttribute("errorMsg", "Something wrong on server");
			resp.sendRedirect("admin/index.jsp");
		}
	}

	
}
