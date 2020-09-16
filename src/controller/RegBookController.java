package controller;

import java.util.List;


import dto.RegBookDto;
import service.RegBookService;
import view.EndView;
import view.FailView;

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
	   /**
	    *  Èñ¸Áµµ¼­ µî·Ï
	    * */
	   public static void insertRegBook(RegBookDto wish) {
			try {
				regbookService.insertRegbook(wish);
				EndView.messagePrint("Èñ¸Áµµ¼­ µî·ÏµÇ¾ú½À´Ï´Ù");
			}catch(Exception e) {
				FailView.errorMessage(e.getMessage());
			}
		}
}
