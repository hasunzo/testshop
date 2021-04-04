package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet rs;
	
	public void addMembers(MemberVO memberVO){
		try {
			con = SingletonHelper.getConnection("oracle");
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String tel = memberVO.getTel();
			String zip_code = memberVO.getZip_code();
			String address = memberVO.getAddress();
			String detail_address = memberVO.getDetail_address();
			
			String query = "insert into tb_member";
			query += "(MEMBER_ID, MEMBER_LOGIN_ID, MEMBER_PW, MEMBER_NAME, MEMBER_EMAIL, MEMBER_TEL, MEMBER_ZIP_CODE, MEMBER_ADDRESS, MEMBER_DETAIL_ADDRESS)";
			query += "values(TB_MEMBER_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, tel);
			pstmt.setString(6, zip_code);
			pstmt.setString(7, address);
			pstmt.setString(8, detail_address);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginMember(HttpSession session, HttpServletResponse response, String id, String pwd) {
		try {
			con = SingletonHelper.getConnection("oracle");
			String sql="select MEMBER_LOGIN_ID, MEMBER_PW from tb_member where MEMBER_LOGIN_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("dsdsd");
			while(rs.next()) {
				if(pwd.equals(rs.getString("MEMBER_PW"))) {
					session.setAttribute("id", rs.getString("MEMBER_LOGIN_ID"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
