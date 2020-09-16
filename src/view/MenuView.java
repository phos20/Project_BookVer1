package view;

import java.util.Scanner;

import controller.AdminController;
import controller.BooksController;
import controller.CartController;
import controller.OrderController;
import controller.UserController;
import controller.RegBookController;
import controller.UserController;
import dto.BookDto;
import dto.OrderLine;
import dto.Orders;
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
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}

	}
	//case : - 로그인
	private static void Login() {
		System.out.println(" ID = ");
		String userId = sc.nextLine();
		System.out.println("PWD = ");
		String userPwd = sc.nextLine();
		
		UserController.Login(userId, userPwd);
		
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
				putCart();
				break;
			case 6:
				showCart();
				break;
			case 7:
				userPoint();
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
				manageBook();
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
	
	public static void manageBook() {
		System.out.println("1. 도서목록 보기 2. 희망도서목록 보기 3. 도서 등록 4. 도서 삭제 5. 나가기");
		int num = Integer.parseInt(sc.nextLine());
		
		switch(num) {
			case 1 : // 도서목록
				BooksController.selectBook(); 
				return;	
			case 2 : // 희망도서목록 
				RegBookController.selectRegBook();
				break;
			case 3: // 도서등록 
				printInsertBook();
				break;
			case 4: // 도서삭제
				printDeleteBook();
				break;
			case 5:
				System.exit(0);
		
		}
	}
	
	
	/**
	 * 도서 등록 
	 */
	public static void printInsertBook() {
		
		System.out.println("등록도서 코드: ");
		String booksId = sc.nextLine();
		System.out.println("등록도서 제목 : ");
		String booksName = sc.nextLine();
		System.out.println("등록도서 저자 : ");
		String booksWriter = sc.nextLine();
		System.out.println("등록도서 출판사 : ");
		String booksPublisher = sc.nextLine();
		System.out.println("등록도서 출판일 : ");
		String booksPubDate = sc.nextLine();
		System.out.println("등록도서 장르 : ");
		String booksGenre = sc.nextLine();
		System.out.println("등록도서 가격 : ");
		int booksPrice = Integer.parseInt(sc.nextLine());
		System.out.println("등록도서 개수 : ");
		int bookStock = Integer.parseInt(sc.nextLine());
		
		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre, booksPrice, bookStock, null); // book 객체 생성 
		
		BooksController.insertBook(bookDto); 
	}
	
	
	/**
	 * 도서 삭제
	 */
	public static void printDeleteBook() {
		System.out.println("삭제도서 코드: ");
		String bookId = sc.nextLine();
		
		BooksController.deleteBook(bookId);
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
	
	/**
	 * 장바구니 담기
	 */
	public static void putCart() {
		System.out.println("-- 장바구니 담기 --");
		System.out.print("도서코드: ");
		String goodsId = sc.nextLine();
		System.out.print("수량: ");
		int quantity = Integer.parseInt(sc.nextLine());
		
		CartController.putCart("C",goodsId,quantity);
	}
	
	/**
	 * 장바구니 보기
	 */
	public static void showCart() {
		CartController.showCart("C");
	}
	
	/**
	 * 포인트
	 */
	public static void userPoint() {
		System.out.println("등록할 포인트: ");
		int point = Integer.parseInt(sc.nextLine());
		
		UserController.userPoint("L", point);
	}

}
