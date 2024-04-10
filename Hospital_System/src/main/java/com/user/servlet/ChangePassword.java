package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.db.DBConnect;
import com.mysql.cj.xdevapi.DbDoc;

@WebServlet("/userChangePassword")
public class ChangePassword extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("uid"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		
		UserDAO dao = new UserDAO(DBConnect.getConn());
		
		HttpSession session = req.getSession();
		
		if(dao.checkOldPassword(id, oldPassword))
		{
			if(dao.changePassword(id, newPassword)) {
				session.setAttribute("succMsg", "Password Change Successfully");
				resp.sendRedirect("change_password.jsp");
			}else {
				session.setAttribute("errorMsg", "Something wrong on Server");
				resp.sendRedirect("change_password.jsp");
			}
		}else {
			session.setAttribute("errorMsg", "Old Password incorrect");
			resp.sendRedirect("change_password.jsp");
		}
	}

	
}
