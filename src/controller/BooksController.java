package controller;

import java.util.List;

import dto.BookDto;
import service.BooksService;
import view.EndView;
import view.FailView;

public class BooksController {
	static BooksService booksService = new BooksService();
	
	/**
	 * ���� ��� �˻� 
	 */
	public static void selectBook() {
		try {
			List<BookDto> list = booksService.selectBook();
			EndView.printBookList(list);
		} catch (Exception e){
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ���� ���
	 */
	public static void insertBook(BookDto bookDto) {
		try {
			int result = booksService.insertBook(bookDto);
			EndView.messagePrint("��� �Ϸ�");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ���� ����
	 */
	public static void deleteBook(String bookId) {
		try {
			int result = booksService.deleteBook(bookId);
			EndView.messagePrint("���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}

	/**å ���� �˻�*/

	  public static void selectByName(String booksName) {
	     try {
	        BookDto books = booksService.selectByName(booksName);
	        EndView.printBookNameList(books);
	     } catch (Exception e){
	        e.printStackTrace();
	        FailView.errorMessage(e.getMessage());
	     }
	  }

	  /**å �帣 �˻�*/
	  public static void selectByGenre(String booksGenre) {
	     try {
	    	List<BookDto> list = booksService.selectByGenre(booksGenre);
	        EndView.printBookGenreList(list);
	     } catch (Exception e){
	        e.printStackTrace();
	        FailView.errorMessage(e.getMessage());
	     }
	  }
	  
	  /**
	   * ���� �ڵ�� ���� �˻�
	   */
	  public static BookDto selectByBooksId(String booksId) {
		  BookDto bookDto = null;
		  try{
			  bookDto = booksService.selectByBooksId(booksId);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return bookDto;
	  }



}
