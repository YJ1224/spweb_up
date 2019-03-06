package com.hyj.web;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyj.dao.Dao;

/* 怨듯넻湲곕뒫援ы쁽 �겢�옒�뒪 : */
public class Ic implements HandlerInterceptor {
	
	@Autowired
	Dao dao;

	@Override 
	// �듅�젙 踰붿쐞�쓽 �뙣�궎吏��뿉 �엳�뒗 硫붿꽌�뱶媛� �떎�뻾�릺湲� �쟾�뿉 �떎�뻾�맆 怨듯넻 硫붿꽌�뱶
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
