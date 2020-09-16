package service;

import java.util.List;

import dao.BooksDao;
import dao.BooksDaoImpl;
import dto.BookDto;

public class BooksService {
	BooksDao booksDao = new BooksDaoImpl();

	public List<BookDto> selectBook() throws Exception {
		List<BookDto> list = booksDao.selectBook();
		if(list.size() == 0)throw new Exception("현재 책이 없습니다.");
		return list;	
		}
	}


