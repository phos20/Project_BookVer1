package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	static UserService userService = new UserService();
	/**�α���*/
	public static void Login(String userId, String userPwd) {
		try {
		UserDto userDto =userService.Login(userId,userPwd);
		EndView.messagePrint(userDto.getUserId()+"�� �α��� ����");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

}
