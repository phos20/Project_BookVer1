package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	
	static UserService userService = new UserService();
<<<<<<< HEAD
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
=======
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
>>>>>>> branch 'master' of https://github.com/phos20/Project_BookVer1.git
		}
		
	}

}
