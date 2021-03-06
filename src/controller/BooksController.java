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
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** 비회원 도서 검색 */
	public static void selectBooks() {
		try {
			List<BookDto> list = booksService.selectBook();
			EndView.printBooksList(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 도서 등록
	 */
	public static void insertBook(BookDto bookDto) {
		try {
			int result = booksService.insertBook(bookDto);
			EndView.messagePrint("등록 완료");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 도서 재고추가
	 */
	public static void updateBook(String booksId, int bookStock) {
		try {
			int result = booksService.updateBook(booksId, bookStock);
			EndView.messagePrint("추가 완료");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	/**
	 * 도서 삭제
	 */
	public static void deleteBook(String bookId) {
		try {
			int result = booksService.deleteBook(bookId);
			EndView.messagePrint("삭제 완료");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/** 책 제목 검색 */
	public static void selectByName(String booksName) {
		try {
			BookDto books = booksService.selectByName(booksName);
			EndView.printBookNameList(books);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** 책 장르 검색 */
	public static void selectByGenre(String booksGenre) {
		try {
			List<BookDto> list = booksService.selectByGenre(booksGenre);
			EndView.printBookGenreList(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 도서 코드로 도서 검색
	 */
	public static BookDto selectByBooksId(String booksId) {
		BookDto bookDto = null;
		try {
			bookDto = booksService.selectByBooksId(booksId);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return bookDto;
	}


}
