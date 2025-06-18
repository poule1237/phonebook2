package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDAO;
import com.javaex.vo.PersonVO;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("PhonebookController");

		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) {
			System.out.println("리스트");

			PhonebookDAO phonebookDAO = new PhonebookDAO();
			List<PersonVO> personList = phonebookDAO.personSelect();

			request.setAttribute("pList", personList);
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);

		} else if ("wform".equals(action)) {
			System.out.println("등록폼");

			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);

		} else if ("write".equals(action)) {
			System.out.println("등록");

			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVO personVO = new PersonVO(name, hp, company);

			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personInsert(personVO);

			response.sendRedirect("/phonebook2/pbc?action=list");

		} else if ("delete".equals(action)) {
			System.out.println("삭제");

			int no = Integer.parseInt(request.getParameter("no"));
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personDelete(no);

			response.sendRedirect("/phonebook2/pbc?action=list");

		} else if ("uform".equals(action)) {
			System.out.println("수정폼 요청");

			int no = Integer.parseInt(request.getParameter("no"));
			
			System.out.println(no);
			
			
			
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			PersonVO personVO = phonebookDAO.personSelectOne(no);
			
			System.out.println(personVO);
		
			request.setAttribute("pVO", personVO);
			RequestDispatcher rd = request.getRequestDispatcher("/editForm.jsp");
			rd.forward(request, response);

		} else if ("update".equals(action)) {
			System.out.println("수정 실행");

			int personId = Integer.parseInt(request.getParameter("personId"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVO personVO = new PersonVO(personId, name, hp, company);

			
			
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personUpdate(personVO);

			response.sendRedirect("/phonebook2/pbc?action=list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
