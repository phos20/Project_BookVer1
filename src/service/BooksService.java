package service;

import java.sql.SQLException;
import java.util.List;

import dao.BooksDao;
import dao.BooksDaoImpl;
import dto.BookDto;

public class BooksService {
	BooksDao booksDao = new BooksDaoImpl();

	/**
	 * ���� ��� �˻�
	 */
	public List<BookDto> selectBook() throws Exception{
		List<BookDto> list = booksDao.selectBook();
		if(list.size()==0)throw new Exception("å��� �����ϴ�.");
		return list;
	}
	
	/**
	 * ���� ���
	 */
	public int insertBook(BookDto bookDto) throws Exception{
		int result = booksDao.check(bookDto);
		if(result==0) throw new Exception("��ϵ��� �ʾҽ��ϴ�.");
		return result;
	}
	
	/**
	 * ���� ����
	 */
	public int deleteBook(String bookId) throws Exception{
		int result = booksDao.deleteBook(bookId);
		if(result==0) throw new Exception("�������� �ʾҽ��ϴ�.");
		return result;
	}
	
	/**
	 * ���� �ڵ�� ���� �˻�
	 */
	public BookDto selectByBookId(String bookId) throws Exception{
		BookDto bookDto = booksDao.booksSelectBybooksId(bookId);
		if(bookDto==null) throw new Exception("�ش��ϴ� ������ �������� �ʽ��ϴ�.");
		return bookDto;
	}

	/**
	 * å ���� �˻�
	 * */
	public BookDto selectByName(String booksName) throws Exception {
		BookDto books = booksDao.SelectByName(booksName);
		if(books == null) throw new Exception("�ش��ϴ� ������ �������� �ʽ��ϴ�.");
		return books;
	}

	/**
	 * å �帣 �˻�
	 * */
	public BookDto selectByGenre(String booksGenre) throws Exception {
		BookDto books = booksDao.SelectByGenre(booksGenre);
		if(books == null) throw new Exception("�ش��ϴ� �帣�� �������� �ʽ��ϴ�.");
		return books;
	}
	
	
}
