package edu.ukc.lms;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookDao;
import model.Book;
import model.Message;


/**
 * Servlet implementation class Login
 */
@WebServlet("/update-customer")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Message m = new Message();
		boolean valid = EmailValidator.getInstance().isValid(request.getParameter("email"));
		if(!valid){
			m.setStatus(0);
			m.setMessage("Invalid email format");
		}
		
		if(valid){
			//System.out.println("zzz" + request.getParameter("firstname"));
			if(request.getParameter("bookTitle").isEmpty() || 
					request.getParameter("bookISBN").isEmpty() ||
					request.getParameter("author").isEmpty() ||
					request.getParameter("edition").isEmpty() ||
					request.getParameter("catName").isEmpty() ||
					request.getParameter("pubName").isEmpty() ||
					request.getParameter("pubDate").isEmpty() ||
					request.getParameter("address").isEmpty() ||
					request.getParameter("").isEmpty() ){
				valid = false;
				m.setStatus(0);
				m.setMessage("Please fill all require field");
			}	
		}
		
		if(valid){
			Book book= new Book();
			book.setBookTitle(request.getParameter("bookTitle"));
			book.setBookISBN(request.getParameter("bookISBN"));
			book.setAuthor(request.getParameter("author"));
			book.setEdition(request.getParameter("edition"));
			book.setCatName(request.getParameter("catName"));
			book.setPubName(request.getParameter("pubName"));
			book.setPubDateFromString(request.getParameter("pubDate"));
			book.setAddress(request.getParameter("address"));
			book.setIssuredDateFromString(request.getParameter("issuredDate"));
			book.setUpdatedDate(new Date());
			int id = Integer.parseInt(request.getParameter("id"));
		
			BookDao bdao = new BookDao();
			if(bdao.update(id, book)){
				m.setStatus(1);
				m.setMessage("Customer is updated successfully");
			}else{
				m.setStatus(0);
				m.setMessage("Updating customer error");
			}
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
        // Set response content type
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, m);

	}
	

}
