package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import controller.AdminController;
import controller.BooksController;
import controller.CartController;
import controller.OrderController;
import controller.RegBookController;
import controller.UserController;
import dao.OrderDAO;
import dao.OrderDAOImpl;
import dto.BookDto;
import dto.CartDto;
import dto.OrderLine;
import dto.Orders;
import dto.Pay;
import dto.RegBookDto;
import dto.UserDto;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);
	static OrderDAO orderDao = new OrderDAOImpl();

	/** 
	 * 초기 화면
	 * */
	public static void menu() {
		System.out.println("--- Book Store에 오신걸 환영합니다. ---");
		System.out.println("      | 1.회원   | 2.비회원    |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			Login();
			break;
		case 2:
			MenuView.nonmember();
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}
	}
	
	/**
	 * 로그인
	 */
	private static void Login() {
		System.out.println("ID = ");
		String userId = sc.nextLine();
		System.out.println("PWD = ");
		String userPwd = sc.nextLine();

		UserController.Login(userId, userPwd);
	}

	/**
	 * case : 2 -비회원-
	 */
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

	/**
	 * case : 2-1 -회원 가입-
	 */
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

	
////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	/** 유저 메뉴 */
	public static void printUserMenu(String userId) {

		while (true) {
			System.out.println("-----------------  User Menu -------------------");
			System.out.println("--------------- " + userId + " 님 접속을 환영합니다  --------------");
			System.out.println("| 1.도서검색        | 2.주문     | 3.주문내역확인 &결제  | 4.희망도서등록&보기 | ");
			System.out.println("| 5.장바구니담기  | 6.장바구니보기  |  7.마이페이지   |  8.로그아웃     |");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.booksearch(userId);
				return;
			case 2:
				printInputOrder(userId);
				break;
			case 3:
				selectOrderAndPay(userId);
				break;
			case 4:
				wishBook(userId);
				break;
			case 5:
				putCart(userId);
				break;
			case 6:
				showCart(userId);
				break;
			case 7:
				myPage(userId);
				break;
			case 8:
				logOut(userId);
				break;
			default:
				System.out.println(userId + "님 올바른 번호를 선택해 주세요");
				break;
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -도서검색 -
	 */
	public static void booksearch(String userId) {
		System.out.println("---- 도서 검색 ----");
		System.out.println(" | 1.전체 검색 | 2.제목 검색 | 3.장르 검색 | 4.뒤로가기 |");
		System.out.println("-------------------");
		System.out.print("선택> ");

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			System.out.println("-------------------");
			BooksController.selectBook();
			booksearch(userId);
			break;
		case 2:
			System.out.println("-------------------");
			searchName();
			booksearch(userId);
			break;
		case 3:
			System.out.println("-------------------");
			searchGenre();
			booksearch(userId);
			break;
		case 4:
			printUserMenu(userId);
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			booksearch(userId);
			break;
		}
	}

	/**
	 * case : 1-2 -제목 검색-
	 */
	private static void searchName() {
		System.out.print("검색할 책 제목 : ");
		String booksName = sc.nextLine();

		BooksController.selectByName(booksName);

	}

	/**
	 * case : 1-3 -장르 검색-
	 */
	private static void searchGenre() {
		System.out.print("검색할 책 장르 : ");
		String booksGenre = sc.nextLine();

		BooksController.selectByGenre(booksGenre);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -주문-
	 */
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 - 주문내역확인& 결제
	 */
	private static void selectOrderAndPay(String userId) {
		System.out.println("| 1.주문내역 확인  | 2.결제  |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			OrderController.selectOrdersByUserId(userId);
			break;
		case 2:
			Payment(userId);
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}
	}

	/**
	 * case : 3-2 -결제
	 */
	public static void Payment(String userId) {
		try {
			List<Pay> paylist = orderDao.ordersPriceByUserId(userId);
			int price = 0;
			for (Pay pay : paylist) {
				price += pay.getTotalAmount();

			}

			// price = list.get(0).getTotalAmount();
			System.out.println("총금액 : " + price + " 결제 하시겠습니까?");
			System.out.println(" | 1. 결제  | 2.취소  |");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserController.Pay(price, userId);
				break;
			case 2:
				break;
			default:
				System.out.println("올바른 번호를 누르세요");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 4 희망도서등록&조회
	 */
	public static void wishBook(String userId) {
		while (true) {
			System.out.println("-----------------  User Menu -------------------");
			System.out.println("--------------- " + userId + " 님 접속을 환영합니다  --------------");
			System.out.println("| 1. 희망도서등록		| 2. 희망도서목록조회		| 3. 나가기		");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				insertRegBook(userId); // 등록
				break;
			case 2:
				RegBookController.selectRegBook(); // 조회
				break;
			case 3:
				printUserMenu(userId); // 나가기

			default:
				System.out.println(userId + "님 올바른 번호를 선택해 주세요");
				break;
			}
		}
	}

	/**
	 * case : 4-1 희망도서등록
	 */
	public static void insertRegBook(String userId) {

		System.out.println("희망도서 제목: ");
		String regName = sc.nextLine();
		System.out.println("희망도서 저자: ");
		String regWriter = sc.nextLine();
		System.out.println("희망도서 출판사: ");
		String regPublisher = sc.nextLine();

		RegBookDto wish = new RegBookDto(0, regName, regWriter, regPublisher, userId, null);
		RegBookController.insertRegBook(wish);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 5 -장바구니 담기-
	 */
	public static void putCart(String userId) {
		System.out.println("-- 장바구니 담기 --");
		System.out.print("도서코드: ");
		String booksId = sc.nextLine();
		System.out.print("수량: ");
		int quantity = Integer.parseInt(sc.nextLine());

		CartDto cartDto = new CartDto(0, userId, booksId, quantity, null);

		CartController.insertCart(cartDto);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	/**
	 * case : 6 -장바구니 보기-
	 */
	public static void showCart(String userId) {
		System.out.println("*** 장바구니 목록 ***");
		Map<BookDto, Integer> map = CartController.selectCart(userId);
		Set<BookDto> set = map.keySet(); // 장바구니에 담긴 책정보 꺼내기

		// if(map==null) {
		// System.out.println("장바구니가 비어있습니다.");
		// }
		// else {

		System.out.println("1. 주문하기 2. 삭제하기 3. 나가기");
		int num = Integer.parseInt(sc.nextLine());

		if (num == 1) {
			System.out.print("배송주소: ");
			String address = sc.nextLine();

			Orders orders = new Orders(0, null, userId, address, 0);

			Iterator<BookDto> iterator = set.iterator(); // 반복자 얻기
			while (iterator.hasNext()) { // 객체 수 만큼 돌기
				BookDto bookDto = iterator.next(); // 한 개의 객체 가져옴

				OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId(), 0, map.get(bookDto), 0);
				orders.getOrderLineList().add(orderLine);
			}

			OrderController.insertOrders(orders);
			CartController.deleteCart(userId);
		} else if (num == 2) {
			CartController.deleteCart(userId);
		} else if (num == 3)
			return;
		// else System.out.println("다시 입력해주세요. ");
		// }

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 7 -마이페이지-
	 */
	public static void myPage(String userId) {
		while (true) {
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
			System.out.println("★★★★★★★★★여기는 현재 마이페이지 입니다★★★★★★★★");
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
			System.out.println("| 1.내정보 수정하기    | 2. 내정보 탈퇴하기    | 3. 포인트 충전하기 |4. 뒤로가기    ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				updateUserInfo(userId);
				break;
			case 2:
				deleteUserInfo(userId);
				break;
			case 3:
				userPoint(userId);
				break;
			case 4:
				printUserMenu(userId);
			default:
				System.out.println(userId + "님 올바른 번호를 선택해 주세요");
				break;
			}
		}
	}

	/**
	 * case: 7-1 -회원수정-
	 */
	private static void updateUserInfo(String userId) {
		System.out.println("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.println("회원 이름: ");
		String userName = sc.nextLine();
		System.out.println("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.updateUserInfo(userDto);
	}

	/**
	 * case : 7-2 -회원탈퇴-
	 */
	private static void deleteUserInfo(String userId) {
		System.out.println("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.println("회원 이름: ");
		String userName = sc.nextLine();
		System.out.println("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(null, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.deleteUserInfo(userDto);
	}

	/**
	 * case : 7-3 -포인트-
	 */
	public static void userPoint(String userId) {

		System.out.println("현재 포인트: " + UserController.selectPoint(userId));
		System.out.println("1. 포인트 등록하기  2. 뒤로 ");
		int num = Integer.parseInt(sc.nextLine());
		if (num == 1) {
			System.out.print("등록할 포인트: ");
			int point = Integer.parseInt(sc.nextLine());

			UserController.userPoint(userId, point);
		}
		if (num == 2)
			return;
		// else System.out.println("다시 입력해주세요. ");
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	/**
	 * case : 8 -로그아웃-
	 */
	private static void logOut(String userId) {
		System.out.println("로그아웃 되었습니다.");
		System.exit(0);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/** 
	 * 관리자메뉴 
	 * */
	public static void printAdminMenu(String userId) {

		while (true) {
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("------------- 관리자 " + userId + "님 모드    -------------");
			System.out.println("| 1.회원관리   | 2.도서관리  | 3.매출관리  | 4.로그아웃  |");

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserManagement();
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
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////	

	/**
	 * case : 1 -회원관리-
	 */
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
		case 3:
			return;
		default:
			System.out.println("관리자님 올바른 번호를 선택해 주세요");
			break;
		}
	}

	/**
	 * case 1-2 : -회원등업-
	 */
	private static void updateUserGrade() {
		System.out.println("등업 할 회원 ID : ");
		String userId = sc.nextLine();
		System.out.println("등급 : ");
		String grade = sc.nextLine();

		AdminController.updateUserGrade(grade, userId);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * case : 2 -도서관리-
	 */
	public static void manageBook() {
		System.out.println("1. 도서목록 보기 2. 희망도서목록 보기 3. 도서 등록 4. 도서 삭제 5. 나가기");
		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1: // 도서목록
			BooksController.selectBook();
			return;
		case 2: // 희망도서목록
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
	 * case : 2-3 -도서 등록-
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

		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre,
				booksPrice, bookStock, null); // book 객체 생성

		BooksController.insertBook(bookDto);
	}

	/**
	 * case : 2-4 -도서 삭제-
	 */
	public static void printDeleteBook() {
		System.out.println("삭제도서 코드: ");
		String bookId = sc.nextLine();

		BooksController.deleteBook(bookId);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	/**
	 * case : 3 -매출관리-
	 */
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

	/**
	 * case : 3-2 -기간별 매출-
	 */
	private static void periodSales() {
		System.out.println("언제부터 ? ");
		String startdate = sc.nextLine();
		System.out.println("언제까지 ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);
	}
	
} // MainView 끝   

