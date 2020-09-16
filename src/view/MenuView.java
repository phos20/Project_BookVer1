package view;

import java.util.Scanner;

import controller.AdminController;
import controller.OrderController;
import controller.RegBookController;
import controller.UserController;
import dto.OrderLine;
import dto.Orders;
import dto.RegBookDto;
import dto.UserDto;
import user.UserSet;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);

	/** 초기 화면 */
	public static void menu() {
		System.out.println("--- Book Store에 오신걸 환영합니다. ---");
		System.out.println("      | 1.회원   | 2.비회원    |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			//wishBook("L2");
			//updateUserInfo("L2");
			 deleteUserInfo("L2");
			break;
		case 2:
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}

	}


	/** 유저 메뉴 */
	public static void printUserMenu(String userId) {

		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet());
			System.out.println("-----------------  User Menu -------------------");
			System.out.println("--------------- " + userId + " 님 접속을 환영합니다  --------------");
			System.out.println("| 1.도서검색        | 2.주문     | 3.주문내역확인    | 4.희망도서등록&보기 | ");
			System.out.println("| 5.장바구니담기  | 6.장바구니보기  |  7.마이페이지   |  8.로그아웃     |");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				return;
			case 2:
				printInputOrder(userId);
				break;
			case 3:
				OrderController.selectOrdersByUserId(userId);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7: 
				break;
			case 8:
				break;
			default:
				System.out.println(userId + "님 올바른 번호를 선택해 주세요");
				break;
			}
		}
	}


	// case : 2 -주문
	public static void printInputOrder(String userId) {
		System.out.print("주문할 책 번호 : ");
		String booksId = sc.nextLine();

		System.out.print("주문수량 : ");
		int qty = Integer.parseInt(sc.nextLine());

		System.out.print("배송주소 : ");
		String address = sc.nextLine();

		Orders orders = new Orders(0, null, userId, address, 0);
		OrderLine orderLine = new OrderLine(0, 0, booksId, 0, qty, 0);
		orders.getOrderLineList().add(orderLine);

		OrderController.insertOrders(orders);
	}

	/** 관리자메뉴 */
	public static void printAdminMenu(String userId) {

		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("------------- 관리자 " + userId + "님 모드    -------------");
			System.out.println("| 1.회원관리   | 2.도서관리  | 3.매출관리  | 4.로그아웃  |");

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				return;
			case 2:
				break;
			case 3:
				SalesManagement(userId);
				break;
			case 4:
				break;
			default:
				System.out.println(userId + "관리자님 올바른 번호를 선택해 주세요");
				break;

			}
		}
	}

	// case : 3 - 매출관리
	private static void SalesManagement(String userId) {
		System.out.println("---- 매출관리 메뉴 ----");
		System.out.println(" | 1.오늘의 매출 | 2.기간별 매출 | 3.총 매출   | 4.뒤로가기 ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			AdminController.todaySales();
			break;
		case 2:
			periodSales();
			break;
		case 3:
			AdminController.totalSales();
			break;
		case 4:
			printAdminMenu(userId);
			break;
		default:
			System.out.println("번호에 맞게 선택해주세요");
			break;
		}
	}

	// case : 3-2 - 기간별 매출
	private static void periodSales() {
		System.out.println("언제부터 ? ");
		String startdate = sc.nextLine();
		System.out.println("언제까지 ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);

	}
	// case : 4 희망도서등록&조회
	public static void wishBook(String userId) {
		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet());
			System.out.println("-----------------  User Menu -------------------");
			System.out.println("--------------- " + userId + " 님 접속을 환영합니다  --------------");
			System.out.println("| 1. 희망도서등록        | 2.희망도서목록조회    ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:  
				insertRegBook(userId); //등록
				break;
			case 2:
				RegBookController.selectRegBook(); //조회
				break;
			default:
				System.out.println(userId + "님 올바른 번호를 선택해 주세요");
				break;
			}
		}
		
	}
	
	// case : 4 희망도서등록
	public static void insertRegBook(String userId) {
		
		System.out.println("희망도서 제목: ");
		String regName = sc.nextLine();
		System.out.println("희망도서 저자: ");
		String regWriter = sc.nextLine();
		System.out.println("희망도서 출판사: ");
		String regPublisher = sc.nextLine();
		
		
		RegBookDto wish = new RegBookDto(0, regName, regWriter, regPublisher,userId,null);
		RegBookController.insertRegBook(wish);
		
		
	}
	/**
	 * case: 7-1 마이페이지(회원수정)
	 * */ 
	private static void updateUserInfo(String userId) {
		System.out.println("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.println("회원 이름: ");
		String userName = sc.nextLine();
		System.out.println("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();
		
		UserDto userDto= new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.updateUserInfo(userDto);
	}
	/**
	 *  case : 7-2 마이페이지(회원탈퇴)
	 * */
	private static void deleteUserInfo(String userId) {
		System.out.println("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.println("회원 이름: ");
		String userName = sc.nextLine();
		System.out.println("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();
		
		UserDto userDto= new UserDto(null, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.deleteUserInfo(userDto);
	}
		
	
}
