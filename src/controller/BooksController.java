package controller;

import java.util.List;

import dto.BookDto;
import service.BooksService;
import view.EndView;
import view.FailView;

public class BooksController {
	static BooksService booksService = new BooksService();
	
	/**
	 * 도서목록 검색 
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
	 * 도서 등록
	 */
	public static void insertBook(BookDto bookDto) {
		try {
			int result = booksService.insertBook(bookDto);
			if(result!=0) System.out.println("등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 도서 삭제
	 */
	public static void deleteBook(String bookId) {
		try {
			int result = booksService.deleteBook(bookId);
			if(result!=0) System.out.println("삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}

	   /**책 제목 검색*/
	   public static void selectByName(String booksName) {
	      try {
	         BookDto books = booksService.selectByName(booksName);
	         EndView.printBookNameList(books);
	      } catch (Exception e){
	         e.printStackTrace();
	         FailView.errorMessage(e.getMessage());
	      }
	   }

	   /**책 장르 검색*/
	   public static void selectByGenre(String booksGenre) {
	      try {
	         BookDto books = booksService.selectByGenre(booksGenre);
	         EndView.printBookGenreList(books);
	      } catch (Exception e){
	         e.printStackTrace();
	         FailView.errorMessage(e.getMessage());
	      }
	   }

}
