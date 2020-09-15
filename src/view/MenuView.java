package view;

import java.util.Scanner;

import controller.AdminController;
import controller.BooksController;
import controller.OrderController;
import controller.RegBookController;
import dto.BookDto;
import dto.OrderLine;
import dto.Orders;
import user.UserSet;

public class MenuView {
	
	private static Scanner sc = new Scanner(System.in);

	/** 초기 화면*/
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

	/** 유저 메뉴*/
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
				break;
			case 3:
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
	


	/** 관리자메뉴*/
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



}
