package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	static UserService userService = new UserService();
	// 회원정보수정
	public static void updateUserInfo(UserDto userDto) {
		try {
			int result =userService.updateUserInfo(userDto);
			EndView.messagePrint("수정되었습니다.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	//회원탈퇴
	public static void deleteUserInfo(UserDto userDto) {
		try {
			int result = userService.deleteUserInfo(userDto);
			EndView.messagePrint("탈퇴되었습니다.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
