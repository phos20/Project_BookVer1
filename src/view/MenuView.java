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
	 * 蟾晦 �飛�
	 */
	public static void menu() {
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式 ");
		System.out.println("      Book Store縑 螃褐勘 �紊腎桭炴�.     ");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("\t函  �蛾�           刻 綠�蛾�   ");
		System.out.println();
		System.out.print("摹鷗> ");
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
			System.out.println("螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			break;
		}
	}

	/**
	 * 煎斜檣
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
	 * case : 2 -綠�蛾�-
	 */
	public static void nonmember() {
		System.out.println("式式式式式式式式式式 綠�蛾� 詭景 式式式式式式式式式式 ");
		System.out.println("\t寞僥濠椒 詭景蒂 摹鷗ж撮蹂");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("         函 �蛾灠㊣�  刻 紫憮 跡煙 爾晦  券 謙猿");
		System.out.println();
		System.out.print("摹鷗> ");
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
			System.out.println("螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			break;
		}
	}

	/**
	 * case : 2-1 -�蛾� 陛殮-
	 */
	private static void signUp() {
		System.out.print("陛殮 ID : ");
		String userId = sc.nextLine();

		System.out.print("陛殮 PW : ");
		String userPwd = sc.nextLine();

		System.out.print("陛殮 檜葷 : ");
		String userName = sc.nextLine();

		System.out.print("陛殮 ア廓�� : ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.signUp(userDto);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** 嶸盪 詭景 */
	public static void printUserMenu(String userId) {

		while (true) {
			System.out.println("式式式式式式式式式式式式式式式式式 User Menu 式式式式式式式式式式式式式式式式式式式 ");
			System.out.println("      \t蕾樓濠 : " + userId + " 椒  詭景蒂 摹鷗ж撮蹂 ");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式 ");
			System.out.println(" 函紫憮匐儀         刻輿僥               券輿僥�挫� &唸薯     刷�騆蟋絳音貔�&爾晦  ");
			System.out.println(" 刺濰夥掘棲氬晦   到濰夥掘棲爾晦   刮葆檜む檜雖           制煎斜嬴醒     ");
			System.out.println();
			System.out.print("摹鷗> ");
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
				System.out.println(userId + "椒 螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
				break;
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -紫憮匐儀 -
	 */
	public static void booksearch(String userId) {
		System.out.println("式式式式式式式式式式式式式式式式 紫憮 匐儀  式式式式式式式式式式式式式式式式");
		System.out.println("                    蕾樓濠 : " + userId + " 椒  詭景蒂 摹鷗ж撮蹂 ");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式 ");
		System.out.println("     函 瞪羹 匐儀   刻 薯跡 匐儀  券 濰腦 匐儀  刷 菴煎陛晦 ");
		System.out.println();
		System.out.print("摹鷗> ");

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
			System.out.println("螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			booksearch(userId);
			break;
		}
	}

	/**
	 * case : 1-2 -薯跡 匐儀-
	 */
	private static void searchName() {
		System.out.print("匐儀й 疇 薯跡 : ");
		String booksName = sc.nextLine();
		System.out.println();
		BooksController.selectByName(booksName);

	}

	/**
	 * case : 1-3 -濰腦 匐儀-
	 */
	private static void searchGenre() {
		System.out.print("匐儀й 疇 濰腦 : ");
		String booksGenre = sc.nextLine();
		System.out.println();
		BooksController.selectByGenre(booksGenre);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -輿僥-
	 */
	public static void printInputOrder(String userId) {
		System.out.print("輿僥й 疇 廓�� : ");
		String booksId = sc.nextLine();

		System.out.print("輿僥熱榆 : ");
		int qty = Integer.parseInt(sc.nextLine());

		System.out.print("寡歎輿模 : ");
		String address = sc.nextLine();

		Orders orders = new Orders(0, null, userId, address, 0);
		OrderLine orderLine = new OrderLine(0, 0, booksId, 0, qty, 0);
		orders.getOrderLineList().add(orderLine);

		OrderController.insertOrders(orders);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 - 輿僥頂羲�挫�& 唸薯
	 */
	private static void selectOrderAndPay(String userId) {
		System.out.println(" 函 輿僥�挫�     刻唸薯  ");
		System.out.println();
		System.out.print("摹鷗> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			selectOrderMenu(userId);
			break;
		case 2:
			Payment(userId);
			break;
		default:
			System.out.println("螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			break;
		}
	}

	/** case : 3-1 -輿僥�挫� */
	private static void selectOrderMenu(String userId) {
		System.out.println(" 函 輿僥頂羲�挫�     刻輿僥鏃模  ");
		System.out.println();
		System.out.print("摹鷗> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			OrderController.selectOrdersByUserId(userId);
			break;
		case 2:
			cancleOrders(userId);
			break;
		default:
			System.out.println("螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			break;
		}

	}

	/** case :3-1-2 -輿僥鏃模 */
	private static void cancleOrders(String userId) {
		System.out.println("餉薯ж堅濠ж朝 輿僥 廓�ㄧ� 殮溘ж撮蹂 :");
		int orderNo = Integer.parseInt(sc.nextLine());
		OrderController.cancleOrders(userId, orderNo);

	}

	/**
	 * case : 3-2 -唸薯
	 */
	public static void Payment(String userId) {
		try {
			List<Pay> paylist = orderDao.ordersPriceByUserId(userId);
			double price = 0;
			for (Pay pay : paylist) {
				price += pay.getTotalAmount();

			}

			System.out.println("識旎擋 : " + Double.parseDouble(String.format("%.1f", price)) + " 唸薯 ж衛啊蝗棲梱?");
			System.out.println("         函  唸薯         刻 鏃模  ");
			System.out.println();
			System.out.print("摹鷗> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserController.Pay(price, userId);
				break;
			case 2:
				break;
			default:
				System.out.println("螢夥艇 廓�ㄧ� 援腦撮蹂");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 4 �騆蟋絳音貔�&褻��
	 */
	public static void wishBook(String userId) {
		while (true) {
			System.out.println("式式式式式式式式式式式式式式式式 �騆蟋絳� 蛔煙&褻��  式式式式式式式式式式式式式式式式");
			System.out.println("         函 �騆蟋絳音貔�    刻 �騆蟋絳飛騇狫僅�   券 釭陛晦");
			System.out.print("摹鷗> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				insertRegBook(userId); // 蛔煙
				break;
			case 2:
				RegBookController.selectRegBook(); // 褻��
				break;
			case 3:
				printUserMenu(userId); // 釭陛晦

			default:
				System.out.println(userId + "椒 螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
				break;
			}
		}
	}

	/**
	 * case : 4-1 �騆蟋絳音貔�
	 */
	public static void insertRegBook(String userId) {

		System.out.print("�騆蟋絳� 薯跡: ");
		String regName = sc.nextLine();
		System.out.print("�騆蟋絳� 盪濠: ");
		String regWriter = sc.nextLine();
		System.out.print("�騆蟋絳� 轎っ餌: ");
		String regPublisher = sc.nextLine();

		RegBookDto wish = new RegBookDto(0, regName, regWriter, regPublisher, userId, null);
		RegBookController.insertRegBook(wish);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 5 -濰夥掘棲 氬晦-
	 */
	public static void putCart(String userId) {
		System.out.println("式式式式式式式式式式 濰夥掘棲 氬晦 式式式式式式式式式式");
		System.out.print("紫憮囀萄: ");
		String booksId = sc.nextLine();
		System.out.print("熱榆: ");
		int quantity = Integer.parseInt(sc.nextLine());
		System.out.println();

		CartDto cartDto = new CartDto(0, userId, booksId, quantity, null);

		if (BooksController.selectByBooksId(booksId) == null) {
			System.out.println("紫憮陛 襄營ж雖 彊嬴 濰夥掘棲縑 氬擊 熱 橈蝗棲棻. ");
		} else
			CartController.insertCart(cartDto);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	/**
	 * case : 6 -濰夥掘棲 爾晦-
	 */
	public static void showCart(String userId) {
		System.out.println("式式式式式式式式式式式式式式式 濰夥掘棲 跡煙 式式式式式式式式式式式式式式式");
		Map<BookDto, Integer> map = CartController.selectCart(userId);
		Set<BookDto> set = map.keySet(); // 濰夥掘棲縑 氬曹 疇薑爾 疏頂晦

		// if(map==null) {
		// System.out.println("濰夥掘棲陛 綠橫氈蝗棲棻.");
		// }
		// else {
		System.out.println();
		System.out.println("函 輿僥ж晦    刻 餉薯ж晦    券釭陛晦");
		System.out.print("摹鷗> ");
		System.out.println();
		int num = Integer.parseInt(sc.nextLine());

		if (num == 1) {
			System.out.print("寡歎輿模: ");
			String address = sc.nextLine();

			Orders orders = new Orders(0, null, userId, address, 0);

			Iterator<BookDto> iterator = set.iterator(); // 奩犒濠 橢晦
			while (iterator.hasNext()) { // 偌羹 熱 虜躑 給晦
				BookDto bookDto = iterator.next(); // и 偃曖 偌羹 陛螳褥

				OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId(), 0, map.get(bookDto), 0);
				orders.getOrderLineList().add(orderLine);
			}

			OrderController.insertOrders(orders);
			CartController.deleteCart(userId);
		} else if (num == 2) {
			CartController.deleteCart(userId);
		} else if (num == 3)
			return;
		// else System.out.println("棻衛 殮溘п輿撮蹂. ");
		// }

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 7 -葆檜む檜雖-
	 */
	public static void myPage(String userId) {
		while (true) {
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("               罹晦朝 ⑷營 葆檜む檜雖 殮棲棻 ");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("  函頂薑爾 熱薑ж晦    刻 頂薑爾 驍黴ж晦    券 ん檣お 醱瞪ж晦  刷 菴煎陛晦    ");
			System.out.println();
			System.out.print("摹鷗> ");
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
				System.out.println(userId + "椒 螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
				break;
			}
		}
	}

	/**
	 * case: 7-1 -�蛾虃鶬�-
	 */
	private static void updateUserInfo(String userId) {
		System.out.print("�蛾� 綠塵廓��: ");
		String userPwd = sc.nextLine();
		System.out.print("�蛾� 檜葷: ");
		String userName = sc.nextLine();
		System.out.print("�蛾� �瑒踰齈醽�: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.updateUserInfo(userDto);
	}

	/**
	 * case : 7-2 -�蛾躠酷�-
	 */
	private static void deleteUserInfo(String userId) {
		System.out.print("�蛾� 綠塵廓��: ");
		String userPwd = sc.nextLine();
		System.out.print("�蛾� 檜葷: ");
		String userName = sc.nextLine();
		System.out.print("�蛾� �瑒踰齈醽�: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.deleteUserInfo(userDto);
	}

	/**
	 * case : 7-3 -ん檣お-
	 */
	public static void userPoint(String userId) {

		System.out.println("⑷營 ん檣お: " + UserController.selectPoint(userId));
		System.out.println(" 函 ん檣お 蛔煙ж晦    刻 菴煎 ");
		System.out.println();
		System.out.print("摹鷗> ");
		int num = Integer.parseInt(sc.nextLine());
		if (num == 1) {
			System.out.print("蛔煙й ん檣お: ");
			int point = Integer.parseInt(sc.nextLine());

			UserController.userPoint(userId, point);
		}
		if (num == 2)
			return;
		// else System.out.println("棻衛 殮溘п輿撮蹂. ");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 8 -煎斜嬴醒-
	 */
	private static void logOut(String userId) {
		System.out.println("煎斜嬴醒 腎歷蝗棲棻.");
		UserDto userDto = new UserDto(null, null, null, null, 0, null, 0, null);
		System.exit(0);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 婦葬濠詭景
	 */
	public static void printAdminMenu(String userId) {

		while (true) {
			System.out.println("式式式式式式式式式式式式式式  Admin Menu 式式式式式式式式式式式式式式式");
			System.out.println("\t\t婦葬濠 " + userId + "椒 賅萄   ");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("  函�蛾灠�葬   刻紫憮婦葬   券衙轎婦葬   刷煎斜嬴醒  ");
			System.out.println();
			System.out.print("摹鷗> ");
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
				System.out.println(userId + "婦葬濠椒 螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
				break;
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -�蛾灠�葬-
	 */
	private static void UserManagement() {
		System.out.println("式式式式式式式式式式式 �蛾� 婦葬 詭景 式式式式式式式式式式式");
		System.out.println("    函 �蛾� 跡煙 爾晦   刻 �蛾礸蹌�   券 菴煎陛晦 ");
		System.out.print("摹鷗> ");
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
			System.out.println("婦葬濠椒 螢夥艇 廓�ㄧ� 摹鷗п 輿撮蹂");
			break;
		}
	}

	/**
	 * case 1-2 : -�蛾礸蹌�-
	 */
	private static void updateUserGrade() {
		System.out.print("蛔機 й �蛾� ID : ");
		String userId = sc.nextLine();
		System.out.print("蛔晝 : ");
		String grade = sc.nextLine();

		AdminController.updateUserGrade(grade, userId);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -紫憮婦葬-
	 */
	public static void manageBook() {
		System.out.println("式式式式式式式式式式式式式式式 紫憮婦葬 詭景 式式式式式式式式式式式式式式式式式式");
		System.out.println(" 函 紫憮跡煙 爾晦   刻�騆蟋絳飛騇� 爾晦   券褐敘紫憮 蛔煙   刷紫憮 營堅蹺陛  刺紫憮 餉薯   到釭陛晦");
		System.out.println();
		System.out.print("摹鷗> ");
		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1: // 紫憮跡煙
			BooksController.selectBook();
			return;
		case 2: // �騆蟋絳飛騇�
			RegBookController.selectRegBook();
			break;
		case 3: // 紫憮蛔煙
			printInsertBook();
			break;
		case 4: //營堅蹺陛
			printUpdateBook();
			break;
		case 5: // 紫憮餉薯
			printDeleteBook();
			break;
		case 6:
			return;

		}
	}

	/**
	 * case : 2-3 -紫憮 蛔煙-
	 */
	public static void printInsertBook() {

		System.out.print("蛔煙紫憮 囀萄: ");
		String booksId = sc.nextLine();
		System.out.print("蛔煙紫憮 薯跡 : ");
		String booksName = sc.nextLine();
		System.out.print("蛔煙紫憮 盪濠 : ");
		String booksWriter = sc.nextLine();
		System.out.print("蛔煙紫憮 轎っ餌 : ");
		String booksPublisher = sc.nextLine();
		System.out.print("蛔煙紫憮 轎っ橾 : ");
		String booksPubDate = sc.nextLine();
		System.out.print("蛔煙紫憮 濰腦 : ");
		String booksGenre = sc.nextLine();
		System.out.print("蛔煙紫憮 陛問 : ");
		int booksPrice = Integer.parseInt(sc.nextLine());
		System.out.print("蛔煙紫憮 偃熱 : ");
		int bookStock = Integer.parseInt(sc.nextLine());

		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre,
				booksPrice, bookStock, null); // book 偌羹 儅撩

		BooksController.insertBook(bookDto);
	}
	
	/**
	 * case : 2-4 -紫憮 營堅蹺陛- 
	 */
	public static void printUpdateBook() {
		System.out.print("蹺陛й 紫憮 囀萄: ");
		String booksId = sc.nextLine();
		System.out.print("蹺陛й 偃熱 : ");
		int bookStock = Integer.parseInt(sc.nextLine());
		
		BooksController.updateBook(booksId, bookStock);
	}

	/**
	 * case : 2-5 -紫憮 餉薯-
	 */
	public static void printDeleteBook() {
		System.out.print("餉薯紫憮 囀萄: ");
		String bookId = sc.nextLine();

		BooksController.deleteBook(bookId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 -衙轎婦葬-
	 */
	private static void SalesManagement(String userId) {
		System.out.println("式式式式式式式式式式式式式式式式 衙轎婦葬 詭景 式式式式式式式式式式式式式式式式");
		System.out.println("    函 螃棺曖 衙轎   刻 晦除滌 衙轎   券 識 衙轎   刷 菴煎陛晦 ");
		System.out.println();
		System.out.print("摹鷗> ");
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
			System.out.println("廓�ˋ� 蜃啪 摹鷗п輿撮蹂");
			break;
		}
	}

	/**
	 * case : 3-2 -晦除滌 衙轎-
	 */
	private static void periodSales() {
		System.out.print("樹薯睡攪 ? ");
		String startdate = sc.nextLine();
		System.out.print("樹薯梱雖 ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

} // MainView 部


	
