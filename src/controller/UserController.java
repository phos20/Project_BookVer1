package controller;

import service.UserService;
import view.EndView;

public class UserController {
	static UserService userService = new UserService();
	
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("����Ʈ ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
