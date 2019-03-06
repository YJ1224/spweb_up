package com.hyj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hyj.service.Service;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	Service sv;
	@Resource
	private String upath;

	// 회원가입
	@GetMapping("join.do")
	public String jointestGet() {
		return "admin/join";
	}

	@RequestMapping(value = "join.do", method = RequestMethod.POST)
	public String jointestPost(@RequestParam Map<String, String> pm) {
		sv.insert(pm);
		return "admin/login";
	}
	
	// 아이디 중복 확인
	@GetMapping(value="idCheck.do")
	public String idcheck(@RequestParam String userid){
		int result = sv.idcheck(userid);
		String redirect = null;
		try {
			redirect = String.format("redirect:/id/idcheck.jsp?result=%s&userid=%s", result
					,URLEncoder.encode(userid, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return redirect;		
	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "../index";
	}

	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	// 상품등록
	// 요청 GET/daotest.do
	@GetMapping("upform")
	public String imgtestGet() {
		return "admin/upform";

	}

	// 요청 POST/daotest.do
	@RequestMapping(value = "/up", method = RequestMethod.POST)
	public ModelAndView imgtestPost(@RequestParam("p_name") String n, @RequestParam("c_code") String c,
			@RequestParam("c_name") String cn, @RequestParam("p_price") String p, @RequestParam("p_stock") String s,
			@RequestParam("p_des") String d, @RequestParam("p_img") MultipartFile f) {
		sv.imginsert(n, c, cn, p, s, d, f);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/upform");
		return mv;
	}

	// 상품목록
	@GetMapping("/up.do")
	public ModelAndView imgtestget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up");
		return mv;
	}

	@GetMapping("/up2.do")
	public ModelAndView img1testget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up2");
		return mv;
	}

	@GetMapping("/up_1.do")
	public ModelAndView img_1testget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up_1");
		return mv;
	}

	@GetMapping("/up_2.do")
	public ModelAndView img_2testget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up_2");
		return mv;
	}

	@GetMapping("/up2_1.do")
	public ModelAndView img2_1testget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up2_1");
		return mv;
	}

	@GetMapping("/up2_2.do")
	public ModelAndView img2_2testget() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imglist", sv.imgselect());
		mv.setViewName("admin/up2_2");
		return mv;
	}

	// 상세보기
	@GetMapping("/view.do")
	public ModelAndView view(@RequestParam("p") int p_num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("view", sv.view(p_num));
		mv.setViewName("admin/view");
		return mv;
	}
	/*--------------------------카트담기 session------------------*/
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView cart(HttpServletRequest request
			,@RequestParam("c_stock") int c_stock
			,@RequestParam("p_num") int p_num){ 
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = session.getAttribute("loginUser").toString();
		System.out.println("아이디 :"+userid);
		System.out.println("수량 :"+c_stock);
		System.out.println("상품번호 :"+p_num);
		sv.cartinsert(userid, p_num, c_stock);
		session.setAttribute("loginUser", userid);
		mv.setViewName("redirect:/cartlist.do?u="+userid);
		return mv;
	}
	/*----------------------------카트 정보 보기---------------------*/
	@GetMapping("/cartlist.do")
	public ModelAndView view(@RequestParam("u") String userid, 
			HttpServletRequest request,Model m) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		userid = session.getAttribute("loginUser").toString();
		mv.addObject("carts",sv.cartselect(userid));
		mv.addObject("sum",sv.cartsum(userid));
		mv.setViewName("admin/cartList");
		return mv;
	}
	
	/*------------------------ 카트 삭제 -------------------*/
	//카트 부분 삭제
	
	@RequestMapping("delete.do")
	public ModelAndView delete(HttpServletRequest request
			,@RequestParam int c_num){ 
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = session.getAttribute("loginUser").toString();
		System.out.println("상품번호 :"+c_num);
		System.out.println("아이디 :"+userid);
		sv.cartdelete(c_num,userid);
		session.setAttribute("loginUser", userid);
		mv.setViewName("redirect:/cartlist.do?u="+userid);
		return mv;
	}
	// 카트 전체 삭제
	@RequestMapping("delete2.do")
	public ModelAndView delete(HttpServletRequest request){ 
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = session.getAttribute("loginUser").toString();
		System.out.println("아이디 :"+userid);
		sv.cartdelete1(userid);
		session.setAttribute("loginUser", userid);
		mv.setViewName("redirect:/cartlist.do?u="+userid);
		return mv;
	}
	
	/*------------------------- 주문하기 ----------------------------*/
	@RequestMapping(value = "/cartList", method = RequestMethod.POST)
	public ModelAndView order(HttpServletRequest request,
			@RequestParam("orderrec") String o,
			@RequestParam("useraddr1") String a1, @RequestParam("useraddr2") String a2,
			@RequestParam("useraddr3") String a3,
			@RequestParam("orderphon") String p, @RequestParam("c_stock") String c) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = session.getAttribute("loginUser").toString();
		// 주문고유번호 설정
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 for(int i = 1; i <= 6; i ++) {
			  subNum += (int)(Math.random() * 10);
			 }
			 
	    String orderid = ymd + "_" + subNum;
		sv.orderinsert(orderid, userid, o, a1, a2, a3, p, c);
		sv.orderview(orderid);
		sv.cartdelete1(userid);
		mv.setViewName("redirect:/orderlist.do?u="+userid);
		return mv;
	}
	
	//사용자 주문내역
	@RequestMapping(value="orderlist.do", method = RequestMethod.GET)
	public ModelAndView orderlist(HttpServletRequest request,@RequestParam("u") String userid) {
	ModelAndView mv = new ModelAndView();
	HttpSession session = request.getSession();
	userid = session.getAttribute("loginUser").toString();
	mv.addObject("orderlist", sv.orderlist(userid));
	mv.setViewName("admin/orderList");
	return mv;
	}
	
	//관리자 주문내역
	@RequestMapping(value="managerlist.do", method = RequestMethod.GET)
	public ModelAndView managerlist() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("managerlist", sv.managerlist());
		mv.setViewName("admin/managerList");
		return mv;
	}
	// 배송상태 변경
	@RequestMapping(value="delivery.do",method = RequestMethod.POST)
	public ModelAndView delivery(@RequestParam Map<String, String> m) {
		ModelAndView mv = new ModelAndView();
		for(int i=0; i<100; i++) {
			sv.delivery(m.get("delivery"+i), m.get("orderid"+i));	
		}
		mv.setViewName("redirect:/managerlist.do");
		return mv;
	}
	
	//주문내역상세
	@RequestMapping(value="/deliveryview", method = RequestMethod.GET)
	public ModelAndView deliveryview(@RequestParam("d") String orderid, Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("deliveryview", sv.deliveryview(orderid));
		mv.setViewName("admin/orderList2");
		return mv;
	}
	/*---------------------------FAQ-----------------------------*/
	@GetMapping("notice.do")
	public String noticeGet() {
		return "admin/notice";
	}
	//등록
	@RequestMapping(value = "notice.do", method = RequestMethod.POST)
	public String notice(@RequestParam Map<String, String> n) {
		sv.notice(n);
		return "../index";
	}
	// FAQ 목록, 페이징
	@GetMapping("naticelist.do")
	public ModelAndView noticelist(@RequestParam Map<String, String> m) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("notice", sv.noticeselect(m));
		mv.addObject("page", sv.pageing(m));
		System.out.println(sv.pageing(m));
		mv.setViewName("admin/noticelist");
		return mv;
	}
	
	// FAQ 상세보기
	@GetMapping(value="noticeview.do")
	public ModelAndView noticeview(@RequestParam("n") int n_num) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("noticeview", sv.noticeview(n_num));
		mv.setViewName("admin/noticeview");
		return mv;
	}
	//검색
	@GetMapping(value="search.do")
	public String search(String search, Model m) {
		m.addAttribute("faq", sv.search(search));
		return "admin/noticelist";
	}
}
