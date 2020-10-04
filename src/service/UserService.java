package service;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;
import view.MenuView;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	MenuView menuView = new MenuView();

	/**
	 * ȸ������
	 */
	public int signup(UserDto userDto) throws Exception {
		int result = userDao.signUp(userDto);
		if (result == 0)
			throw new Exception("ȸ�������� �� �� �����ϴ�.");
		return result;

	}

	/**
	 * ȸ������ ����
	 */
	public int updateUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.updateUserInfo(userDto);
		if (result == 0)
			throw new SQLException("�������� �ʾҽ��ϴ�");
		return result;
	}

	/**
	 * ȸ������ Ż��
	 **/
	public int deleteUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.deleteUserInfo(userDto);
		if (result == 0)
			throw new SQLException("Ż����� �ʾҽ��ϴ�.");
		return result;
	}

	/**
	 * ����Ʈ �˻�
	 */
	public int getUserPoint(String userId) throws Exception {
		int point = userDao.getUserPoint(userId);
		return point;
	}

	/**
	 * ����Ʈ ���
	 */
	public int userPoint(String userId, int point) throws Exception {
		int result = userDao.userPoint(userId, point);
		if (result == 0)
			throw new Exception("����Ʈ�� �������� �ʾҽ��ϴ�. ");
		return result;
	}

	/**
	 * ȸ������ ��ȸ
	 */
	public UserDto Login(String userId, String userPwd) throws SQLException {
		UserDto userDto = userDao.Login(userId, userPwd);
		if (userDto == null) {
			System.out.println("ȸ�������� �������� �ʽ��ϴ�");
			menuView.menu();
		}
		return userDto;
	}

	/** ���� */
	public void Pay(double price, String userId) throws SQLException {
		double result = userDao.Pay(price, userId);
		if (result == 0)
			throw new SQLException("���� �Ұ�");

	}

}
