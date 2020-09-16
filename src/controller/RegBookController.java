package controller;

import java.util.List;

import dto.RegBookDto;
import service.RegBookService;
import view.EndView;

public class RegBookController {
	static RegBookService regbookService = new RegBookService();
	
	/**
	 * Èñ¸Áµµ¼­¸ñ·Ï °Ë»ö
	 */
	public static void selectRegBook() {
		try {
			List<RegBookDto> list = regbookService.selectRegBook();
			EndView.printRegBookList(list);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
