package controller;

import java.util.List;


import dto.RegBookDto;
import service.RegBookService;
import view.EndView;
import view.FailView;

public class RegBookController {
	static RegBookService regbookService = new RegBookService();
	/**
	    * ���������� �˻�
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
	    *  ������� ���
	    * */
	   public static void insertRegBook(RegBookDto wish) {
			try {
				regbookService.insertRegbook(wish);
				EndView.messagePrint("������� ��ϵǾ����ϴ�");
			}catch(Exception e) {
				FailView.errorMessage(e.getMessage());
			}
		}
}
