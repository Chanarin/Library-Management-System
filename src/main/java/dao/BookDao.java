package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.Book;
import util.DBCP2DataSourceUtils;

public class BookDao {

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate(DBCP2DataSourceUtils.getDataSource());

	public BookDao() {}

	public Book getById(Integer id) throws SQLException {
		if (id == null) {
			return null;
		}
		Book book = null;
		String sql = "SELECT * FROM tbl_books WHERE book_id = ?";
		System.out.println(sql);
        book = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BookMapper());
        System.out.println(book);
		return book;
	}
	
	public List<Book> getAll(int limit, int offset, String search){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SQL_CALC_FOUND_ROWS " + "t.* FROM tbl_books t ");
		if(search != "" && search != null){
			// fitler by phone number or email address
			sql.append("WHERE book_title LIKE '%" + search + "%'" );
			sql.append(" OR cat_name LIKE '%" + search + "%'" );
		}
		sql.append(" ORDER BY book_id DESC LIMIT " + offset + ", " + limit );
		System.out.println(sql.toString());
		return jdbcTemplate.query( sql.toString(), new BookMapper());
	}
	
	public int getFoundRows(){
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_books", Integer.class);
	}

	public boolean insert(Book book) {
		int inserted = jdbcTemplate.update(
		        "INSERT INTO "
		        + "tbl_books ("
		        + "book_id, "
		        + "book_title, "
		        + "isbn, "
		        + "author,"
		        + "edition, "
		        + "cat_name, "
		        + "pub_name, "
		        + "pub_date, "
		        + "address, "
		        + "issure_date, "
		        + "updated_date ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
		        book.getBookTitle(), 
		        book.getBookISBN(), 
		        book.getAuthor(),
		        book.getEdition(),
		        book.getCatName(), 
		        book.getPubName(),
		        book.getPubDateToString(),
		        book.getAddress(),
		        book.getIssuredDateToString(),
		        book.getUdatedDateToString()
		);	
		
		if(inserted > 0) return true;
		else return false;
	}
	
	public boolean delete(Integer id){
		if(id == null) return false;
		if(jdbcTemplate.update( "DELETE FROM tbl_b WHERE cus_id = ?", Integer.valueOf(id)) > 0) return true;
		else return false;
	}
	
	public boolean update(final int id, Book book) {
		int updated = jdbcTemplate.update(
		        "UPDATE  "
		        + "tbl_books ("
				+ "book_id, "
				+ "book_title, "
				+ "isbn, "
				+ "author,"
				+ "edition, "
				+ "cat_name, "
				+ "pub_name, "
				+ "pub_date, "
				+ "address, "
				+ "issure_date, "
				+ "updated_date ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				book.getBookTitle(), 
				book.getBookISBN(), 
				book.getAuthor(),
				book.getEdition(),
				book.getCatName(), 
				book.getPubName(),
				book.getPubDateToString(),
				book.getAddress(),
				book.getIssuredDateToString(),
				book.getUdatedDateToString(),
		        id
		);
		if(updated > 0) return true;
		else return false;
	}

}
  final class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setBookTitle(rs.getString("book_title"));
		book.setBookISBN(rs.getString("isbn"));
		book.setAuthor(rs.getString("author"));
		book.setEdition(rs.getString("edition"));
		book.setCatName(rs.getString("cat_name"));
		book.setPubName(rs.getString("pub_name"));
		book.setPubDateFromString(rs.getString("pub_date"));
		book.setAddress(rs.getString("address"));
		book.setIssuredDateFromString(rs.getString("issure_date"));
		book.setUpdatedDateFromString(rs.getString("updated_date"));
		return book;
	}
}