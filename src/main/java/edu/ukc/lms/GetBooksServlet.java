package edu.ukc.lms;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


import dao.BookDao;
import model.BootstrapTableBookFormatter;
import model.Book;

/**
 * Servlet implementation class Login
 */
@WebServlet("/getBooks")
public class GetBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		BookDao bdao = new BookDao();
		Integer offset = Integer.parseInt(request.getParameter("offset"));
		Integer limit = Integer.parseInt(request.getParameter("limit"));
		String search = request.getParameter("search");
		List<Book> books = bdao.getAll(limit, offset, search);
        try {
            ObjectMapper mapper = new ObjectMapper();
            BootstrapTableBookFormatter bsBookMapper = new BootstrapTableBookFormatter();
            bsBookMapper.setTotal(bdao.getFoundRows());
            bsBookMapper.setRows(books);
            // Set response content type
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            mapper.writeValue(out, bsBookMapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("something is wrong with server " + e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	

}
