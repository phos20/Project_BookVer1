package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	static UserService userService = new UserService();
	// ȸ����������
	public static void updateUserInfo(UserDto userDto) {
		try {
			int result =userService.updateUserInfo(userDto);
			EndView.messagePrint("�����Ǿ����ϴ�.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	//ȸ��Ż��
	public static void deleteUserInfo(UserDto userDto) {
		try {
			int result = userService.deleteUserInfo(userDto);
			EndView.messagePrint("Ż��Ǿ����ϴ�.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
