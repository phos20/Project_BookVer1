package controller;

import dto.UserDto;

import service.UserService;
import view.EndView;
import view.FailView;
import view.MenuView;

public class UserController {

	static UserService userService = new UserService();

	/**
	 * 회원 가입
	 */
	public static void signUp(UserDto userDto) {
		try {
			userService.signup(userDto);
			EndView.messagePrint("회원 가입을 축하드립니다");
			MenuView.printUserMenu(userDto.getUserId());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	// 회원정보수정
	public static void updateUserInfo(UserDto userDto) {
		try {
			userService.updateUserInfo(userDto);
			EndView.messagePrint("수정되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	// 회원탈퇴
	public static void deleteUserInfo(UserDto userDto) {
		try {
			userService.deleteUserInfo(userDto);
			EndView.messagePrint("탈퇴되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			MenuView.myPage(userDto.getUserId());
		}
	}

	/** 로그인 */

	public static void Login(String userId, String userPwd) {
		try {
			UserDto userDto = userService.Login(userId, userPwd);

			if (userDto.getGrade().equals("관리자")) {
				System.out.println("───────── 관리자님 어서오세요 ─────────");
				System.out.println();
				MenuView.printAdminMenu(userId);
			} else {
				System.out.println("───────── 접속을 환영합니다 ─────────");
				System.out.println();
				MenuView.printUserMenu(userId);
			}

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 포인트 검색
	 */
	public static int selectPoint(String userId) {
		int point = 0;
		try {
			point = userService.getUserPoint(userId);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return point;
	}

	/**
	 * 포인트 등록
	 */
	public static void userPoint(String userId, int point) {
		try {
			int result = userService.userPoint(userId, point);
			EndView.printMessage("포인트 적립 완료");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());

		}

	}

	/** 포인트차감 */
	public static void Pay(double price, String userId) {
		try {
			userService.Pay(price, userId);
			EndView.messagePrint("결제 성공");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
