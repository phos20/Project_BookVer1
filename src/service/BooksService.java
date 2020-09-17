package service;

import java.sql.SQLException;
import java.util.List;

import dao.BooksDao;
import dao.BooksDaoImpl;
import dto.BookDto;

public class BooksService {
	BooksDao booksDao = new BooksDaoImpl();

	/**
	 * 도서목록 검색
	 */
	public List<BookDto> selectBook() throws Exception{
		List<BookDto> list = booksDao.selectBook();
		if(list.size()==0)throw new Exception("책재고가 없습니다.");
		return list;
	}
	
	/**
	 * 도서 등록
	 */
	public int insertBook(BookDto bookDto) throws Exception{
		int result = booksDao.check(bookDto);
		if(result==0) throw new Exception("등록되지 않았습니다.");
		return result;
	}
	
	/**
	 * 도서 삭제
	 */
	public int deleteBook(String bookId) throws Exception{
		int result = booksDao.deleteBook(bookId);
		if(result==0) throw new Exception("삭제되지 않았습니다.");
		return result;
	}
	
	/**
	 * 도서코드로 도서 검색
	 */
	public BookDto selectByBookId(String bookId) throws Exception{
		BookDto bookDto = booksDao.booksSelectBybooksId(bookId);
		if(bookDto==null) throw new Exception("해당하는 도서가 존재하지 않습니다.");
		return bookDto;
	}
	
}
