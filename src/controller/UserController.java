package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	static UserService userService = new UserService();
	/**
	 * 회원 가입
	 */
	public static void signUp(UserDto userDto) {
		try {
			userService.signup(userDto);
			EndView.messagePrint("회원 가입을 축하드립니다");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
