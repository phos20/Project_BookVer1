package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
	
	static UserService userService = new UserService();
<<<<<<< HEAD
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
=======
	/**�α���*/
	public static void Login(String userId, String userPwd) {
		try {
		UserDto userDto =userService.Login(userId,userPwd);
		EndView.messagePrint(userDto.getUserId()+"�� �α��� ����");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
		
	
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("����Ʈ ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
>>>>>>> branch 'master' of https://github.com/phos20/Project_BookVer1.git
		}
		
	}

}
