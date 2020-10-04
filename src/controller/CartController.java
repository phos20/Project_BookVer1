package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.BookDto;
import dto.CartDto;
import service.CartService;
import view.EndView;
import view.FailView;

public class CartController {
	private static CartService cartService = new CartService();

	/**
	 * 장바구니 담기
	 */
	public static void insertCart(CartDto cartDto) {

		try {
			int result = cartService.insertCart(cartDto);
			EndView.messagePrint("장바구니 담기 성공");
		} catch (Exception e) {
			EndView.messagePrint(e.getMessage());
		}

	}

	/**
	 * 장바구니 보기
	 */
	public static Map<BookDto, Integer> selectCart(String userId) {

		Map<BookDto, Integer> map = new HashMap<BookDto, Integer>();
		try {
			map = cartService.selectCart(userId);
			EndView.showCart(map);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return map;
	}

	/**
	 * 장바구니 삭제
	 */
	public static void deleteCart(String userId) {
		try {
			int result = cartService.deleteCart(userId);
			EndView.messagePrint("장바구니 삭제 완료");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}
}
