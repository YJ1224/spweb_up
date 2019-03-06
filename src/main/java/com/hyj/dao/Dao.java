package com.hyj.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.sun.corba.se.pept.transport.Connection;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import commons.Db;


@Component
public class Dao {
	/*----------------------------------회원가입-------------------------------*/
	// 회원가입
	public void insert(Map<String, String> d) {
		String sql = String.format(
				"INSERT INTO MEMBER " + "(USERID, USERPASS, USERNAME,USERAGE,USEREMAIL,PHONE, ADDR,USERDATE)\r\n"
						+ "VALUES('%s','%s','%s','%s','%s','%s','%s',sysdate)",
				d.get("userid"), d.get("userpass"), d.get("username"), d.get("userage"), d.get("useremail"),
				d.get("phone"), d.get("addr"));
		Db.executeUpdate(sql);
	}

	// member select
	public ArrayList<HashMap<String, String>> select() {
		return Db.selectListMap("SELECT * FROM member");
	}
	
	//아이디중복확인
	public ArrayList<HashMap<String, String>> idcheck(String userid){
		String sql = String.format("select * from member where userid = '%s'", userid);
		return Db.selectListMap(sql);
		}

	/*--------------------------------상품등록(이미지업로드)-------------------------------------------*/
	// img insert
	public void imginsert(Map<String, String> dd) {
		String sql = String.format(
				"INSERT INTO product (p_num, p_name, c_code,c_name,p_price,p_stock,p_des,p_img,p_date)"
						+ "VALUES(SEQ_product.NEXTVAL,'%s','%s','%s','%s','%s','%s','%s',sysdate)",
				dd.get("p_name"), dd.get("c_code"), dd.get("c_name"), dd.get("p_price"), dd.get("p_stock"),
				dd.get("p_des"), dd.get("p_img"));
		System.out.println(sql);
		Db.executeUpdate(sql);
	}
	
	// img select
	public ArrayList<HashMap<String, String>> imgselect() {
		String sql = String.format("SELECT * FROM product ORDER BY p_num DESC");
		return Db.selectListMap(sql);
	}

	/*------------------------상세보기-----------------------------*/
	public HashMap<String, String> view(int p_num) {
		String sql = String.format("SELECT * FROM product WHERE p_num = %s", p_num);
		return Db.selectMap(sql);

	}

	/*--------------------------카트--------------------------*/
	// 카트담기
	public void cartinsert(String userid, int p_num, int c_stock) {
		String s_p_num = String.valueOf(p_num);
		String sql = String.format(
				"insert into cart (c_num, userid, p_num, c_stock) " + "values (cart_seq.nextval, '%s', %s, %s)",
				userid, s_p_num, c_stock);
		Db.executeUpdate(sql);
	}
	// 카트목록
	public ArrayList<HashMap<String, String>> cartselect(String userid) {
		String sql = String.format("select row_number() over(order by c.c_num desc) as num,\r\n" + 
				"c.c_num, c.userid, c.p_num, c.c_stock, c.adddate,\r\n" + 
				"p.p_name, p.p_price, p.p_img from cart c inner join product p on c.p_num = p.p_num where c.USERID='%s'"
				,userid);
		return Db.selectListMap(sql);
	}
	// 카트(부분) 삭제
	public void cartdelete(int c_num,String userid) {
		String sql = String.format("delete CART WHERE C_NUM = %s AND USERID = '%s'", c_num,userid);
		Db.executeUpdate(sql);
	}
	// 카트(전체) 삭제
	public void cartdelete1(String userid) {
		String sql =String.format("delete CART WHERE USERID = '%s'", userid);
		Db.executeUpdate(sql);
	}
	// 카트 금액
	public ArrayList<HashMap<String, String>> cartsum(String userid) {
		String sql = String.format("select nvl(sum(p_price * c_stock), 0) money from cart c, "
				+ "product p where c.P_NUM = p.P_NUM and c.USERID='%s'", userid);
		return Db.selectListMap(sql);	
	}
	
	/*-------------------------------주문하기-------------------------------*/
	// 주문하기
	public void orderinsert(String orderid,String userid,Map<String, String> o) {
		String sql = String.format(
				"INSERT INTO tb_order (orderid, userid, orderrec, useraddr1, useraddr2, useraddr3, orderphon, c_stock, orderDate)\r\n" + 
				"VALUES('%s','%s','%s','%s','%s','%s','%s',%s,sysdate)",orderid , userid,
				o.get("orderrec"),o.get("useraddr1"), o.get("useraddr2"), o.get("useraddr3"),
				o.get("orderphon"),o.get("c_stock"));
		Db.executeUpdate(sql);
	}
	
	// 주문상세보기
	public void orderview(String orderid) {
		String sql = String.format("insert into tb_order_view(ordernum, orderid, p_num, c_stock) \r\n" + 
				"select order_seq.nextval,'%s', p_num, c_stock from cart",orderid);
		Db.executeUpdate(sql);
	}
	
	// 주문목록보기
	public ArrayList<HashMap<String, String>> orderlist(String userid){
		String sql=String.format("select orderid, userid, orderrec, useraddr1, useraddr2, useraddr3, orderphon, c_stock, orderdate, delivery from  tb_order\r\n" + 
				"where userid = '%s'", userid);
		return Db.selectListMap(sql);
	}
	/*-------------------------------관리자용 주문관리----------------------------*/
	
	// 관리자용 모든주문 LIST
	public ArrayList<HashMap<String, String>> managercart(){
	return Db.selectListMap("select orderid, userid, orderrec, useraddr1, useraddr2, useraddr3, orderphon, c_stock, orderdate, delivery from tb_order");	
	}
	
	//관리자 배송상태 확인
	public void delivery(String delivery, String orderid) {
		String sql=String.format("update tb_order set delivery = '%s' where orderid = '%s'",
				delivery, orderid);
		Db.executeUpdate(sql);
	}
	
	//주문상세보기
	public ArrayList<HashMap<String, String>> deliverview(String orderid) {
		String sql=String.format(" select o.orderid, o.userid, o.orderrec, o.useraddr1, o.useraddr2, o.useraddr3, o.orderphon, o.c_stock, o.orderdate\r\n" + 
				"    , o.delivery, v.ordernum, v.p_num,v.c_stock, p.p_num, p.p_img, p.p_price from tb_order o inner join\r\n" + 
				"    tb_order_view v on o.orderid = v.orderid inner join product p on v.p_num = p.p_num where o.orderid= '%s'", orderid);
		return Db.selectListMap(sql);
	}

	/*-----------------------------------FAQ------------------------*/
	// 등록
	public void notice(Map<String, String> n) {
		String sql = String.format("insert into notice (n_num, n_title, n_content) \r\n" + 
				"values (notice_seq.nextval, '%s','%s')", n.get("n_title"),n.get("n_content"));
		Db.executeUpdate(sql);
	}
	
	//페이징
	public ArrayList<HashMap<String, String>> noticeselect(String pg, String bEa){
		String sql = String.format("SELECT * FROM V_notice WHERE F_NOTICE(R,%s,%s) = 1", pg,bEa);
		return Db.selectListMap(sql);
		
	}
	//페이징 번호 받기
	public HashMap<String, String> page(){
		String sql = String.format("SELECT COUNT(*) ct, CEIL(COUNT(*)/5) cp FROM V_notice");
		return Db.selectMap(sql);
		
	}
	//상세보기
	public HashMap<String, String> noticeview(int n_num) {
		String sql = String.format("select * from notice where n_num = %s", n_num);
		return Db.selectMap(sql);

	}
	//검색하기
	public ArrayList<HashMap<String, String>> search(String search){
		String sql = String.format("select * from notice where n_title || n_content like '%%%s%%'", search);
		return Db.selectListMap(sql);
	}

}