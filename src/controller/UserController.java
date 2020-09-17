package controller;

import dto.UserDto;
import service.UserService;
import view.EndView;
import view.FailView;
import view.MenuView;

public class UserController {

	static UserService userService = new UserService();

	/**
	 * ȸ�� ����
	 */
	public static void signUp(UserDto userDto) {
		try {
			userService.signup(userDto);
			EndView.messagePrint("ȸ�� ������ ���ϵ帳�ϴ�");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	// ȸ����������
	public static void updateUserInfo(UserDto userDto) {
		try {
			userService.updateUserInfo(userDto);
			EndView.messagePrint("�����Ǿ����ϴ�.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	// ȸ��Ż��
	public static void deleteUserInfo(UserDto userDto) {
		try {
			userService.deleteUserInfo(userDto);
			EndView.messagePrint("Ż��Ǿ����ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}




	/** �α��� */

	public static void Login(String userId, String userPwd) {
		try {
			UserDto userDto = userService.Login(userId, userPwd);

			if (userDto.getGrade().equals("������")) {
				System.out.println("�����ڴ� �������");
				MenuView.printAdminMenu(userId);
			} else {
				System.out.println("������ ȯ���մϴ�");
				MenuView.printUserMenu(userId);
			}

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ����Ʈ �˻�
	 */
	public static int selectPoint(String userId) {
		int point = 0;
		try {
			point = userService.getUserPoint(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return point;
	}
	
	/**
	 * ����Ʈ ���
	 */
/**
 *  ����Ʈ ����
 * */
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("����Ʈ ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}

	}

}
