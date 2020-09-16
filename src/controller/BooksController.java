package controller;

import java.util.List;

import dto.BookDto;
import service.BooksService;
import view.EndView;
import view.FailView;

public class BooksController {
	static BooksService booksService = new BooksService();

	public static void selectBook() {
		try {
			List<BookDto> list = booksService.selectBook();
			EndView.printBooksList(list);
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	public static void selectByName() {
		// TODO Auto-generated method stub
		
	}

	public static void selectByGenre() {
		// TODO Auto-generated method stub
		
	}

}
