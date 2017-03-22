package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private int bookId;
	private String bookTitle;
	private String bookISBN;
	private String author;
	private String edition;
	private String catName;
	private String pubName;
	private Date pubDate;
	private String address;
	private Date issuredDate;
	private Date updatedDate;
	
	public Book(){}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	public Date getPubDate() {
		return pubDate;
	}
	public String getPubDateToString(){
		if(this.pubDate != null) return (new SimpleDateFormat("yyyy-MM-dd")).format(this.pubDate);
		else return "";
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public void setPubDateFromString(String pubDate){
		if(pubDate != null) {
			try{
				this.setPubDate((new SimpleDateFormat("yyyy-MM-dd")).parse(pubDate));
			}catch(ParseException e){
				e.printStackTrace();
			}		
		}
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getIssuredDate() {
		return issuredDate;
	}
	public String getIssuredDateToString(){
		if(this.issuredDate != null) return (new SimpleDateFormat("yyyy-MM-dd")).format(this.issuredDate);
		else return "";
	}
	public void setIssuredDate(Date issuredDate) {
		this.issuredDate = issuredDate;
	}
	public void setIssuredDateFromString(String issuredDate){
		if(issuredDate != null) {
			try{
				this.setIssuredDate((new SimpleDateFormat("yyyy-MM-dd")).parse(issuredDate));
			}catch(ParseException e){
				e.printStackTrace();
			}		
		}
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public String getUdatedDateToString(){
		if(this.updatedDate != null) return (new SimpleDateFormat("yyyy-MM-dd")).format(this.updatedDate);
		else return "";
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setUpdatedDateFromString(String updatedDate){
		if(updatedDate != null) {
			try{
				this.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd")).parse(updatedDate));
			}catch(ParseException e){
				e.printStackTrace();
			}		
		}
	}
	public String getActionButtons(){
		return "<a data-id='"+this.bookId+"' class='edit' style='margin-right:10px; cursor:pointer' role='button'><i class='glyphicon glyphicon-pencil'></i></a>"
				+ "<a data-id='"+this.bookId+"' class='delete' style='cursor:pointer;' ><i class='glyphicon glyphicon-remove ' style='color:red'></i></a>";
	}
	

}
