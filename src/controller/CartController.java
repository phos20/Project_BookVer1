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
	 * ��ٱ��� ���
	 */
	public static void insertCart(CartDto cartDto) {

		try {
			int result = cartService.insertCart(cartDto);
			EndView.messagePrint("��ٱ��� ��� ����");
		} catch (Exception e) {
			EndView.messagePrint(e.getMessage());
		}

	}

	/**
	 * ��ٱ��� ����
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
	 * ��ٱ��� ����
	 */
	public static void deleteCart(String userId) {
		try {
			int result = cartService.deleteCart(userId);
			EndView.messagePrint("��ٱ��� ���� �Ϸ�");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}
}
