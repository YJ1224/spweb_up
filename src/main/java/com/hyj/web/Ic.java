package com.hyj.web;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyj.dao.Dao;

public class Ic implements HandlerInterceptor {
	
	@Autowired
	Dao dao;

	@Override 
	public boolean preHandle(HttpServletRequest r, HttpServletResponse res, Object o)
			throws Exception {
		
		String id = r.getParameter("userid");
		String pw = r.getParameter("userpass");
		System.out.printf("id : %s, pw : %s", id, pw);
		
		for (int i = 0; i < dao.select().size(); i++) {
			if (id.equals(dao.select().get(i).get("USERID"))) {
				if (pw.equals(dao.select().get(i).get("USERPASS"))) {
					HttpSession session = r.getSession();
					session.setAttribute("loginUser",id);
					return true;
				} else {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호가 일치하지 않습니다.');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
				}
			} else {
			}
		}
		res.setContentType("text/html; charset =utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('존재하지 않는 아이디입니다.');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		return false;	
	
	}
	
	
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle 실행");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
