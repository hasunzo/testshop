package kr.or.bit.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		MemberDAO dao = new MemberDAO();
		String command = request.getParameter("command");
		
		//회원가입
		if(command != null & command.equals("addMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String zip_code = request.getParameter("zip_code");
			String address = request.getParameter("address");
			String detail_address = request.getParameter("detail_address");
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setName(name);
			vo.setEmail(email);
			vo.setTel(tel);
			vo.setZip_code(zip_code);
			vo.setAddress(address);
			vo.setDetail_address(detail_address);
			dao.addMembers(vo);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("location.href='memberLogin.html'");	
			out.print("</script>");
		} else if(command != null & command.equals("login")) {
		//로그인
			System.out.println("login1");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			HttpSession session = request.getSession();
			dao.loginMember(session, response, id, pwd);
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
		
		

	}

}
