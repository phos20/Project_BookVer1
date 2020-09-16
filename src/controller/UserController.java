package controller;

import service.UserService;
import view.EndView;

public class UserController {
	static UserService userService = new UserService();
	
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("포인트 적립 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
