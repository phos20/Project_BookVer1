package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	static UserService userService = new UserService();
	/**
	 * ȸ�� ����
	 */
	public static void signUp(UserDto userDto) {
		try {
			userService.signup(userDto);
			EndView.messagePrint("ȸ�� ������ ���ϵ帳�ϴ�");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
