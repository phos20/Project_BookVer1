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
	 */
	public static void menu() {
		System.out.println("─────────────────────────────────── ");
		System.out.println("      Book Store에 오신걸 환영합니다.     ");
		System.out.println("───────────────────────────────────");
		System.out.println("\t①  회원           ② 비회원   ");
		System.out.println();
		System.out.print("선택> ");
		int menu = Integer.parseInt(sc.nextLine());
		System.out.println();
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
		System.out.print("ID = ");
		String userId = sc.nextLine();
		System.out.print("PWD = ");
		String userPwd = sc.nextLine();
		System.out.println();

		UserController.Login(userId, userPwd);
	}

	/**
	 * case : 2 -비회원-
	 */
	public static void nonmember() {
		System.out.println("────────── 비회원 메뉴 ────────── ");
		System.out.println("\t방문자님 메뉴를 선택하세요");
		System.out.println("────────────────────────────");
		System.out.println("         ① 회원가입  ② 도서 목록 보기  ③ 종료");
		System.out.println();
		System.out.print("선택> ");
		int menu = Integer.parseInt(sc.nextLine());
		System.out.println();
		switch (menu) {
		case 1:
			MenuView.signUp();
			break;
		case 2:
			BooksController.selectBooks();
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}
	}

	/**
	 * case : 2-1 -회원 가입-
	 */
	private static void signUp() {
		System.out.print("가입 ID : ");
		String userId = sc.nextLine();

		System.out.print("가입 PW : ");
		String userPwd = sc.nextLine();

		System.out.print("가입 이름 : ");
		String userName = sc.nextLine();

		System.out.print("가입 폰번호 : ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.signUp(userDto);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** 유저 메뉴 */
	public static void printUserMenu(String userId) {

		while (true) {
			System.out.println("───────────────── User Menu ─────────────────── ");
			System.out.println("      \t접속자 : " + userId + " 님  메뉴를 선택하세요 ");
			System.out.println("─────────────────────────────────────────────── ");
			System.out.println(" ①도서검색         ②주문               ③주문확인 &결제     ④희망도서등록&보기  ");
			System.out.println(" ⑤장바구니담기   ⑥장바구니보기   ⑦마이페이지           ⑧로그아웃     ");
			System.out.println();
			System.out.print("선택> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.booksearch(userId);
				break;
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
		System.out.println("──────────────── 도서 검색  ────────────────");
		System.out.println("                    접속자 : " + userId + " 님  메뉴를 선택하세요 ");
		System.out.println("─────────────────────────────────────── ");
		System.out.println("     ① 전체 검색   ② 제목 검색  ③ 장르 검색  ④ 뒤로가기 ");
		System.out.println();
		System.out.print("선택> ");

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			BooksController.selectBook();
			booksearch(userId);
			break;
		case 2:
			searchName();
			booksearch(userId);
			break;
		case 3:
			searchGenre();
			booksearch(userId);
			break;
		case 4:
			printUserMenu(userId);
			break;
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
		System.out.println();
		BooksController.selectByName(booksName);

	}

	/**
	 * case : 1-3 -장르 검색-
	 */
	private static void searchGenre() {
		System.out.print("검색할 책 장르 : ");
		String booksGenre = sc.nextLine();
		System.out.println();
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
		System.out.println(" ① 주문확인     ②결제  ");
		System.out.println();
		System.out.print("선택> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			selectOrderMenu(userId);
			break;
		case 2:
			Payment(userId);
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}
	}

	/** case : 3-1 -주문확인 */
	private static void selectOrderMenu(String userId) {
		System.out.println(" ① 주문내역확인     ②주문취소  ");
		System.out.println();
		System.out.print("선택> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			OrderController.selectOrdersByUserId(userId);
			break;
		case 2:
			cancleOrders(userId);
			break;
		default:
			System.out.println("올바른 번호를 선택해 주세요");
			break;
		}

	}

	/** case :3-1-2 -주문취소 */
	private static void cancleOrders(String userId) {
		System.out.println("삭제하고자하는 주문 번호를 입력하세요 :");
		int orderNo = Integer.parseInt(sc.nextLine());
		OrderController.cancleOrders(userId, orderNo);

	}

	/**
	 * case : 3-2 -결제
	 */
	public static void Payment(String userId) {
		try {
			List<Pay> paylist = orderDao.ordersPriceByUserId(userId);
			double price = 0;
			for (Pay pay : paylist) {
				price += pay.getTotalAmount();

			}

			System.out.println("총금액 : " + Double.parseDouble(String.format("%.1f", price)) + " 결제 하시겠습니까?");
			System.out.println("         ①  결제         ② 취소  ");
			System.out.println();
			System.out.print("선택> ");
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
			System.out.println("──────────────── 희망도서 등록&조회  ────────────────");
			System.out.println("         ① 희망도서등록    ② 희망도서목록조회   ③ 나가기");
			System.out.print("선택> ");
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

		System.out.print("희망도서 제목: ");
		String regName = sc.nextLine();
		System.out.print("희망도서 저자: ");
		String regWriter = sc.nextLine();
		System.out.print("희망도서 출판사: ");
		String regPublisher = sc.nextLine();

		RegBookDto wish = new RegBookDto(0, regName, regWriter, regPublisher, userId, null);
		RegBookController.insertRegBook(wish);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 5 -장바구니 담기-
	 */
	public static void putCart(String userId) {
		System.out.println("────────── 장바구니 담기 ──────────");
		System.out.print("도서코드: ");
		String booksId = sc.nextLine();
		System.out.print("수량: ");
		int quantity = Integer.parseInt(sc.nextLine());
		System.out.println();

		CartDto cartDto = new CartDto(0, userId, booksId, quantity, null);

		if (BooksController.selectByBooksId(booksId) == null) {
			System.out.println("도서가 존재하지 않아 장바구니에 담을 수 없습니다. ");
		} else
			CartController.insertCart(cartDto);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	/**
	 * case : 6 -장바구니 보기-
	 */
	public static void showCart(String userId) {
		System.out.println("─────────────── 장바구니 목록 ───────────────");
		Map<BookDto, Integer> map = CartController.selectCart(userId);
		Set<BookDto> set = map.keySet(); // 장바구니에 담긴 책정보 꺼내기

		// if(map==null) {
		// System.out.println("장바구니가 비어있습니다.");
		// }
		// else {
		System.out.println();
		System.out.println("① 주문하기    ② 삭제하기    ③나가기");
		System.out.print("선택> ");
		System.out.println();
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
			System.out.println("─────────────────────────────────────────────");
			System.out.println("               여기는 현재 마이페이지 입니다 ");
			System.out.println("─────────────────────────────────────────────");
			System.out.println("  ①내정보 수정하기    ② 내정보 탈퇴하기    ③ 포인트 충전하기  ④ 뒤로가기    ");
			System.out.println();
			System.out.print("선택> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				updateUserInfo(userId);
				break;
			case 2:
				deleteUserInfo(userId);
				menu();
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
		System.out.print("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.print("회원 이름: ");
		String userName = sc.nextLine();
		System.out.print("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.updateUserInfo(userDto);
	}

	/**
	 * case : 7-2 -회원탈퇴-
	 */
	private static void deleteUserInfo(String userId) {
		System.out.print("회원 비밀번호: ");
		String userPwd = sc.nextLine();
		System.out.print("회원 이름: ");
		String userName = sc.nextLine();
		System.out.print("회원 휴대폰번호: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.deleteUserInfo(userDto);
	}

	/**
	 * case : 7-3 -포인트-
	 */
	public static void userPoint(String userId) {

		System.out.println("현재 포인트: " + UserController.selectPoint(userId));
		System.out.println(" ① 포인트 등록하기    ② 뒤로 ");
		System.out.println();
		System.out.print("선택> ");
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

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 8 -로그아웃-
	 */
	private static void logOut(String userId) {
		System.out.println("로그아웃 되었습니다.");
		UserDto userDto = new UserDto(null, null, null, null, 0, null, 0, null);
		System.exit(0);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 관리자메뉴
	 */
	public static void printAdminMenu(String userId) {

		while (true) {
			System.out.println("──────────────  Admin Menu ───────────────");
			System.out.println("\t\t관리자 " + userId + "님 모드   ");
			System.out.println("──────────────────────────────────────────");
			System.out.println("  ①회원관리   ②도서관리   ③매출관리   ④로그아웃  ");
			System.out.println();
			System.out.print("선택> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserManagement();
				break;
			case 2:
				manageBook();
				break;
			case 3:
				SalesManagement(userId);
				break;
			case 4:
				logOut(userId);
				break;
			default:
				System.out.println(userId + "관리자님 올바른 번호를 선택해 주세요");
				break;
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -회원관리-
	 */
	private static void UserManagement() {
		System.out.println("─────────── 회원 관리 메뉴 ───────────");
		System.out.println("    ① 회원 목록 보기   ② 회원등업   ③ 뒤로가기 ");
		System.out.print("선택> ");
		System.out.println();

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
		System.out.print("등업 할 회원 ID : ");
		String userId = sc.nextLine();
		System.out.print("등급 : ");
		String grade = sc.nextLine();

		AdminController.updateUserGrade(grade, userId);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -도서관리-
	 */
	public static void manageBook() {
		System.out.println("─────────────── 도서관리 메뉴 ──────────────────");
		System.out.println(" ① 도서목록 보기   ②희망도서목록 보기   ③도서 등록   ④도서 삭제   ⑤나가기");
		System.out.println();
		System.out.print("선택> ");
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
			return;

		}
	}

	/**
	 * case : 2-3 -도서 등록-
	 */
	public static void printInsertBook() {

		System.out.print("등록도서 코드: ");
		String booksId = sc.nextLine();
		System.out.print("등록도서 제목 : ");
		String booksName = sc.nextLine();
		System.out.print("등록도서 저자 : ");
		String booksWriter = sc.nextLine();
		System.out.print("등록도서 출판사 : ");
		String booksPublisher = sc.nextLine();
		System.out.print("등록도서 출판일 : ");
		String booksPubDate = sc.nextLine();
		System.out.print("등록도서 장르 : ");
		String booksGenre = sc.nextLine();
		System.out.print("등록도서 가격 : ");
		int booksPrice = Integer.parseInt(sc.nextLine());
		System.out.print("등록도서 개수 : ");
		int bookStock = Integer.parseInt(sc.nextLine());

		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre,
				booksPrice, bookStock, null); // book 객체 생성

		BooksController.insertBook(bookDto);
	}

	/**
	 * case : 2-4 -도서 삭제-
	 */
	public static void printDeleteBook() {
		System.out.print("삭제도서 코드: ");
		String bookId = sc.nextLine();

		BooksController.deleteBook(bookId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 -매출관리-
	 */
	private static void SalesManagement(String userId) {
		System.out.println("──────────────── 매출관리 메뉴 ────────────────");
		System.out.println("    ① 오늘의 매출   ② 기간별 매출   ③ 총 매출   ④ 뒤로가기 ");
		System.out.println();
		System.out.print("선택> ");
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
		System.out.print("언제부터 ? ");
		String startdate = sc.nextLine();
		System.out.print("언제까지 ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

} // MainView 끝
