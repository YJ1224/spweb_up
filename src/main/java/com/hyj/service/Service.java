package com.hyj.service;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hyj.dao.Dao;
@org.springframework.stereotype.Service
public class Service {
	// Dao 주입
		@Autowired
		Dao dao;
		// 회원가입 Map형식으로 insert
		public void insert(Map<String,String> d) {
			dao.insert(d);
		}
		
		// 회원 select
		public ArrayList<HashMap<String, String>> select() {
			return dao.select();
		}
		
		// 아이디 중복확인
		public int idcheck(String userid){
			if(dao.idcheck(userid).size() == 0) {
				return -1;
			}else {
				return 1;
			}
		}
		
		// 상품상세보기
		public HashMap<String,String> view(int p_num){
			return dao.view(p_num);
		}
		
		// 상품보기
		public ArrayList<HashMap<String, String>> imgselect(){
			return dao.imgselect();
		}
		
		// 카트 담기
		public void cartinsert(String userid,int p_num,int c_stock){
			dao.cartinsert(userid, p_num, c_stock);
		}
		
		// 카트목록
		public ArrayList<HashMap<String, String>> cartselect(String userid) {
			return dao.cartselect(userid);
		}
		
		// 부분 삭제
		public void cartdelete(int c_num,String userid) {
			dao.cartdelete(c_num, userid);
		}
		
		// 전체 삭제
		public void cartdelete1(String userid) {
			dao.cartdelete1(userid);
		}
		
		//카트 총 금액
		public ArrayList<HashMap<String, String>> cartsum(String userid) {
			return dao.cartsum(userid);
		}
		
		// 주문하기
		public Map<String, String> orderinsert(String orderid,String userid,String o, String a1, String a2, String a3, 
				String p, String c){
			Map<String, String> order = new HashMap<String, String>(){{
			put("orderrec", o);
			put("useraddr1",a1);
			put("useraddr2",a2);
			put("useraddr3",a3);
			put("orderphon",p);
			put("c_stock", c);
			}};
			dao.orderinsert(orderid, userid, order);
			return order;
		}
		
		// 주문상세보기
		public void orderview(String orderid) {
			dao.orderview(orderid);
		}
		
		// 주문내역보기
		public ArrayList<HashMap<String, String>> orderlist(String userid){
			return dao.orderlist(userid);
		}
		
		// 관리자 - 모든 사용자 주문내역 보기
		public ArrayList<HashMap<String, String>> managerlist(){
			return dao.managercart();
		}
		
		// 배송상태
		public void delivery(String delivery, String orderid) {
			dao.delivery(delivery, orderid);
		}
		
		// 주문내역상세보기
		public ArrayList<HashMap<String, String>> deliveryview(String orderid){
			return dao.deliverview(orderid);
		}
		//FAQ 등록
		public void notice(Map<String,String> n) {
			dao.notice(n);	
		}
		// FAQ 목록, 페이징
		public ArrayList<HashMap<String, String>> noticeselect(Map<String, String> m){
		      String pgnum = m.get("pg");
		      String bEanum = m.get("bEa");
		      if (bEanum == "" || Objects.isNull(bEanum)) {
		         bEanum = "5";
		      } else {
		         bEanum = m.get("bEa");
		      }
		      if (pgnum == "" || Objects.isNull(pgnum)) {
		         pgnum = "1";
		      } else {   
		         pgnum = m.get("pg");
		      }
			return dao.noticeselect(pgnum,bEanum);
		}
		// FAQ 상세보기
		public HashMap<String,String> noticeview(int n_num){
			return dao.noticeview(n_num);
		}
		//검색
		public ArrayList<HashMap<String, String>> search(String search){
			return dao.search(search);
		}
		/*-----------------파일업로드-------------*/
		@Autowired 
		String upath;
		public Map<String, String> imginsert(String n, String s, String c,String p, String d,String cn,MultipartFile f) {
			// 파일이름
			System.out.printf("파일이름 : %s", f.getOriginalFilename());
			
			
			UUID uid = UUID.randomUUID(); //랜덤생성
			String oname = f.getOriginalFilename();
			String fname = uid.toString() + "_" + oname;
			
			Map<String, String> finfos = new HashMap<String,String>(){{
				put("p_name",n); 
				put("c_code",c); 
				put("c_name", cn);
				put("p_price",p); 
				put("p_stock",s); 
				put("p_des",d);
				put("p_img",fname);
				
				
			}}; 
			dao.imginsert(finfos);
			
			
			File file = new File(upath, fname);
			try{
				FileCopyUtils.copy(f.getBytes(), file); //���� copy
				return finfos;
			} catch (Exception e) {
				return null;
			}
		}
		//페이징
		public HashMap<String, String> pageing(Map<String, String> map) {
		      String pgnum = map.get("pg");
		      String bEanum = map.get("bEa");
		      
		      if(bEanum == "" || Objects.isNull(bEanum)) {
		         bEanum = "5";
		      } else {
		         bEanum = map.get("bEa");
		      }
		      
		      if(pgnum == "" || Objects.isNull(pgnum)) {
		         pgnum = "1";   
		      } else {
		         pgnum = map.get("pg");
		      }
		      
		      HashMap<String, String> page = dao.page();
		      String entbcnt = page.get("CT");
		      String lstbnum = page.get("CP");
		      int pgN = Integer.parseInt(pgnum);
		      
		      int stn = ((pgN-1)/5)*5 + 1;
		      int endn = stn + 5 - 1;
		      
		      HashMap<String, String> paging = new HashMap<String, String>();
		      
		      paging.put("pg", pgnum);
		      paging.put("bEa", bEanum);
		      paging.put("stn", Integer.toString(stn));
		      paging.put("endn", Integer.toString(endn));
		      paging.put("entbcnt", entbcnt);
		      paging.put("lstbnum", lstbnum);
		      
		      return paging; 
		   }
}
