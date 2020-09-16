package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	
	static UserService userService = new UserService();
	/**로그인*/
	public static void Login(String userId, String userPwd) {
		try {
		UserDto userDto =userService.Login(userId,userPwd);
		EndView.messagePrint(userDto.getUserId()+"님 로그인 성공");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
		
	
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("포인트 적립 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
