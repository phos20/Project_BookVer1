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
	 * 장바구니 담기
	 */
	public static void putCart(String userId, String bookId, int quantity) {
		try {
			//도서코드에 해당하는 도서 검색 
			BookDto bookDto = service.selectByBookId(bookId);
			
			//사용자 찾기
			UserSet us = UserSet.getInstance();
			User user = us.get(userId);	
			
			Map<BookDto, Integer> cart = (Map<BookDto, Integer>)user.getAttribute("cart"); //상품 , 수량 저장 
			
			//장바구니가 비어있다면 생성 
			if(cart == null) { 
				cart = new HashMap<>(); 
				user.setAttribute("cart", cart);
			}
			
			//장바구니에서 상품 찾기
			Integer oldQuantity = cart.get(bookDto);
			if(oldQuantity != null) { //장바구니에 이미 상품이 있다면
				quantity += oldQuantity; //수량을 누적
			}
			
			cart.put(bookDto, quantity); //장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");
			
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 보기
	 */
	public static void showCart(String id) {
		UserSet us = UserSet.getInstance();
		User user = us.get(id);
		Map<BookDto,Integer> cart = (Map<BookDto, Integer>) user.getAttribute("cart");
		if(cart == null ) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printCart(id , cart);
		}
	}
	

}
