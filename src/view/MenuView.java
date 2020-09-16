package view;

import java.util.Scanner;

import controller.AdminController;
import controller.BooksController;
import controller.OrderController;
import controller.UserController;
import dto.OrderLine;
import dto.Orders;
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
			break;
		case 2:
			MenuView.nonmember();
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}

	}
	
	//case : 2 - 비회원-
	public static void nonmember() {
		System.out.println("---- 비회원 메뉴 ----");
		System.out.println(" | 1.회원가입 | 2.도서 목록 보기 | ");
		
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			MenuView.signUp();
			break;
		case 2:
			BooksController.selectBook();
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}
	}
			
	// case : 2-1 회원 가입
	private static void signUp() {
			System.out.println("가입 ID : ");
			String userId = sc.nextLine();
				
			System.out.println("가입 PW : ");
			String userPwd = sc.nextLine();
				
			System.out.println("가입 이름 : ");
			String userName = sc.nextLine();
				
			System.out.println("가입 폰번호 : ");
			String userPhone = sc.nextLine();
			
			UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
			UserController.signUp(userDto);
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
				MenuView.booksearch();
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

	//case : 1 -도서검색-
		public static void booksearch() {
			System.out.println("---- 도서 검색 ----");
			System.out.println(" | 1.전체 검색 | 2.제목 검색 | 3.장르 검색 |");
			System.out.println("-------------------");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				BooksController.selectBook();
				break;
			case 2:
				BooksController.selectByName();
				break;
			case 3:
				BooksController.selectByGenre();
				break;
			default:
				System.out.println("올바른 번호를 선택해 주세요");
				break;
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
				UserManagement();
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
	
	//case : 1 -회원관리
	private static void UserManagement() {
		System.out.println("---- 회원 관리 메뉴 ----");
		System.out.println("| 1. 회원 목록 보기  |  2.회원등업  | 3. 뒤로가기  |");

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			AdminController.selectUserList();
			break;
		case 2:
			updateUserGrade();
			break;
		case 3 :
			return;
		default:
			System.out.println("관리자님 올바른 번호를 선택해 주세요");
			break;
		}
		
	}
	//case 1-2 : 회원등업
	private static void updateUserGrade() {
		System.out.println("등업 할 회원 ID : ");
		String userId = sc.nextLine();
		System.out.println("등급 : ");
		String grade = sc.nextLine();
		
		AdminController.updateUserGrade(grade,userId);
		

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

}
