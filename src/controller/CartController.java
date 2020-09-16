package controller;

import user.User;
import user.UserSet;

import java.util.HashMap;
import java.util.Map;

import view.EndView;
import view.FailView;
import dto.BookDto;
import service.BooksService;

public class CartController {
	private static BooksService service = new BooksService();
	
	/**
	 * ��ٱ��� ���
	 */
	public static void putCart(String userId, String bookId, int quantity) {
		try {
			//�����ڵ忡 �ش��ϴ� ���� �˻� 
			BookDto bookDto = service.selectByBookId(bookId);
			
			//����� ã��
			UserSet us = UserSet.getInstance();
			User user = us.get(userId);	
			
			Map<BookDto, Integer> cart = (Map<BookDto, Integer>)user.getAttribute("cart"); //��ǰ , ���� ���� 
			
			//��ٱ��ϰ� ����ִٸ� ���� 
			if(cart == null) { 
				cart = new HashMap<>(); 
				user.setAttribute("cart", cart);
			}
			
			//��ٱ��Ͽ��� ��ǰ ã��
			Integer oldQuantity = cart.get(bookDto);
			if(oldQuantity != null) { //��ٱ��Ͽ� �̹� ��ǰ�� �ִٸ�
				quantity += oldQuantity; //������ ����
			}
			
			cart.put(bookDto, quantity); //��ٱ��Ͽ� ��ǰ �ֱ�
			EndView.printMessage("��ٱ��Ͽ� ��ҽ��ϴ�");
			
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ��ٱ��� ����
	 */
	public static void showCart(String id) {
		UserSet us = UserSet.getInstance();
		User user = us.get(id);
		Map<BookDto,Integer> cart = (Map<BookDto, Integer>) user.getAttribute("cart");
		if(cart == null ) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printCart(id , cart);
		}
	}
	

}
